/*
 * Part of USBtinViewer - Simple GUI for USBtin - USB to CAN interface
 * http://www.fischl.de/usbtin
 *
 * Notes:
 * - The timestamp is generated in the application on the host, the hardware
 *   timestamping is currently not used!
 * - Disable "Follow" on high-loaded busses!
 *
 * Copyright (C) 2014  Thomas Fischl 
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import de.fischl.usbtin.*;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 * Table model for log messages
 * 
 * @author Thomas Fischl
 */
public class LogMessageTableModel implements TableModel {

    /** Column titles */
    protected final String[] titles = new String[]{"Time (ms)", "Type", "Id", "DLC", "Data"};
    
    /** Column classes */
    protected final Class[] classes = new Class[]{String.class, ImageIcon.class, String.class, String.class, String.class};
    
    /** Type icons */
    protected ImageIcon[] icons;
    
    /** List containing active listeners */
    private final ArrayList<TableModelListener> listeners = new ArrayList<TableModelListener>();
    
    /** List of log messages to view */
    private final ArrayList<LogMessage> messages = new ArrayList<LogMessage>();

    /**
     * Standard constructor
     */
    public LogMessageTableModel() {
        icons = new ImageIcon[]{
            new ImageIcon(getClass().getResource("/res/icons/info.png")),
            new ImageIcon(getClass().getResource("/res/icons/error.png")),
            new ImageIcon(getClass().getResource("/res/icons/receive.png")),
            new ImageIcon(getClass().getResource("/res/icons/send.png"))
        };
    }

    /**
     * Add given message to message list
     * 
     * @param msg Message list to add
     */
    public void addMessage(LogMessage msg) {
        int index = messages.size();
        messages.add(msg);

        TableModelEvent e = new TableModelEvent(this, index, index, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);
        for (int i = 0, n = listeners.size(); i < n; i++) {
            listeners.get(i).tableChanged(e);
        }
    }

    public LogMessage getMessage(int index) {
        return messages.get(index);
    }

    /**
     * Clear the message list
     */
    public void clear() {
        
        if (messages.size() == 0) return;
        int lastRow = messages.size() - 1;

        messages.clear();

        TableModelEvent e = new TableModelEvent(this, 0, lastRow, TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);
        for (int i = 0, n = listeners.size(); i < n; i++) {
            listeners.get(i).tableChanged(e);
        }
    }

    /**
     * Get count of rows
     * 
     * @return Count of rows in the message list
     */
    @Override
    public int getRowCount() {
        return messages.size();
    }

    /**
     * Get count of columns
     * 
     * @return Count of columns of message list
     */
    @Override
    public int getColumnCount() {
        return titles.length;
    }

    /**
     * Get name of given column
     * 
     * @param i Column id
     * @return Name of given column
     */
    @Override
    public String getColumnName(int i) {
        return titles[i];
    }

    /**
     * Get class of given column
     * 
     * @param i Column id
     * @return Class of given column
     */
    @Override
    public Class<?> getColumnClass(int i) {
        return classes[i];
    }

    /**
     * Determin if given cell is editable
     * 
     * @param row Row id
     * @param col Column id
     * @return True, if editable
     */
    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    /**
     * Get value of given table cell
     * 
     * @param row Row id
     * @param col Column id
     * @return Value of given cell
     */
    @Override
    public Object getValueAt(int row, int col) {

        CANMessage canmsg = messages.get(row).getCanmsg();
        if (canmsg == null) {
            switch (col) {
                case 1:
                    return icons[messages.get(row).getType().ordinal()];
                case 4:
                    return messages.get(row).getMessage();
            }
            return "";
        } else {
            switch (col) {
                case 0:
                    return messages.get(row).getTimestamp();
                case 1:
                    return icons[messages.get(row).getType().ordinal()];

                case 2:
                    if (canmsg.isExtended()) {
                        return String.format("%08xh", canmsg.getId());
                    } else {
                        return String.format("%03xh", canmsg.getId());
                    }

                case 3:
                    return canmsg.getData().length;

                case 4:

                    if (canmsg.isRtr()) {
                        return "Remote Transmission Request";
                    }

                    String s = "";
                    byte[] data = canmsg.getData();
                    for (int i = 0; i < data.length; i++) {
                        if (i > 0) {
                            s = s.concat(" ");
                        }
                        s = s.concat(String.format("%02x", data[i]));
                    }

                    return s;

            }
            return "";
        }
    }

    /**
     * Set value of given cell
     * 
     * @param o Object to set
     * @param i Row
     * @param i1 Column
     */
    @Override
    public void setValueAt(Object o, int i, int i1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Add listern
     * 
     * @param tl Listener to add
     */
    @Override
    public void addTableModelListener(TableModelListener tl) {
        listeners.add(tl);
    }

    /**
     * Remove listener
     * 
     * @param tl Listener to remove
     */
    @Override
    public void removeTableModelListener(TableModelListener tl) {
        listeners.remove(tl);
    }

}

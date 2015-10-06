/*
 * Part of USBtinViewer - Simple GUI for USBtin - USB to CAN interface
 * http://www.fischl.de/usbtin
 * *
 * Copyright (C) 2015  Thomas Fischl 
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

/**
 * Represents a log message
 * 
 * @author Thomas Fischl
 */
public class MonitorMessage {

   
    /** Last corresponding log message */
    protected LogMessage lastLogMessage;
       
    /** Timestamp of this log message */
    protected long period;
    
    /** Count of messages */
    protected long count;        
     
    /**
     * Get last corresponding log message
     * 
     * @return Log message
     */
    public LogMessage getLastLogMessage() {
        return lastLogMessage;
    }
    
    /**
     * Set last log message
     * 
     * @param msg Log message to set
     */
    public void setLastLogMessage(LogMessage msg) {
        lastLogMessage = msg;
    }
    
    /**
     * Set period (in milliseconds)
     * 
     * @param period Period
     */
    public void setPeriod(long period) {
        this.period = period;
    }
    
    /**
     * Get period (in milliseconds)
     * 
     * @return Period
     */
    public long getPeriod() {
        return period;
    }
    
    /**
     * Increase counter
     */
    public void increaseCount() {
        count++;
    }
    
    /**
     * Get count of messages
     * 
     * @return Count of messages
     */
    public long getCount() {
        return count;
    }
    
    /**
     * Construct monitor message
     * 
     * @param msg Last log message
     */
    public MonitorMessage(LogMessage msg) {
        this.lastLogMessage = msg;
        this.count = 1;
        this.period = 0;
    }
}

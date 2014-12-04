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

/**
 * Represents a log message
 * 
 * @author Thomas Fischl
 */
public class LogMessage {

    /** Log message type */
    public enum MessageType {
        INFO, ERROR, IN, OUT
    }
    
    /** If CAN message should be logged, the message is stored in this member */
    protected CANMessage canmsg;
    
    /** Message to view */
    protected String message;
    
    /** Type of this log message */
    protected MessageType type;
    
    /** Timestamp of this log message */
    protected long timestamp;

    /**
     * Get type of log message
     * 
     * @return Type of log message
     */
    public MessageType getType() {
        return type;
    }
     
    /**
     * Get CAN message stored to this log message
     * 
     * @return CAN message
     */
    public CANMessage getCanmsg() {
        return canmsg;
    }

    /**
     * Get message string for this log entry
     * 
     * @return Log message string
     */
    public String getMessage() {
        return message;
    }

    /**
     * Get timestamp for this log message
     * 
     * @return Timestamp in milliseconds
     */
    public long getTimestamp() {
        return timestamp;
    }    
    
    /**
     * Construct log message
     * 
     * @param canmsg CAN message to log
     * @param message Message string
     * @param type Type of message
     * @param timestamp Timestamp
     */
    public LogMessage (CANMessage canmsg, String message, MessageType type, long timestamp) {
        this.canmsg = canmsg;
        this.message = message;
        this.type = type;
        this.timestamp = timestamp;
    }
}

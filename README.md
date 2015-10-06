USBtinViewer
============

USBtinViewer is a simple GUI for USBtin, the USB to CAN interface
(http://www.fischl.de/usbtin).

Notes:
 - The timestamp is generated in the application on the host, the hardware
   timestamping is currently not used!
 - Disable "Follow" on high-loaded busses!

Build and run
-------------

The frame design was created with Netbeans built-in designer.

USBtinViewer uses USBtinLib to talk to USBtin devices. USBtinLib uses jSSC for
accessing the virtual serial port. Both libraries are included in this source
code as JAR file in folder lib/.

Ant is used to build the application from Java source code. To run the 
program, type
```
ant run
```

To create a JAR file use
```
ant jar
java -jar dist/USBtinViewer.jar
```


Changelog
---------

1.2 (2015-10-06)
* Updated USBtinLib to version 1.1.0
* Added monitor view

1.1 (2015-05-16)
* Minor changes for compatibility with java 1.6

1.0 (2014-12-04)
First release


License
-------

Copyright (C) 2014  Thomas Fischl (http://www.fischl.de)

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.


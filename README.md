Eclipse DSF Command Template
============================

This project shows how a completely new DSF command can be added to Eclipse.

The new "Report DSF Context" command is added to debug view's popup menu,
and when invoked, either reports the pid of the process that contains the 
selected item, or reports that no process id is known. For simplicity,
System.println is used to report the pid.

The operation also shows some basic enablement/disablement - the operation has 10 second
delay, and the command cannot be invoved on a debug element in the same process until
the previous operation is done. 

Thanks to Mikhail Khodjaiants for guidance.




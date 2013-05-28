Eclipse DSF Command Template
============================

This project shows how a completely new DSF command can be added to Eclipse.

Specifically, new "Report DSF Context" command is added to debug view's popup menu,
that either reports the pid of the process that contains the selected item, or reports
that no process id is known.

The operation also shows some basic enablement/disablement - the operation has 10 second
delay, and the command cannot be invoved on a debug element in the same process until
the previous operation is done.

For simplicity, System.println is used.



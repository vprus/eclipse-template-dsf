Eclipse DSF Command Template
============================

This project shows how a completely new DSF command can be added to Eclipse.

Specifically, new "Report DSF Context" command is added to debug view's popup menu,
that either reports the pid of the process that contains the selected item, or reports
that no process id is known.

Because the primary goal is to demostrate DSF operations, there are some simplifications,
in particular:

- I don't try to enable/disable this command
- The reporting of pid uses System.out.println.

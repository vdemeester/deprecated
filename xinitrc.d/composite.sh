#!/bin/sh
# Enable a composite manager if present.
DCOMPMGR=`which dcompmgr`

if [ -x $DCOMPMGR ]; then
    $DCOMPMGR &
else
    echo "No dcompmgr. You might want to get it :"
    echo "get clone git://git.openbox.org/dana/dcompmgr"
fi

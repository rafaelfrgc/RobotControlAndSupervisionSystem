#!/bin/sh
# This script is used to run SBGTest program to test Java SBG dynamic
# library on Linux systems.
# You can specify host processor architecture (32 or 64 (default)),
# followed by SBGTest arguments.
# Example: sbgtest 32 "/dev/ttyUSB0"

libdir=.
archBits=64

# Set 32 or 64 bit arquitecture
if [ "$1" = "64" ]; then
    # Use default 
    shift
elif [ "$1" = "32" ]; then
    # Compile using 32 bits
   archBits=32
   shift
fi

sbglibdir="$libdir/l$archBits"


echo "Executing SBG Device Java test on $archBits architecture..."
java -d$archBits -cp . -Djava.library.path="$sbglibdir" SBGTest $1 $2

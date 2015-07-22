#!/bin/bash -x
echo "Hello ${LOGNAME}"
echo "Today is $(date)"
echo "Users currently on the machine, and their processes:"
w

### Turn off debug mode ###
set +x

# Run shell commands
echo "Hello ${LOGNAME}"
echo "Today is $(date)"
echo "Users currently on the machine, and their processes:"
w

### Turn on debug mode ###
set -x

set -n # only read command but do not execute them
set -o noexec
echo "This is a test"
# no file is created as bash will only read commands but do not executes them
>/tmp/debug.txt

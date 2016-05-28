#! /bin/bash

function f() {
	echo "your input is:$0 $*"
	echo "number of parameter: $#"
	echo "\$@ is $@"
	for p in "$*"; do
		echo "$p"
	done
}

f 1 2 3 4 

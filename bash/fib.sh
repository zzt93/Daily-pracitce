#! /bin/bash
trap "exit" INT

fib() {
    if [ "$1" -eq 1 ]; then
        echo 0
        return 0
    elif [ "$1" -eq 2 ]; then
        echo 1
        return 1
    fi
    pre1=$(($1 - 1))
    pre2=$(($1 - 2))
    #echo $pre1, $pre2
    res1=$(fib "$pre1")
    res2=$(fib "$pre2")
    res=$(($res1 + $res2))
    echo $res
}

fib 10

v=3
pre1=$(($v - 1))
pre2=$(($v - 2))
res1=$(fib "$pre1")
res2=$(fib "$pre2")
res=$(($res1 + $res2))
echo $res

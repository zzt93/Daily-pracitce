#! /bin/bash

read -p "Enter two numbers:" x y
# no need to surround it with double quote for it is number and should not
# have space in it
ans=$((x + y))
ans1=$(($x + $y))
let ans2=$x+$y
echo "$x + $y = $ans = $ans1 = $ans2"

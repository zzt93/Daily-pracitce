#! /bin/bash
echo -e "Hello \c"
str=World
printf "%s\n" $str
read -p "[Enter your name]: " name
echo "Bye $name"

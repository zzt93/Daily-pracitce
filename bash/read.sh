#! /bin/bash
echo -e "Hello \c"
str=World
printf "%s\n" $str
read -p "[Enter your name]: " name
echo "Bye $name"

read -t 5 -p -s "last 5 second to enter password" pw
read -n1 -p "Do you want to continue?[y|n]" c
echo "your password is $pw"

count=1
cat str | while read line
do 
# $(("10 + 5"))
count=$(($count + 1))
done

echo $count

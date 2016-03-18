#！/bin/bash

for filename in $(ls *.sh); do
echo "name is $filename"
done


quit=n
while [ "$quit" != y ]; do
read choice
case "$choice" in
a) echo "a";;
b) echo "b";;
q|Q) quit=y;;
*) echo "can't recognize";;
esac
done

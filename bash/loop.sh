#！/bin/bash

for filename in $(ls .); do
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

count=0
for i in $(seq 1 10); do
	echo "$i"
	count=$(($count + $i))
	echo "$count"
done

count=0
while [ $count -lt 10 ]; do
	echo "$count"
	let count=count+1
done;

a=01

if [ $a = 1 ]
then 
echo " = 1"
fi

if [ $a -eq 1 ] 
then
echo " eq 1"
fi

if [ $a -gt 0 ]; then
	echo "$a > 0"
elif [ $a -eq 1 ]; then
	echo "$a = 1"
fi

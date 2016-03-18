#! /bin/bash

clear
select item in Continue Finish
do
case "$item" in
        Continue) ;;
        Finish) break ;;
        *) echo "Wrong choice! Please select again!" ;;
esac
done

clear
echo "1) Continue"
echo "2) Finish"

while true ; do
    printf "#? "
    read item
    if [ "$item" == 2 ]; then
        break;
    elif [ "$item" != 1 ]; then
        echo "Wrong choice! Please select again!"
    fi
done
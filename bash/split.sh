#! /bin/bash

pws="gitevivek:x:1002:1002:::/home/gitevivek:/bin/sh"
old="$IFS"
IFS=:

read -r login pw uid gid info info2 home shell <<< "$pws"
printf "Your login name is %s, uid %d, gid %d, home dir set to %s with %s as login shell\n" $login $uid $gid $home $shell
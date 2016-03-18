#! /bin/bash

yesno() {
    msg="$1"
    def="$2"
    while true; do
        echo " "
        echo "$msg"
        read answer
        if [ -n "$answer" ]; then
            case "$answer" in
                y|Y|yes|Yes) return 0 ;;
                n|N|no|No) return 1;;
                *) echo "Error: not recognize your input"
            esac
        else
            echo "$def"
        fi
    done
}

trap "echo 'interrupted'" INT
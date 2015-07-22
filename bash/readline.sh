
read_with_sapce () {
    IFS=''
    while read data; do
        echo "$data"
    done < para.sh
}

read_with_sapce
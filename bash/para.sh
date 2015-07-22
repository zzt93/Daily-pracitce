#! /bin/bash

fun () {
    echo "$1"
    # can't assign it
    $1="something else"
    echo "$1"
}
fun test
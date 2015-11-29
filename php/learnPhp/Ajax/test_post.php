<?php
/**
 * Created by PhpStorm.
 * User: zzt
 * Date: 11/20/15
 * Time: 11:24 AM
 */


function test_post()
{
    $name = $_POST['name'];
    $age = $_POST['age'];
//    print("$name is $age");
//    exit("$name is $age");
    echo "$name is $age";
}

//test_post();
function send()
{
    var_dump($_POST);
    $name = $_POST['funcName'];
    echo $name;
    if (isset($name)) {
        echo 'asdf';
    }
    if (is_string($name)) {
        echo 'asdf';
        $name();
    }
}

send();
<?php
/**
 * Created by PhpStorm.
 * User: zzt
 * Date: 11/20/15
 * Time: 3:47 PM
 */

require 'init.php';

function insert($table, $array)
{
    $db_handle = initDB();
//
//    $user = SQLite3::escapeString($_POST['username']);
//    $pass = SQLite3::escapeString($_POST['password']);

//    var_dump($array);
//    echo $array[0], $array[1];
    $query = "INSERT INTO $table ($array[0]) VALUES ($array[1])";
    echo $query;
    $result = $db_handle->exec($query) or die("Unable to add user $table");
    echo $result;

}

insert('user', ['user, password', 'zzt, 123']);
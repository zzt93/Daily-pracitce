<?php
/**
 * Created by PhpStorm.
 * User: zzt
 * Date: 11/20/15
 * Time: 3:47 PM
 */

require 'sqliteInit.php';

function insert($table, $array)
{
    $db_handle = initDB();

    $query = "INSERT INTO $table ($array[0]) VALUES ($array[1])";
    echo $query;
    $result = $db_handle->exec($query) or die("Unable add data to $table");
    print_r($result);

}

insert('user', ['password, uname, email', '123456, "zzt", "asdf@1.com"']);
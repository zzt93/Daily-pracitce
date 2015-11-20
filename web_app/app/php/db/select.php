<?php
/**
 * Created by PhpStorm.
 * User: zzt
 * Date: 11/20/15
 * Time: 3:47 PM
 */


function select($table, $col, $condition)
{
    $db_handle = new SQLite3(FILENAME) or die('Unable to open database');

    $result = $db_handle->query($query_string);
    $row = $result->fetchArray();
    echo $row;


    $user = SQLite3::escapeString($_POST['username']);
    $pass = SQLite3::escapeString($_POST['password']);

    $query = <<<INS
  INSERT INTO $table VALUES ( '$user', '$pass' )
INS;

    $db_handle->exec($query) or die("Unable to add user $user");
    $result = $db_handle->query('SELECT * FROM users') or die('Query failed');
    while ($row = $result->fetchArray()) {
        echo "User: {$row['username']}\nPasswd: {$row['password']}\n";
    }

}
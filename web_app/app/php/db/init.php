<?php
/**
 * Created by PhpStorm.
 * User: zzt
 * Date: 11/20/15
 * Time: 11:29 PM
 */

define('FILENAME', '../../sql/app.sqlite');

function & initDB() {
    $db = new SQLite3(FILENAME) or die('Unable to open database');
    return $db;
}
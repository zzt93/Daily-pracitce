<?php
/**
 * Created by PhpStorm.
 * User: zzt
 * Date: 11/20/15
 * Time: 11:29 PM
 */

define('FILENAME', '../../sql/app.sqlite');

function & initDB()
{
    $db = new SQLite3(FILENAME) or die('Unable to open database');
    return $db;
}

function getSqliteType($arg)
{
    switch (gettype($arg)) {
        case 'double':
            return SQLITE3_FLOAT;
        case 'integer':
            return SQLITE3_INTEGER;
        case 'boolean':
            return SQLITE3_INTEGER;
        case 'NULL':
            return SQLITE3_NULL;
        case 'string':
            return SQLITE3_TEXT;
        default:
            throw new \InvalidArgumentException('Argument is of invalid type ' . gettype($arg));
    }
}
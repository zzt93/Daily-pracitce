<?php
/**
 * Created by PhpStorm.
 * User: zzt
 * Date: 11/7/15
 * Time: 8:18 PM
 */

session_start();

$start = time();

echo 'waiting';

while (time() - $start < 10) {
    echo '.';
}

session_destroy();


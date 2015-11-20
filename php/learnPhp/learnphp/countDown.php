<?php
/**
 * Created by PhpStorm.
 * User: zzt
 * Date: 11/15/15
 * Time: 10:17 PM
 */

$start = time();

echo 'waiting';

while (time() - $start < 10) {
//    echo '.';
}
//session_destroy();
<?php
/**
 * Created by PhpStorm.
 * User: zzt
 * Date: 11/25/15
 * Time: 9:31 AM
 */

$first = 'ab';
$sec = &$first;
$thi;
unset($first);
echo $sec;
//echo $first;
//echo $thi;
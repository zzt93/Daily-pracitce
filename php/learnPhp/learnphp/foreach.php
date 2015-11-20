<?php
/**
 * Created by PhpStorm.
 * User: zzt
 * Date: 11/6/15
 * Time: 9:26 PM
 */

$employeeAges = [];
$employeeAges["Lisa"] = "28";
$employeeAges["Jack"] = "16";
$employeeAges["Ryan"] = "35";
$employeeAges["Rachel"] = "46";
$employeeAges["Grace"] = "34";

foreach ($employeeAges as $key => $value) {
    echo "Name: $key, Age: $value <br />";
}

$array = array(1, 2, 3, 4, 5);

foreach ($array as $value) {
    echo "Value is $value <br />";
}
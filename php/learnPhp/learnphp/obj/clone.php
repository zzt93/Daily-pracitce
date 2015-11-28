<?php
/**
 * Created by PhpStorm.
 * User: zzt
 * Date: 11/28/15
 * Time: 4:52 PM
 */

//When clone is invoked on a Copy object, a new shallow copy is made
class Copy {}
$first = new Copy();
$second = $first;
// PHP 4: $second and $first are 2 distinct objects
// PHP 5 plus: $second and $first refer to one object
$thi = clone $first;
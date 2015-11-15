<?php
/**
 * Created by PhpStorm.
 * User: zzt
 * Date: 11/15/15
 * Time: 10:17 PM
 */

//Calculate 60 days in the future
//seconds * minutes * hours * days + current time
$inTwoMonths = 60 * 60 * 24 * 60 + time();
setcookie('lastVisit', date("G:i - m/d/y"), $inTwoMonths);


if (isset($_COOKIE['lastVisit'])) {
    $visit = $_COOKIE['lastVisit'];
} else {
    echo "You've got some stale cookies!";
    return;
}


echo "Your last visit was - " . $visit;
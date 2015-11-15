<?php
/**
 * Created by PhpStorm.
 * User: zzt
 * Date: 11/7/15
 * Time: 8:18 PM
 *
 * When website need to pass along user data from one page to another,
 * it might be time to start thinking about using PHP sessions
 */

session_start();

if (isset($_SESSION['views'])) {
    $_SESSION['views'] = $_SESSION['views'] + 1;
} else {
    $_SESSION['views'] = 1;
}

echo "views = " . $_SESSION['views'];
//unset($_SESSION['cart']);



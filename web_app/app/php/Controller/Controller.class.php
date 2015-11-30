<?php

/**
 * Created by PhpStorm.
 * User: zzt
 * Date: 11/29/15
 * Time: 7:13 PM
 */
abstract class Controller
{
    const FUNC_NAME = "funcName";

    /**
     * Controller constructor.
     */
    public function __construct()
    {
        session_start();
        if (isset($_SESSION['loggedin']) && $_SESSION['loggedin'] == true) {
            echo "Welcome to the member's area, " . $_SESSION['username'] . "!";
        } else {
            echo "Please log in first to see this page.";
            header('../../html/login.php');
        }
    }

    public function makeSession($userName) {
        session_start();
        $_SESSION['loggedin'] = true;
        $_SESSION['username'] = $userName;
    }


    abstract function distribute();

}
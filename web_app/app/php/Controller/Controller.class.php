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
    const USER_NAME = "uname";
    const DEFAULT_AJAX_RETURN = 'JSON';

    /**
     * Controller constructor.
     */
    public function __construct()
    {
        session_start();
        if (isset($_SESSION['loggedin']) && $_SESSION['loggedin'] == true) {
//            echo "Welcome to the member's area, " . $_SESSION[self::USER_NAME] . "!";
        } else {
//            echo "Please log in first to see this page.";
            header('../../html/login.php');
        }
    }

    public function makeSession($userName)
    {
        session_start();
        $_SESSION['loggedin'] = true;
        $_SESSION[self::USER_NAME] = $userName;
    }

    public function ajaxReturn($data, $type = '', $json_option = 0)
    {
        if (empty($type)) {
            $type = self::DEFAULT_AJAX_RETURN;
        }
        switch (strtoupper($type)) {
            case 'JSON' :
//                $data = json_encode((array)$data, $json_option);
                break;
            case 'EVAL' :
                break;
        }
//        echo($data);
    }


    abstract function distribute();

}
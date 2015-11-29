<?php
/**
 * Created by PhpStorm.
 * User: zzt
 * Date: 11/20/15
 * Time: 11:40 PM
 *
 */

require_once('includes.php');

/**
 * Class SignController
 * this class is responsible for sign up/log in user
 * and maintain user login state when jump between
 * links
 */
class SignController implements Controller
{


    const UNAME = "uname";
    const EMAIL = "email";
    const PASS = "password";

    private static $sign;
    private $userMapper;

    /**
     * SignController constructor.
     */
    public function __construct()
    {
        if (isset($sign)) {
            self::$sign = new SignController();
        }
        $this->userMapper = new UserMapper();
    }


    public function signUp()
    {
        $userName = $_POST[self::UNAME];
        $userEmail = $_POST[self::EMAIL];
        $password = $_POST[self::PASS];
        // check whether the uer name is unique by insert result
        $res = $this->userMapper->insert(new SignUser(null, $userName, $userEmail, $password));
        $suc = $this->userMapper->isSuccess($res, $this->userMapper->insertStmt);
        if ($suc) {
            echo 'failure';
        } else {
            http_redirect(dirname(__FILE__) . '../../html/account.php');
        }
    }


    public function distribute()
    {
        print_r($_POST);
        $f = $_POST[Controller::FUNC_NAME];
        $this->$f();
    }
}

$sign = new SignController();
$sign->distribute();

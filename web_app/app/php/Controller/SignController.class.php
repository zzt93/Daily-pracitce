<?php
/**
 * Created by PhpStorm.
 * User: zzt
 * Date: 11/20/15
 * Time: 11:40 PM
 *
 */

// place this at the top of the file
if (count(get_included_files()) == 1) {
    define('TEST_SUITE', __FILE__);
}

require_once 'includes.php';

/**
 * Class SignController
 * this class is responsible for sign up/log in user
 * and maintain user login state when jump between
 * links
 */
class SignController extends Controller
{


    const EMAIL = "email";
    const PASS = "password";

    private $userMapper;

    /**
     * SignController constructor.
     */
    public function __construct()
    {
        // not to start session when sign up or log in
        // parent::__construct();
        debug_backtrace();
        $this->userMapper = new UserMapper();
    }


    public function signUp()
    {
        $userName = $_POST[Controller::USER_NAME];
        $userEmail = $_POST[self::EMAIL];
        $password = $_POST[self::PASS];
//        print_r($_POST);
        // check whether the uer name is unique by insert result
        $res = $this->userMapper->insert(array($userName, $userEmail, password_hash($password, PASSWORD_DEFAULT)));
        $suc = $this->userMapper->isSuccess($res, $this->userMapper->insertStmt);
        if ($suc) {
            $this->makeSession($this->userMapper->findByKey($userName));
            header("Location: ../../html/account.php");
        } else {
            header("Location: ../../html/signup.php");
        }
    }

    /**
     * This function should only invoked when the user and password is checked by
     * hasUser();
     */
    public function logIn()
    {
        $userName = $_POST[Controller::USER_NAME];
//        echo $userName;
        $user = $this->userMapper->findByKey($userName);
//        print_r($user);
        $this->makeSession($user);
        header('Location: ../../html/account.php');
    }


    public function hasUserName()
    {
        debug_backtrace();
        $userName = $_GET[Controller::USER_NAME];
        $user = $this->userMapper->findByKey($userName);
        $has = !is_null($user);
        echo $has ? 'true' : 'false';
        return $has;
    }

    public function hasUser()
    {
        // print_r($_GET);
        $userName = $_GET[Controller::USER_NAME];
        $user = $this->userMapper->findByKey($userName);
        if (is_null($user)) {
            echo 'false';
            return false;
        } else {
            $password = $_GET[self::PASS];
            $hash = $user['password'];
            if (password_verify($password, $hash)) {
                echo 'true';
                return true;
            }
            echo 'false';
            return false;
        }
    }

//    function distribute()
//    {
//        //        print_r($_POST);
//        if (isset($_POST[Controller::FUNC_NAME])) {
//            $f = $_POST[Controller::FUNC_NAME];
//        } else {
//            $f = $_GET[Controller::FUNC_NAME];
//        }
//        $this->$f();
//    }
}


if (defined('TEST_SUITE') && TEST_SUITE == __FILE__) {
    // run test suite here
    $sign = new SignController();
    $sign->distribute();
}

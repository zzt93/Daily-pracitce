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
class SignController extends Controller
{


    const USER_NAME = "uname";
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
        $this->userMapper = new SignMapper();
    }


    public function signUp()
    {
        $userName = $_POST[self::USER_NAME];
        $userEmail = $_POST[self::EMAIL];
        $password = $_POST[self::PASS];
        // check whether the uer name is unique by insert result
        $res = $this->userMapper->insert(new SignUser(null, $userName, $userEmail, $password));
        $suc = $this->userMapper->isSuccess($res, $this->userMapper->insertStmt);
        if ($suc) {
            $this->makeSession($userName);
            $user = $this->userMapper->find($userName);
            print_r(json_encode($user));
            header("Location: ../../html/account.php?user=" . json_encode($user));
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
        $userName = $_GET[self::USER_NAME];
        assert($this->hasUser());
        $this->makeSession($userName);
        $user = $this->userMapper->find($userName);
        if (is_null($user)) {
            throw new Exception("no such user");
        }
        header('Location: ../../html/account.php?user=' . json_encode($user));
    }

    public function hasUserName()
    {
        debug_backtrace();
        $userName = $_GET[self::USER_NAME];
        $user = $this->userMapper->find($userName);
        $has = !is_null($user);
        echo $has ? 'true' : 'false';
        return $has;
    }

    public function hasUser()
    {
        print_r($_GET);
        $userName = $_GET[self::USER_NAME];
        $user = $this->userMapper->find($userName);
        if (is_null($user)) {
            echo 'false' . $userName;
            return false;
        } else {
            $password = $_GET[self::PASS];
            $pass = $user->getPassword();
            if ($pass == SignUser::hash($password)) {
                echo 'true';
                return true;
            }
            echo 'false asd';
            return false;
        }
    }

    public function distribute()
    {
//        print_r($_POST);
        $f = $_POST[Controller::FUNC_NAME];
        if (!isset($f)) {
            $f = $_GET[Controller::FUNC_NAME];
        }
        $this->$f();
    }
}

$sign = new SignController();
$sign->distribute();

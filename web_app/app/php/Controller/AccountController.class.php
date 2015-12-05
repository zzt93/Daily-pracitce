<?php
/**
 * Created by PhpStorm.
 * User: zzt
 * Date: 11/29/15
 * Time: 11:24 AM
 */

// place this at the top of the file
if (count(get_included_files()) == 1) {
    define('TEST_SUITE', __FILE__);
}

require_once 'includes.php';


class AccountController extends Controller
{

    private $userMapper;

    /**
     * AccountController constructor.
     */
    public function __construct()
    {
        parent::__construct();
        $this->userMapper = new UserMapper();
        $this->setting = new SettingMapper();
    }


    function getUserData()
    {
//        print_r($_GET);
//        if (!isset($_SESSION[Controller::USER_NAME])) {
//            return;
//        }
        $userName = $_SESSION[Controller::USER_NAME];
        $user = $this->userMapper->findByKey($userName);
        if (isset($user)) {
            $this->ajaxReturn($user);
        }
    }

    function setUserData()
    {
//        print_r($_POST);
        $uid = $_SESSION[Controller::UID];
        $res = $this->userMapper->update(array(
            $_POST[Controller::USER_NAME], $_POST['gender'],
            $_POST['email'], $_POST['age'], $_POST['location']
        ), $uid);
        if (isset($res)) {
            $_SESSION[Controller::USER_NAME] = $_POST[Controller::USER_NAME];
            echo 'true';
        } else {
            echo 'false';
        }
    }

    function getSetting()
    {
        $uid = $_SESSION[Controller::UID];
//        echo $uid;
        $user = $this->setting->findByKey($uid);
        if (isset($user)) {
            $this->ajaxReturn($user);
        } else {
            debug_backtrace();
//            $this->setting->insert(array($uid));
//            $this->ajaxReturn($this->setting->findByKey($uid));
        }
    }

    function updateSetting()
    {
//        print_r($_POST);
        $res = $this->setting->update(
            array(
                $this->checkBox(($_POST['weight'])),
                $this->checkBox(($_POST['heart_rate'])),
                $this->checkBox(($_POST['slumber'])),
                $this->checkBox(($_POST['walk'])),
                $this->checkBox(($_POST['upper_limb'])),
                $this->checkBox(($_POST['lower_limb'])),
                $this->checkBox(($_POST['send'])),
                $_POST['sync']),
            $_SESSION[Controller::UID]
        );
//        print_r($res);
        if (!$res) {
            echo 'false';
        } else {
            echo 'true';
        }
    }

    private function checkBox($c)
    {
        if (is_null($c)) {
            return 0;
        }
        $i = $c == 'true' ? 1 : 0;
        return $i;
    }

    function updateAvatar() {

    }

}

if (defined('TEST_SUITE') && TEST_SUITE == __FILE__) {
    // run test suite here
    $account = new AccountController();
    $account->distribute();
}

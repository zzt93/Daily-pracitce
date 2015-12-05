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
//        echo $userName;
        $user = $this->userMapper->findByKey($userName);
        if (isset($user)) {
            $this->ajaxReturn($user);
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
        $this->setting->update(
            array(
                $this->checkBox(isset($_POST['weight'])),
                $this->checkBox(isset($_POST['heart_rate'])),
                $this->checkBox(isset($_POST['slumber'])),
                $this->checkBox(isset($_POST['walk'])),
                $this->checkBox(isset($_POST['upper_limb'])),
                $this->checkBox(isset($_POST['lower_limb'])),
                $this->checkBox(isset($_POST['send'])),
                $_POST['sync']),
            $_SESSION[Controller::UID]
        );
        self::refreshRedirect("../../html/account.php");
    }

    private function checkBox($c)
    {
        return $c ? 1 : 0;
    }
}

if (defined('TEST_SUITE') && TEST_SUITE == __FILE__) {
    // run test suite here
    $account = new AccountController();
    $account->distribute();
}

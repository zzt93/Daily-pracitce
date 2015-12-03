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
    }


    function getUserData()
    {
//        print_r($_GET);
        if (!isset($_SESSION[Controller::USER_NAME])) {
            return;
        }
        $userName = $_SESSION[Controller::USER_NAME];
//        echo $userName;
        $user = $this->userMapper->find($userName);
        if (isset($user)) {
            $this->ajaxReturn($user);
        }
    }

//    function distribute()
//    {
//
//    }
}

if (defined('TEST_SUITE') && TEST_SUITE == __FILE__) {
    // run test suite here
    $account = new AccountController();
    $account->distribute();
}

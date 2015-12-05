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

class AdminController extends Controller {


    /**
     * AdminController constructor.
     */
    public function __construct()
    {
        parent::__construct();
        $this->activity = new ActivityMapper();
        $this->post = new PostMapper();
    }

    public function addActivity() {
        $time = date(Controller::TIME);
        try {
            $this->activity->insert(array(
                $_POST['title'], $_POST['content'], $time, $_POST['end']
            ));
            header("Location: ../../html/admin.php?now=3");
        } catch (Exception $e) {
            debug_backtrace();
        }
    }

    public function addPost() {
//        print_r($_POST);
        $time = date(Controller::TIME);
        try {
            $this->post->insert(array(
                $_POST['content'], $time
            ));
            header("Location: ../../html/admin.php?now=3");
        } catch (Exception $e) {
            debug_backtrace();
        }
    }

}


if (defined('TEST_SUITE') && TEST_SUITE == __FILE__) {
    // run test suite here
    $analysis = new AdminController();
    $analysis->distribute();
}
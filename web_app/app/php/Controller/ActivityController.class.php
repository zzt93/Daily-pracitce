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

class ActivityController extends Controller {


    /**
     * ActivityController constructor.
     */
    public function __construct()
    {
        parent::__construct();
        $this->posts = new PostMapper();
        $this->activity = new ActivityMapper();
    }

    public function getAllPost() {
        $this->ajaxReturn($this->posts->findAll());
    }

    public function getAllActivities() {
        $this->ajaxReturn($this->activity->findAll());
    }

    public function getAllAP() {
        $this->getAllPost();
        echo Controller::SEPARATOR;
        $this->getAllActivities();
    }
}


if (defined('TEST_SUITE') && TEST_SUITE == __FILE__) {
    // run test suite here
    $analysis = new ActivityController();
    $analysis->distribute();
}
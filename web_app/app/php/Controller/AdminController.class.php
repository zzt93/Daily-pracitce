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

    }

}


if (defined('TEST_SUITE') && TEST_SUITE == __FILE__) {
    // run test suite here
    $analysis = new AnalysisController();
    $analysis->distribute();
}
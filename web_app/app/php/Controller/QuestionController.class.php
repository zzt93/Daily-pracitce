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

class QuestionController extends Controller
{

    private $question;
    private $advice;

    /**
     * QuestionController constructor.
     */
    public function __construct()
    {
        parent::__construct();
        $this->question = new QuestionMapper();
        $this->advice = new AdviceMapper();
    }

    public function getAdviceData()
    {
        $uid = $_SESSION[Controller::UID];
        $questions = $this->question->find($uid);
        $this->ajaxReturn($questions);
        echo Controller::SEPARATOR;
        $advices = $this->advice->find($uid);
        $this->ajaxReturn($advices);
    }

    public function askQuestion()
    {
        $uid = $_SESSION[Controller::UID];
        $type = $_POST['type'];
        if (isset($_POST['to_user'])) {
            $to_user = $_POST['to_user'];
        } else {
            $to_user = null;
        }
        $title = $_POST['title'];
        $content = $_POST['content'];
        $time = date("Y-m-d");
        try {
            $this->question->insert(array(
                $uid, $type, $to_user, $title, $content, $time
            ));
            header("Location: ../../html/health.php");
        } catch (Exception $e) {
            print_r($e);
        }
    }

    public function answerQuestion()
    {
        $qid = $_POST['qid'];
        $content = $_POST['content'];
        $ans_user = $_SESSION[Controller::UID];
        $time = date(DATE_RSS);
        try {
            $this->advice->insert(array(
                $qid, $content, $ans_user, $time
            ));
            // TODO see askQuestion
            header("Location: ../../html/health.php");
        } catch (Exception $e) {
            print_r($e);
        }
    }
}


if (defined('TEST_SUITE') && TEST_SUITE == __FILE__) {
    // run test suite here
    $analysis = new QuestionController();
    $analysis->distribute();
}
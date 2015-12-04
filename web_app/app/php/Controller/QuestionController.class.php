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
    const QID = 'qid';

    /**
     * QuestionController constructor.
     */
    public function __construct()
    {
        parent::__construct();
        $this->question = new QuestionMapper();
        $this->advice = new AdviceMapper();
        $this->users = new UserMapper();
    }

    public function getUserAQ()
    {
//        $uid = $_SESSION[Controller::UID];
//        if (is_null($uid)) {
//            echo Controller::AJAX_LOGIN;
//            return;
//        }
        $questions = $this->question->findByUser();
        $this->ajaxReturn($questions);
        echo Controller::SEPARATOR;
        $advices = $this->advice->findByUser();
        $this->ajaxReturn($advices);
    }

    public function askQuestion()
    {
//        if (is_null($_SESSION[Controller::UID])) {
//            parent::refreshRedirect();
//        }

        $uid = $_SESSION[Controller::UID];
        $type = $_POST['type'];
        if (isset($_POST['to_user'])) {
            $user = $_POST['to_user'];
            $to_user = $this->users->findByKey($user)[Controller::UID];
        } else {
            $to_user = null;
        }
        $title = $_POST['title'];
        $content = $_POST['content'];
        $time = date(Controller::TIME);
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
        $qid = $_POST[self::QID];
        $content = $_POST['content'];
        $ans_user = $_SESSION[Controller::UID];
        $time = date(Controller::TIME);
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

    public function getQuestionAndAdvice() {
        $qid = $_GET[self::QID];
        $this->ajaxReturn($this->question->findByKey($qid));
        echo Controller::SEPARATOR;
        $this->ajaxReturn($this->advice->findByKey($qid));
    }

    public function getAll() {
        $questions = $this->question->findAll();
        $this->ajaxReturn($questions);
    }

}


if (defined('TEST_SUITE') && TEST_SUITE == __FILE__) {
    // run test suite here
    $analysis = new QuestionController();
    $analysis->distribute();
}
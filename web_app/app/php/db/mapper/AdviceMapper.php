<?php
/**
 * Created by PhpStorm.
 * User: zzt
 * Date: 12/1/15
 * Time: 6:51 PM
 */



class AdviceMapper extends Mapper
{
    /**
     * AdviceMapper constructor.
     */
    public function __construct()
    {
        parent::__construct();
        $this->selectUser = self::$db_handler->prepare(
            'SELECT * FROM advice WHERE ans_user=?');

        $this->selectStmt = self::$db_handler->prepare(
            'SELECT * FROM advice WHERE qid=?');
        $this->updateStmt = self::$db_handler->prepare(
            'UPDATE advice SET content=? WHERE qid=?');
        $this->insertStmt = self::$db_handler->prepare(
            'INSERT INTO advice ( qid, content, ans_user, time ) VALUES( ?, ?, ?, ? )');
    }

    public function findByUser() {
        $uid = $_SESSION[Controller::UID];
        return $this->findMore($uid, $this->selectUser);
    }

    /**
     * @return SQLite3Stmt
     */
    protected function selectStmt()
    {
        return $this->selectStmt;
    }

    protected function insertStmt()
    {
        return $this->insertStmt;
    }

    function updateStmt()
    {
        return $this->updateStmt;
    }
}
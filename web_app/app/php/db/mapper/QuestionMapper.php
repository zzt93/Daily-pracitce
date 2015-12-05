<?php

/**
 * Created by PhpStorm.
 * User: zzt
 * Date: 12/1/15
 * Time: 6:50 PM
 */
class QuestionMapper extends Mapper
{
    /**
     * QuestionMapper constructor.
     */
    public function __construct()
    {
        parent::__construct();
        $this->selectUser = self::$db_handler->prepare(
            'SELECT title, content, vote, qid FROM question WHERE uid=?');

        $this->selectStmt = self::$db_handler->prepare(
            'SELECT u.uname, q.type, q.to_user, q.title, q.content, q.time, q.vote
            FROM question q JOIN user u ON q.uid = u.uid WHERE qid=?');
        $this->updateStmt = self::$db_handler->prepare(
            'UPDATE question SET title=?, content=?, vote=? WHERE qid=?');
        $this->insertStmt = self::$db_handler->prepare(
            'INSERT INTO question ( uid, type, to_user, title, content, time) VALUES( ?, ?, ?, ?, ?, ? )');

        $this->selectAll = self::$db_handler->prepare(
            'SELECT * FROM question'
        );
    }

    public function findByUser() {
        $uid = $_SESSION[Controller::UID];
        return $this->findMore($uid, $this->selectUser);
    }

    function selectAll()
    {
        return $this->selectAll;
    }

    public function lastInsertQid() {
        return self::$db_handler->lastInsertRowID();
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
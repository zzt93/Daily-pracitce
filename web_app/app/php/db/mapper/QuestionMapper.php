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
        $this->selectStmt = self::$db_handler->prepare(
            'SELECT * FROM question WHERE uid=?');
        $this->updateStmt = self::$db_handler->prepare(
            'UPDATE question SET title=?, content=?, vote=? WHERE qid=?');
        $this->insertStmt = self::$db_handler->prepare(
            'INSERT INTO question ( uid, type, to_user, title, content, time) VALUES( ?, ?, ?, ?, ?, ? )');

        $this->selectAll = self::$db_handler->prepare(
            'SELECT * FROM question'
        );
    }

    function findAll()
    {
        return $this->selectAll;
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
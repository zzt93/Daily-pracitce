<?php

/**
 * Created by PhpStorm.
 * User: zzt
 * Date: 12/1/15
 * Time: 6:50 PM
 */
class PlanMapper extends Mapper
{

    const COUNT = 'count';

    function __construct()
    {
        parent::__construct();
        $this->selectStmt = self::$db_handler->prepare(
            'SELECT * FROM plan WHERE uid=?');
        $this->updateStmt = self::$db_handler->prepare(
            'UPDATE plan SET breakfast=?, lunch=?, dinner=?, exercise=? WHERE uid=? AND pid=?');
        $this->insertStmt = self::$db_handler->prepare(
            'INSERT INTO plan ( pid, uid ) VALUES( ?, ? )');

        $this->countStmt = self::$db_handler->prepare(
            'SELECT count(*) ' . self::COUNT . ' FROM plan WHERE uid=?'
        );
    }

    public function countPlan()
    {
        return $this->findMore($_SESSION[Controller::UID], $this->countStmt);
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
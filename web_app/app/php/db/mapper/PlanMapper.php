<?php
/**
 * Created by PhpStorm.
 * User: zzt
 * Date: 12/1/15
 * Time: 6:50 PM
 */



class PlanMapper extends Mapper
{
    function __construct()
    {
        parent::__construct();
        $this->selectStmt = self::$db_handler->prepare(
            'SELECT * FROM plan WHERE uid=?');
        $this->updateStmt = self::$db_handler->prepare(
            'UPDATE plan SET breakfast=? WHERE uid=?');
        $this->insertStmt = self::$db_handler->prepare(
            'INSERT INTO plan ( breakfast, lunch, dinner, exercise, uid ) VALUES( ?, ?, ?, ?, ? )');
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
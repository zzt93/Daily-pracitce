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
        $this->selectStmt = self::$db_handler->prepare(
            'SELECT * FROM user WHERE uname=?');
        $this->updateStmt = self::$db_handler->prepare(
            'UPDATE user SET password=?, uname=?, role=?, gender=?, email=?, age=?, icon_url=?, location=? WHERE uid=?');
        $this->insertStmt = self::$db_handler->prepare(
            'INSERT INTO user ( uname, email, password ) VALUES( ?, ?, ? )');
    }


    /**
     * @return SQLite3Stmt
     */
    protected function selectStmt()
    {
        // TODO: Implement selectStmt() method.
    }

    protected function insertStmt()
    {
        // TODO: Implement insertStmt() method.
    }

    function updateStmt()
    {
        // TODO: Implement updateStmt() method.
    }
}
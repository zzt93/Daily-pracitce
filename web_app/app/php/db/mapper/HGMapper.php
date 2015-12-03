<?php
/**
 * Created by PhpStorm.
 * User: zzt
 * Date: 12/1/15
 * Time: 6:51 PM
 */



class HGMapper extends Mapper
{
    function __construct()
    {
        parent::__construct();
        $this->selectStmt = self::$db_handler->prepare(
            'SELECT * FROM health_goal WHERE uid=?');
        $this->updateStmt = self::$db_handler->prepare(
            'UPDATE health_goal SET walk=?, upper_limb=?, lower_limb=? WHERE uid=?');
        $this->insertStmt = self::$db_handler->prepare(
            'INSERT INTO health_goal ( walk, upper_limb, lower_limb ) VALUES( ?, ?, ? )');
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
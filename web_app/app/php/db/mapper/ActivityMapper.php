<?php
/**
 * Created by PhpStorm.
 * User: zzt
 * Date: 12/1/15
 * Time: 6:51 PM
 */



class ActivityMapper extends Mapper
{
    /**
     * ActivityMapper constructor.
     */
    public function __construct()
    {
        parent::__construct();

        $this->selectAll = self::$db_handler->prepare(
            'SELECT title, content FROM activity');

        $this->selectStmt = self::$db_handler->prepare(
            'SELECT * FROM activity WHERE acid=?');
        $this->updateStmt = self::$db_handler->prepare(
            'UPDATE activity SET title=?, content=?, a_img_url=?, enter_amount=?, end_time=? WHERE acid=?');
        $this->insertStmt = self::$db_handler->prepare(
            'INSERT INTO activity ( title, content, post_time, end_time ) VALUES( ?, ?, ?, ? )');
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

    function selectAll()
    {
        return $this->selectAll;
    }
}
<?php
/**
 * Created by PhpStorm.
 * User: zzt
 * Date: 12/1/15
 * Time: 6:50 PM
 */



class PostMapper extends Mapper
{
    /**
     * PostMapper constructor.
     */
    public function __construct()
    {
        parent::__construct();
        $this->selectAll = self::$db_handler->prepare(
            'SELECT * FROM post');

        $this->selectStmt = self::$db_handler->prepare(
            'SELECT * FROM post WHERE post_id=?');
        $this->updateStmt = self::$db_handler->prepare(
            'UPDATE post SET content=? WHERE post_id=?');
        $this->insertStmt = self::$db_handler->prepare(
            'INSERT INTO post (content, post_time) VALUES(?, ? )');
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

    function findAll()
    {
        return $this->selectAll;
    }

}
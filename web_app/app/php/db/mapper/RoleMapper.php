<?php
/**
 * Created by PhpStorm.
 * User: zzt
 * Date: 12/1/15
 * Time: 5:25 PM
 */



class RoleMapper extends Mapper
{
    public function __construct()
    {
        parent::__construct();
        $this->selectStmt = self::$db_handler->prepare(
            'SELECT * FROM role WHERE rid=?');
        $this->updateStmt = self::$db_handler->prepare(
            'UPDATE role SET role=?, role_des=? WHERE rid=?');
        $this->insertStmt = self::$db_handler->prepare(
            'INSERT INTO role ( role, role_des ) VALUES( ?, ? )');
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
<?php

/**
 * Created by PhpStorm.
 * User: zzt
 * Date: 12/1/15
 * Time: 5:25 PM
 */
class SettingMapper extends Mapper
{
    public function __construct()
    {
        parent::__construct();
        $this->selectStmt = self::$db_handler->prepare(
            'SELECT * FROM setting WHERE uid=?');
        $this->updateStmt = self::$db_handler->prepare(
            'UPDATE setting SET weight=?, heart_rate=?, slumber=?, walk=?, upper_limb=?, lower_limb=?, send_campaign=?, sync_time=? WHERE uid=?');
        $this->insertStmt = self::$db_handler->prepare(
            'INSERT INTO setting ( uid ) VALUES( ? )');
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
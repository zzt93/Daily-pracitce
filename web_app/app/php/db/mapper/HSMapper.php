<?php
/**
 * Created by PhpStorm.
 * User: zzt
 * Date: 12/1/15
 * Time: 6:50 PM
 */



class HSMapper extends Mapper
{
    function __construct()
    {
        parent::__construct();
        $this->selectStmt = self::$db_handler->prepare(
            'SELECT * FROM health_statistic WHERE uid=?');
        $this->insertStmt = self::$db_handler->prepare(
            'INSERT INTO health_statistic ( uid, date, weight, heart_rate, slumber, walk, upper_limb, lower_limb ) VALUES( ?, ?, ?, ?, ?, ?, ?, ? )');
    }

    function update(DomainObject $object)
    {
        // do nothing, for this table should not be updated
        // always to insert
    }

    /**
     * @return SQLite3Stmt
     */
    protected function selectStmt()
    {
        return $this->selectStmt;
    }

    public function insertStmt()
    {
        return $this->insertStmt;
    }

    function updateStmt()
    {
        throw new Exception('should not invoke this function');
    }
}
<?php

/**
 * Created by PhpStorm.
 * User: zzt
 * Date: 11/29/15
 * Time: 6:11 PM
 */


require dirname(__FILE__) . '/../sqliteInit.php';

abstract class Mapper
{
    protected static $db_handler;

    function __construct()
    {
        if (!isset(self::$db_handler)) {
            self::$db_handler = initDB();
        }
    }

    /**
     * @param $key -- The primary key or unique key
     * @return array|null
     */
    function findByKey($key)
    {
//        $res = self::$db_handler->query("SELECT * FROM user WHERE uname=$key");
//        print_r($res->fetchArray());
//        return $res;

        $stmt = $this->selectStmt();
        return $this->findMore($key, $stmt);
    }

    /**
     * Invoke when
     *  - only one key is needed to bind
     *  - result is has multiple rows, i.e. return the the array of array
     * @param $key
     * @param $stmt
     * @return array|null
     */
    protected function findMore($key, $stmt)
    {
        $type = $this->getType($key);
//        echo $type;
        $stmt->bindValue(1, $key, $type);

        $cursor = $stmt->execute();
        $res = array();
        while (is_array($array = $cursor->fetchArray())) {
            $res[] = $array;
        }
        switch (count($res)) {
            case 0:
                return null;
            case 1:
                return $res[0];
        }
        return $res;
    }

    function findAll()
    {
        $stmt = $this->selectAll();
        $cursor = $stmt->execute();
        $res = array();
        while (is_array($array = $cursor->fetchArray())) {
            $res[] = $array;
        }
        switch (count($res)) {
            case 0:
                return null;
            case 1:
                return $res[0];
        }
        return $res;
    }


//    function createObject($array)
//    {
//        $obj = $this->doCreateObject($array);
//        return $obj;
//    }

    function insert(array $arr)
    {
        return $this->doInsert($arr);
    }

    static function getType($id)
    {
        return getSqliteType($id);
    }

//    abstract function update(DomainObject $object);

    function update(array $data, $key)
    {
        $stmt = $this->updateStmt();
        $i = 1;
        foreach ($data as $value) {
            $stmt->bindValue($i++, $value, Mapper::getType($value));
        }
        if (is_array($key)) {
            foreach ($key as $k) {
                $stmt->bindValue($i++, $k, Mapper::getType($k));
            }
        } else {
            $stmt->bindValue($i, $key, Mapper::getType($key));
        }
        return $stmt->execute();
    }

//    protected abstract function doCreateObject(array $array);

    /**
     * @param array $data
     * @return mixed -- execution result
     * @internal param DomainObject $object -- This object is for insert, so may be part
     * of full info
     */
    protected function doInsert(array $data)
    {
//        print "inserting\n";
//        debug_print_backtrace();
        $stmt = $this->insertStmt();
        $i = 1;
        foreach ($data as $value) {
            $stmt->bindValue($i++, $value, Mapper::getType($value));
        }
        return $stmt->execute();
    }

    /**
     * @return SQLite3Stmt
     */
    protected abstract function selectStmt();

    protected function selectAll()
    {
        return null;
    }

    protected abstract function insertStmt();

    function isSuccess($sqliteResult, $query)
    {
        if (!$sqliteResult) {
            // the query failed and debugging is enabled
            echo "<p>There was an error in query:";
            print_r($query);
            echo "</p>";
            echo self::$db_handler->lastErrorMsg();
            return false;
        }
        return true;
    }

    function lastError() {
        return self::$db_handler->lastErrorMsg();
    }

    abstract function updateStmt();
}
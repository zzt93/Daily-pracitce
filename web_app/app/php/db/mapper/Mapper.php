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
    function find($key)
    {
//        $res = self::$db_handler->query("SELECT * FROM user WHERE uname=$key");
//        print_r($res->fetchArray());
//        return $res;

        $stmt = $this->selectStmt();
        $type = $this->getType($key);
//        echo $type;
        $stmt->bindValue(1, $key, $type);

        $res = $stmt->execute();
        $array = $res->fetchArray();
        if (!is_array($array)) {
            return null;
        }
        return $array;
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

    function update(DomainObject $object)
    {
        print "updating\n";
        $stmt = $this->updateStmt();
        $data = $object->getData();
        $i = 1;
        foreach ($data as $value) {
            $stmt->bindValue($i++, $value, Mapper::getType($value));
        }
        $stmt->bindValue($i, $object->getKey(), Mapper::getType($object->getKey()));
        $stmt->execute();
    }

//    protected abstract function doCreateObject(array $array);

    /**
     * @param array $data
     * @return mixed -- execution result
     * @internal param DomainObject $object -- This object is for insert, so may be part
     * of full info
     */
    protected function doInsert(array $data) {
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
    protected abstract function insertStmt();

    function isSuccess($sqliteResult, $query) {
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

    abstract function updateStmt();
}
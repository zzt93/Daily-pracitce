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
     * @return $object -- The user account object
     */
    function find($key)
    {
//        $res = self::$db_handler->query("SELECT * FROM user WHERE uname=$key");
//        print_r($res->fetchArray());
//        echo "...";
//        return $res;
        $stmt = $this->selectStmt();
        $type = $this->getType($key);
//        echo $type;
        $stmt->bindValue(1, $key, $type);

        $res = $stmt->execute();
        $array = $res->fetchArray();
//        print_r($array);
        if (!is_array($array)) {
            return null;
        }
        $object = $this->createObject($array);
        return $object;
    }

    function createObject($array)
    {
        $obj = $this->doCreateObject($array);
        return $obj;
    }

    function insert(DomainObject $obj)
    {
        return $this->doInsert($obj);
    }

    static function getType($id)
    {
        return getSqliteType($id);
    }

    abstract function update(DomainObject $object);

    protected abstract function doCreateObject(array $array);

    protected abstract function doInsert(DomainObject $object);

    /**
     * @return SQLite3Stmt
     */
    protected abstract function selectStmt();

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
}
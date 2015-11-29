<?php

/**
 * Created by PhpStorm.
 * User: zzt
 * Date: 11/29/15
 * Time: 6:52 PM
 */
class UserMapper extends Mapper
{

    function __construct()
    {
        parent::__construct();
        $this->selectStmt = self::$db_handler->prepare(
            "SELECT * FROM user WHERE uid=?");
        $this->updateStmt = self::$db_handler->prepare(
            "UPDATE user SET uname=?, uid=? WHERE uid=?");
        $this->insertStmt = self::$db_handler->prepare(
            "INSERT INTO user ( uname, email, password ) VALUES( ?, ?, ? )");
    }

    function update(DomainObject $object)
    {
        print "updating\n";
        $stmt = $this->updateStmt;
        $data = $object->getData();
        $i = 1;
        foreach ($data as $value) {
            $stmt->bindValue($i++, $value, Mapper::getType($value));
        }
        $stmt->execute();
    }

    protected function doCreateObject(array $array)
    {
        $obj = new SignUser($array['uid'], $array['uname'], $array['email'], $array['password']);
        return $obj;
    }

    protected function doInsert(DomainObject $object)
    {
        print "inserting\n";
        debug_print_backtrace();
        $data = $object->getData();
        $stmt = $this->insertStmt;
        $i = 1;
        foreach ($data as $value) {
            $stmt->bindValue($i++, $value, Mapper::getType($value));
        }
        return $stmt->execute();
    }

    protected function selectStmt()
    {
        return $this->selectStmt;
    }
}
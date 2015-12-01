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
            'SELECT * FROM user WHERE uname=?');
        $this->updateStmt = self::$db_handler->prepare(
            'UPDATE user SET uname=?, uid=? WHERE uid=?');
        $this->insertStmt = self::$db_handler->prepare(
            'INSERT INTO user ( uname, email, password ) VALUES( ?, ?, ? )');
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

    /**
     * return full info of user
     * @param array $array
     * @return User
     */
    protected function doCreateObject(array $array)
    {
        $obj = new User(
            $array['uid'],
            $array['password'],
            $array['uname'],
            $array['role'],
            $array['gender'],
            $array['email'],
            $array['age'],
            $array['icon_url'],
            $array['location']
        );
        return $obj;
    }

    /**
     * insert part of user info
     * @param DomainObject $object
     * @return mixed
     */
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
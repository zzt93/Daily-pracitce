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
            'UPDATE user SET uname=?, gender=?, email=?, age=?, location=? WHERE uid=?');
        $this->insertStmt = self::$db_handler->prepare(
            'INSERT INTO user ( uname, email, password ) VALUES( ?, ?, ? )');
    }

    public function updateAvatar($destination)
    {
        $stmt = self::$db_handler->prepare(
            'UPDATE user SET icon_url=? WHERE uid=?');
        $i = 1;
        $stmt->bindValue($i++, $destination, Mapper::getType($destination));
        $uid = $_SESSION[Controller::UID];
        $stmt->bindValue($i, $uid, Mapper::getType($uid));
        $stmt->execute();
    }

//    function update(DomainObject $object)
//    {
//        print "updating\n";
//        $stmt = $this->updateStmt;
//        $data = $object->getData();
//        $i = 1;
//        foreach ($data as $value) {
//            $stmt->bindValue($i++, $value, Mapper::getType($value));
//        }
//        $stmt->bindValue($i, $object->getKey(), Mapper::getType($object->getKey()));
//        $stmt->execute();
//    }

//    /**
//     * return full info of user
//     * @param array $array
//     * @return User
//     */
//    protected function doCreateObject(array $array)
//    {
//        $obj = new User(
//            $array['uid'],
//            $array['password'],
//            $array['uname'],
//            $array['role'],
//            $array['gender'],
//            $array['email'],
//            $array['age'],
//            $array['icon_url'],
//            $array['location']
//        );
//        return $obj;
//    }


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
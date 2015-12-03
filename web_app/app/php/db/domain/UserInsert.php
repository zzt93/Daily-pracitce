<?php

/**
 * Created by PhpStorm.
 * User: zzt
 * Date: 11/29/15
 * Time: 8:32 PM
 */

/**
 * Class UserInsert: used to insert sign up user info
 */
class UserInsert extends DomainObject
{
    private $name;
    private $email;
    private $password;
    const ITERATOIN = 1000;

    /**
     * SignUser constructor.
     * @param $name
     * @param $email
     * @param $password
     */
    public function __construct($uid, $name, $email, $password)
    {
        parent::__construct($uid);
        $this->name = $name;
        $this->email = $email;
        $this->password = $this->hash($password);
    }

    public static function hash($pass)
    {
        return password_hash($pass, PASSWORD_DEFAULT);
    }

    /**
     * @return mixed
     */
    public function getName()
    {
        return $this->name;
    }

    /**
     * @return mixed
     */
    public function getEmail()
    {
        return $this->email;
    }

    /**
     * @return mixed
     */
    public function getPassword()
    {
        return $this->password;
    }

    function getData()
    {
        // the order of this is fixed, see UserMap insertStmt
        return array($this->name, $this->email, $this->password);
    }
}
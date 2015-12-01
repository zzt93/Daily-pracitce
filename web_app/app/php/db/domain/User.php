<?php

/**
 * Created by PhpStorm.
 * User: zzt
 * Date: 11/30/15
 * Time: 10:15 AM
 */
class User extends DomainObject
{
    private $uid;
    private $password;
    private $role;
    private $gender;
    private $email;
    private $age;
    private $icon_url;
    private $location;

    /**
     * User constructor.
     * @param $uid
     * @param $password
     * @param $userName
     * @param $role
     * @param $gender
     * @param $email
     * @param $age
     * @param $icon_url
     * @param $location
     */
    public function __construct($uid, $password, $userName, $role, $gender, $email, $age, $icon_url, $location)
    {
        $this->uid = $uid;
        $this->password = $password;
        parent::__construct($userName);
        $this->role = $role;
        $this->gender = $gender;
        $this->email = $email;
        $this->age = $age;
        $this->icon_url = $icon_url;
        $this->location = $location;
    }


    function getData()
    {
        return array($this->uid,
            $this->password,
            $this->getKey(),
            $this->role,
            $this->gender,
            $this->email,
            $this->age,
            $this->icon_url,
            $this->location);
    }

    function __toString()
    {
        return "{" .
        '"uid"' . ":" . $this->uid .
        '",uerName"' . ":" . $this->getKey() .
        '",roles"' . ":" . $this->role .
        '",gender"' . ":" . $this->gender .
        '",email"' . ":" . $this->email .
        '",age"' . ":" . $this->age .
        '",icon_url"' . ":" . $this->icon_url .
        '",location"' . ":" . $this->location .
        "}";
    }

    /**
     * @return mixed
     */
    public function getPassword()
    {
        return $this->password;
    }

    /**
     * @return null
     */
    public function getUid()
    {
        return $this->uid;
    }

    /**
     * @return mixed
     */
    public function getRole()
    {
        return $this->role;
    }

    /**
     * @return mixed
     */
    public function getGender()
    {
        return $this->gender;
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
    public function getAge()
    {
        return $this->age;
    }

    /**
     * @return mixed
     */
    public function getIconUrl()
    {
        return $this->icon_url;
    }

    /**
     * @return mixed
     */
    public function getLocation()
    {
        return $this->location;
    }


}
<?php

/**
 * Created by PhpStorm.
 * User: zzt
 * Date: 11/29/15
 * Time: 6:53 PM
 */



abstract class DomainObject
{
    private $key;

    function __construct($key = null)
    {
        $this->key = $key;
    }

    function getKey()
    {
        return $this->key;
    }

    /**
     * @return array
     * if the object is for insert: the method must correspond to
     * the insert statement which is always part of object
     *
     * if the object is for update: the method will return all
     * data except key
     */
    abstract function getData();
}
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

    abstract function getData();
}
<?php

/**
 * Created by PhpStorm.
 * User: zzt
 * Date: 11/29/15
 * Time: 6:53 PM
 */
abstract class DomainObject
{
    private $id;

    function __construct($id = null)
    {
        $this->id = $id;
    }

    function getId()
    {
        return $this->id;
    }

    abstract function getData();
}
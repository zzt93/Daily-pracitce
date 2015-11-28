<?php

/**
 * Created by PhpStorm.
 * User: zzt
 * Date: 11/28/15
 * Time: 4:25 PM
 */
class XmlException extends Exception
{

    private $error;

    /**
     * XmlException constructor.
     * @param LibXMLError $libxml_get_last_error
     */

    function __construct(LibXmlError $error)
    {
        $shortfile = basename($error->file);
        $msg = "[{$shortfile}, line {$error->line}, col {$error->column}] ➥
{$error->message}";
        $this->error = $error;
        parent::__construct($msg, $error->code);
    }

    function getLibXmlError()
    {
        return $this->error;
    }
}
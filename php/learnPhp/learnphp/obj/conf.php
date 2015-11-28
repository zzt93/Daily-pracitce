<?php

/**
 * Created by PhpStorm.
 * User: zzt
 * Date: 11/28/15
 * Time: 4:11 PM
 */
class Conf
{
    private $file;
    private $xml;
    private $lastmatch;

    function __construct($file)
    {
        if (!file_exists($file)) {
            throw new Exception("file '$file' does not exist");
        }
        $this->file = $file;
        $this->xml = simplexml_load_file($file);
        if ( ! is_object( $this->xml ) ) {
            throw new XmlException( libxml_get_last_error() );
        }
        print gettype( $this->xml );
        $matches = $this->xml->xpath("/conf");
        if ( ! count( $matches ) ) {
            throw new ConfException( "could not find root element: conf" );
        }
    }

    function write()
    {
        if (!is_writeable($this->file)) {
            throw new Exception("file '{$this->file}' is not writeable");
        }
        file_put_contents($this->file, $this->xml->asXML());
    }

    function get($str)
    {
        $matches = $this->xml->xpath("/conf/item[@name=\"$str\"]");
        if (count($matches)) {
            $this->lastmatch = $matches[0];
            return (string)$matches[0];
        }
        return null;
    }

    function set($key, $value)
    {
        if (!is_null($this->get($key))) {
            $this->lastmatch[0] = $value;
            return;
        }
        $conf = $this->xml->conf;
        $this->xml->addChild('item', $value)->addAttribute('name', $key);
    }
}
<?php
/**
 * Created by PhpStorm.
 * User: zzt
 * Date: 12/2/15
 * Time: 7:47 PM
 */
// place this at the top of the file
if (count(get_included_files()) == 1) {
    define('TEST_SUITE', __FILE__);
}


class xmlDataReader
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
            throw new Exception( libxml_get_last_error() );
        }
//        print gettype( $this->xml );
        $matches = $this->xml->xpath("/data");
        if ( ! count( $matches ) ) {
            throw new Exception( "could not find root element: data" );
        }
    }


    function getUnderUser($str)
    {
        $matches = $this->xml->xpath("/data/user/$str");
        if (count($matches)) {
            $this->lastmatch = $matches[0];
            return (string)$matches[0];
        }
        return null;
    }

    function getAllUnderUser() {
        $matches = $this->xml->xpath("/data/user");
        if (!count($matches)) {
            return array();
        }
        $father = $matches[0];
        $res = array();
        foreach($father->children() as $child) {
            $res[$child->getName()] = (string)$child;
        }
        return $res;
    }

}

if (defined('TEST_SUITE') && TEST_SUITE == __FILE__) {
    // run test suite here  
}
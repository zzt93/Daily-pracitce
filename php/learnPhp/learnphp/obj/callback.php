<?php

/**
 * Created by PhpStorm.
 * User: zzt
 * Date: 11/28/15
 * Time: 5:00 PM
 */

namespace learn;

class Product
{
    public $name;
    public $price;

    function __construct($name, $price)
    {
        $this->name = $name;
        $this->price = $price;
    }
}

class ProcessSale
{
    private $callbacks;

    function registerCallback($callback)
    {
        if (!is_callable($callback)) {
            throw new Exception("callback not callable");
        }
        $this->callbacks[] = $callback;
    }

    function sale($product)
    {
        print "{$product->name}: processing \n";
        foreach ($this->callbacks as $callback) {
            call_user_func($callback, $product);
        }
    }
}

$logger = create_function('$product',
    'print "logger ({$product->name})\n";');
$processor = new ProcessSale();
$logger2 = function ($product) {
    print "logger2 ({$product->name})\n";
};
function f($product) {
    print "logger3 ({$product->name})\n";
}

$processor->registerCallback($logger);
$processor->registerCallback($logger2);
$processor->registerCallback(f);

$processor->sale(new Product("shoes", 6));
print "\n";
$processor->sale(new Product("coffee", 6));


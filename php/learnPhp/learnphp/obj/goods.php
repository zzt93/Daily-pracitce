<?php
/**
 * Created by PhpStorm.
 * User: zzt
 * Date: 11/28/15
 * Time: 2:54 PM
 */

class Goods {
    private $price;
    private $name;
    private $producer;

    /**
     * Goods constructor.
     * @param $price
     * @param $name
     * @param $producer
     */
    public function __construct($price, $name, $producer)
    {
        $this->price = $price;
        $this->name = $name;
        $this->producer = $producer;
    }

    /**
     * @return mixed
     */
    public function getPrice()
    {
        return $this->price;
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
    public function getProducer()
    {
        return $this->producer;
    }

    public function geneSummary() {
        $base = $this->getName();
        $base .= $this->getPrice();
        return $base;
    }

}

class CD extends Goods {
    public $playTime;

    /**
     * @return mixed
     */
    public function getPlayTime()
    {
        return $this->playTime;
    }

    public function geneSummary()
    {
        $base = parent::geneSummary();
        $base .= $this->getPlayTime();
        return $base;
    }


}
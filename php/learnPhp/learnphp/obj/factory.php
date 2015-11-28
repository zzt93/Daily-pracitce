<?php

/**
 * Created by PhpStorm.
 * User: zzt
 * Date: 11/28/15
 * Time: 12:40 PM
 */
class Factory
{
    public static function getInstance($id, PDO $pdo)
    {
        $stmt = $pdo->prepare("select * from products where id=?");
        $result = $stmt->execute(array($id));
        $row = $stmt->fetch();
        if (empty($row)) {
            return null;
        }
        if ($row['type'] == "book") {
            $product = new BookProduct(
                $row['title'],
                $row['firstname'],
                $row['mainname'],
                $row['price'],
                $row['numpages']);
        } else if ($row['type'] == "cd") {
            $product = new CdProduct(
                $row['title'],
                $row['firstname'],
                $row['mainname'],
                $row['price'],
                $row['playlength']);
        } else {
            $product = new ShopProduct(
                $row['title'],
                $row['firstname'],
                $row['mainname'],
                $row['price']);
        }
        $product->setId(
            $row['id']);
        $product->setDiscount(
            $row['discount']);
        return $product;
    }
}
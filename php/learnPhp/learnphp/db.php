<?php
/**
 * Created by PhpStorm.
 * User: zzt
 * Date: 10/24/15
 * Time: 4:06 PM
 */

//$db = new SQLite3('mysqlitedb.db');
//
//$results = $db->query('SELECT bar FROM foo');
//while ($row = $results->fetchArray()) {
//    var_dump($row);
//}

$name_bad = "' OR 1'";

//$name_bad = mysql_real_escape_string($name_bad);

$query_bad = "SELECT * FROM customers WHERE username = '$name_bad'";
echo "Escaped Bad Injection: <br />" . $query_bad . "<br />";


$name_evil = "'; DELETE FROM customers WHERE 1 or username = '";

//$name_evil = mysql_real_escape_string($name_evil);

$query_evil = "SELECT * FROM customers WHERE username = '$name_evil'";
echo "Escaped Evil Injection: <br />" . $query_evil;


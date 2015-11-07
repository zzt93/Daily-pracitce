<?php
require "db.php";
//require "notexist.php";

$myString = "Hello!";
echo $myString;
echo "<h5>I love using PHP!</h5>";


// This won't work because of the quotes around specialH5!
//echo "<h5 class="specialH5">I love using PHP!</h5>";

// OK because we escaped the quotes!
echo "<h5 class=\"specialH5\">I love using PHP!</h5>";

// OK because we used an apostrophe '
echo "<h5 class='specialH5'>I love using PHP!</h5>";

$my_string = "Hello Bob.  My name is: ";
$my_number = 4;
$my_letter = 'a';
echo $my_string;
echo $my_number;
echo $my_letter;

echo "<br>name is $my_number " . "asfd";
echo $my_number, $my_letter;

//phpinfo();

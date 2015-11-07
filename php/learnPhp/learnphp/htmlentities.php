<?php
/**
 * Created by PhpStorm.
 * User: zzt
 * Date: 11/6/15
 * Time: 10:10 PM
 */

$userInput = "I am going to hax0r your site, hahaha!
	<script type='text/javascript'>
	window.location = 'http://www.example.com/'
	</script>'";

//Lets make it safer before we use it
$userInputEntities = htmlentities($userInput);

//Now we can display it
echo $userInputEntities;
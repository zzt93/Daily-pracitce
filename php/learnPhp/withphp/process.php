<html>
<body>
<?php
$quantity = $_POST['quantity'];
$item = $_POST['item'];
$array[3] = 'asfd';

echo "You ordered " . $quantity . " " . $item . ".<br/>";
echo "Thank you for ordering from Art Supplies!" . $array[3];

if (get_magic_quotes_gpc())
    echo "Magic quotes are enabled";
else
    echo "Magic quotes are disabled";
?>

</body>
</html>
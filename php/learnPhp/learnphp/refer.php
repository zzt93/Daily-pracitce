<html>

<head>
    <title>Passing Argument by Reference</title>
</head>

<body>

<?php
function addFive($num)
{
    $num += 5;
}

function addSix(&$num)
{
    $num += 6;
}

$orignum = 10;
addFive($orignum);

echo "Original Value is $orignum<br />";

addSix($orignum);
echo "Original Value is $orignum<br />";

class Test {
    public $i = 0;
}

function addFour(Test $t) {
    $t->i += 4;
}

function addThree(Test &$t) {
    $t->i += 3;
}

$t = new Test();
addFour($t);
echo "Original value is $t->i<br/>";
addThree($t);
echo "Original value is $t->i<br/>";
?>

</body>
</html>
<html>

<head>
    <title>Sending HTML email using PHP</title>
</head>

<body>

<?php
$to = "hcw13@software.nju.edu.cn";
$subject = "This is subject";

$message = "<b>This is HTML message.</b>";
$message .= "<h1>This is headline.</h1>";

$header = "From:zzt13@software.nju.edu.cn \r\n";
$header .= "MIME-Version: 1.0\r\n";
$header .= "Content-type: text/html\r\n";

$res = mail($to, $subject, $message, $header);

if ($res == true) {
    echo "Message sent successfully...";
} else {
    echo "Message could not be sent...";
}
?>

</body>
</html>
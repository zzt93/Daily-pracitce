<?php
require_once '../php/Controller/Controller.class.php';
Controller::releaseSession();
?>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!--<link href="styles/fonts.css" type="text/css" rel="stylesheet">-->
    <link href="../styles/sign-head.css" type="text/css" rel="stylesheet">
    <link href="../styles/sign-footer.css" type="text/css" rel="stylesheet">
    <link href="../styles/sign-content.css" type="text/css" rel="stylesheet">
    <link href="../styles/anchor.css" type="text/css" rel="stylesheet">
    <link href="../styles/tooltip.css" type="text/css" rel="stylesheet">

    <title>Log in</title>
</head>
<body onload="useToolTip('#register')">

<div id="headline">
    <div class="container">

        <header>
            <h1>Personal health app</h1>

            <span>Try to manage every details of your health</span>
        </header>
        <div id="intro">
            <p>So you've understand that your health is kind of a big deal,
                and you're not sure how to manage you health.
                So here is the right place
                to start your management.
            </p>

            <p>This app is designed to teach you what
                you need to know and what you should do
                to manage your health.</p>

            <ul>
                <li>Health statistic tracker</li>
                <li>Advice from professionals: doctor, fitness</li>
                <li>Statistic analysis</li>
                <li>Fitness campaign</li>
                <li>More ...</li>
            </ul>
        </div>
        <form action="../php/Controller/SignController.class.php"
            method="post" id="register" onsubmit="return checkLogIn()">
            <h2 class="inline-h2">Log in </h2>
            <h2 class="smaller-font inline-h2" id="failAuth"> | try <a href="signup.php" class="on-logpanel">sign up</a></h2>

            <br>
            <label for="name">User Name</label>
            <br>
            <input type="text" name="uname" id="name" onchange="checkUser()"
                   title="how to name you in our website?"
                   placeholder="Zeng ZeTang" required>
            <br>

            <label for="pw">Password</label>
            <br>
            <input type="password" name="password" id="pw" onchange="checkUser()"
                   title="your password"
                   placeholder="******" required>
            <br>
            <br>
            <input type="submit" value="Log in">

            <input type="hidden" name="funcName" value="logIn">

            <br>
        </form>
        <!-- Elements after a floating element will flow around it.
         To avoid this, use the clear property.
        The clear property specifies on which sides of an element
         floating elements are not allowed to float:-->
        <br>
    </div>
</div>

<div id="example">
    <div class="container">
        <h2>Our Users</h2>

        <p>some comments and app screen shots</p>

        <div class="avatar">
            <img src="../images/user.png" alt="Li is diving">

            <p class="quote">
                Lee:<br>
                "I follow the advice of my fitness to dive,
                and I feel so good now."
            </p>
        </div>
        <div class="avatar">
            <img src="../images/user.png" alt="Li is diving">
            <p class="quote">
                Lee:<br>
                "I follow the advice of my fitness to dive,
                and I feel so good now."
            </p>

        </div>
        <ul>
            <li>Li's comment and health statistic</li>
            <li>Hong's comment and statistic analysis</li>
        </ul>
    </div>
</div>

<?php require("footer.php"); ?>


<script type="application/javascript" src="../scripts/jquery/dist/jquery.min.js"></script>
<script type="application/javascript" src="../scripts/jquery/jquery.tools.min.js"></script>
<script type="application/javascript" src="../scripts/formToolTip.js"></script>
<script type="application/javascript" src="../scripts/logInCheck.js"></script>


</body>
</html>
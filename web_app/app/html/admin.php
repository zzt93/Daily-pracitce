<?php
require_once '../php/Controller/Controller.class.php';
Controller::testLogIn();
?>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>admin</title>

    <link rel="stylesheet" href="../styles/account-content.css">
    <link rel="stylesheet" href="../styles/admin-content-small.css">
    <link rel="stylesheet" href="../styles/admin-content.css">
    <link rel="stylesheet" href="../styles/main-header.css">
    <link rel="stylesheet" href="../styles/lightBox.css">
    <link rel="stylesheet" href="../styles/ask-content-small.css">
    <link rel="stylesheet" href="../styles/ask-content.css">


    <link rel="stylesheet" href="../fonts/font-awesome-4.4.0/css/font-awesome.min.css"/>

</head>
<body onload="
addLightBox('apply-link');
addListChosenListener('side_nav_list', 'tabbed-block');
getUserAccountInfo();
getActivity();
 ">
<div id="light-box">
    <h3>Apply for:</h3>

    <form method="post" id="register">
        <label for="type">Role type</label>
        <br>
        <input type="text" id="type"
               placeholder="doctor" required>
        <br>
        <label for="proof">Proof</label>
        <br>
        <input type="file" id="proof"
               required>
        <br>
        <input type="submit" value="Apply">
        <br>
    </form>

</div>

<header>
    <section id="main-header">
        <img src="../images/yellow-pin.png" id="logo">

        <p id="app-name">Fit</p>

        <form><p class="action">
                <a href="login.php" class="fa fa-user"> log out</a>
            </p>
        </form>
        <br>
    </section>
</header>

<div id="main-container" class="flex-container-large">
    <nav id="side_nav" class="none">
        <ul id="side_nav_list">
            <li>
                <a href="#">个人信息 </a>
            </li>
            <li>
                <a href="#">申请管理
                    <span class="float-num">5</span>
                    <br>
                </a>
            </li>
            <li>
                <a href="#">用户管理
                    <span class="float-num">3</span>
                    <br>
                </a>
            </li>
            <li>
                <a href="#">活动管理
                    <span class="float-num">1</span>
                    <br>
                </a>
            </li>
        </ul>
    </nav>

    <div id="tabbed-block" class="flex1">


        <div class="container" id="personal">
            <h3>Your public information</h3>
            <section class="personal-info flex-container-large" id="public-info">
                <div class="avatar-wrapper none">
                    <img id="avatar" class="resize" src="../images/user.png" alt="a user">

                    <div id="upload-file-container">
                        <input type="file" name="photo" title="choose a picture" onchange="checkFile(this)">
                    </div>
                </div>
                <div id="change-picture-progress">&nbsp;</div>
                <div class="flex1 info-part">
                    <label for="DisplayName">Display name
                        <br>
                        <input id="DisplayName" value="" maxlength="30" tabindex="1"
                               data-site="Tony" type="text">
                    </label>

                    <br>
                    <label>Location
                        <br>
                        <input id="Location" value="" maxlength="100"
                               tabindex="3" data-site="Nanjing, China" type="text">
                    </label>
                    <br>
                    <input type="submit" value="Submit changes" onclick="setUserAccountInfo(this)">
                    <br>
                </div>

                <div class="flex1 info-part">
                    <h4> Your role
                    </h4>
                    <ul id="roles">
                    </ul>

                    <div class="apply-container" id="apply-container">
                        <a href="#" id="apply-link"> Apply for more role ?
                        </a>
                    </div>
                </div>

            </section>
            <br>

            <h3>Your private information</h3>
            <section class="personal-info" id="private-info">
                <div>
                    <label for="age">Age
                        <br>
                        <input id="age" value="" data-default="Tony" maxlength="30" tabindex="1"
                               data-site="Tony" type="text">
                    </label>

                    <br>
                    <label>Email
                        <br>
                        <input name="email" id="email" value="" maxlength="100"
                               tabindex="3" data-site="Nanjing, China" type="text">
                    </label>
                    <br>
                    <label id="gender">Gender

                        <br>
                        <label>
                            <input type="radio" name="gender">Prefer not to say
                        </label>
                        <label>
                            <input type="radio" name="gender">Female
                        </label>
                        <label>
                            <input type="radio" name="gender">Male
                        </label>
                    </label>
                    <br>
                    <input type="submit" value="Submit changes" onclick="setUserAccountInfo(this)">

                </div>

            </section>
            <br>
        </div>

        <div class="container" id="apply">

            <section class="advice_head flex-container-large">
                <form class="flex1 horizontal-center inner-search">
                    <label class="fa fa-search">
                        <input type="search" class="search-input" placeholder="apply">
                    </label>
                </form>

                <ul class="flex2">
                    <li class="horizontal-li">
                        <!-- TODO add link-->
                        <a href="#">Doctor</a>
                    </li>
                    <li class="horizontal-li">
                        <!-- TODO add link-->
                        <a href="#">Fitness</a>
                    </li>
                    <li class="horizontal-li">
                        <!-- TODO add link-->
                        <a href="#" class="fa fa-plus"> Add more type</a>
                    </li>
                    <br class="clear-left">
                </ul>
            </section>
            <h3>Apply lists:</h3>
            <section class="question-block flex-container">
                <div class="question-statistic none">
                    <h3><a href="#">doctors</a></h3>

                    <p>pediatrics</p>
                </div>

                <div class="question-body flex1">
                    <a class="question-title" href="question.php">Proof</a>

                    <p class="q-content">Description: ...</p>
                </div>
                <br>
            </section>


        </div>

        <div class="container" id="user">

            <section class="advice_head flex-container-large">
                <form class="flex1 horizontal-center inner-search" id="advice-search">
                    <label class="fa fa-search">
                        <input type="search" class="search-input" placeholder="user name">
                    </label>
                </form>

                <ul id="advice-tab" class="flex2">
                    <li class="horizontal-li">
                        <a href="#" class="fa fa-minus"> Revoke</a>
                    </li>
                    <li class="horizontal-li">
                        <a href="#" class="fa fa-plus"> Grant</a>
                    </li>
                    <li class="horizontal-li">
                        <a href="#">?</a>
                    </li>
                    <br>
                </ul>
            </section>
            <h3>User lists:</h3>
            <section class="question-block">

                <div class="user-statistic first-info">
                    <div class="user-choose">
                        <label>
                            <input type="radio" name="users">
                        </label></div>
                    <div class="user-choose">
                        <label>
                            <input type="checkbox" name="users">
                        </label>
                    </div>
                </div>
                <div class="user-statistic">
                    <h3><img src="../images/yellow-pin.png" alt=""></h3>

                    <p>avatar</p>
                </div>
                <div class="user-statistic">
                    <h3>Tom</h3>

                    <p>name</p>
                </div>
                <div class="user-statistic">
                    <h3>20</h3>

                    <p>questions</p>
                </div>
                <div class="user-statistic">
                    <h3>12</h3>

                    <p>answers</p>
                </div>
                <div class="user-statistic">
                    <h3>20</h3>

                    <p>activities</p>
                </div>
                <br>
            </section>

        </div>

        <div class="container" id="campaign">
            <section class="advice_head flex-container-large">
                <form class="flex1 inner-search" id="advice-search">
                    <label class="fa fa-search">
                        <input type="search" class="search-input" placeholder="advice">
                    </label>
                </form>

                <ul id="advice-tab" class="flex2">
                    <li class="horizontal-li">
                        <a href="#">View</a>
                    </li>
                    <li class="horizontal-li">
                        <a href="#">New</a>
                    </li>
                    <br>
                </ul>
            </section>

            <div class="post" id="post">
                <div class="horizontal-center"><h3>Posts</h3></div>
                <div class="notice">
                    <span>10/23/2015</span> -- <span>This is a notice: ...</span>
                    <a href="#" class="right-float" onclick="editPost()">Edit</a>
                    <br class="clear-right">
                </div>
            </div>
            <section class="activity" style="display: none;">
                <div class="horizontal-center"><p>Running Man</p></div>
                <div class="activity-content">
                    <article>
                    </article>
                    <a href="#" class="right-float">More</a>
                    <br class="clear-right">
                </div>
                <div class="activity-join right-float">
                    <form action="../php/Controller/AdminController.class.php" method="post">
                        <input type="submit" value="edit">
                    </form>
                </div>
                <br class="clear-right">
            </section>

            <br>
            <hr>
            <section class="advices-body" style="display: block">
                <h3>Add post:</h3>

                <div class="advice-detail">
                    <label for="post-detail">Details:</label>
                    <br>
                    <textarea form="addpost" name="content" id="post-detail" cols="30" rows="10"
                              required></textarea>
                </div>
                <form id="addpost" action="../php/Controller/AdminController.class.php" method="post">
                    <input type="hidden" name="funcName" value="addPost">
                    <input type="submit" value="Add">
                </form>
            </section>
            <hr>
            <section class="advices-body" style="display: block">
                <h3>Add activity:</h3>

                <div class="advice-title">
                    <label for="activity-title">Title:</label>
                    <br>
                    <input form="activity" name="title" type="text" id="activity-title" required>
                    <br>
                    <label for="end">End time:</label>
                    <br>
                    <input form="activity" name="end" type="date" id="end" required>
                </div>
                <div class="advice-detail">
                    <label for="activity-detail">Details:</label>
                    <br>
                    <textarea form="activity" name="content" id="activity-detail" cols="30" rows="10"
                              required></textarea>
                </div>
                <form id="activity" action="../php/Controller/AdminController.class.php" method="post">
                    <input type="hidden" name="funcName" value="addActivity">
                    <input type="submit" value="Add">
                </form>
            </section>

        </div>


    </div>
</div>
</body>
<?php require("footer.php"); ?>
<script type="application/javascript" src="../scripts/chosen.js"></script>
<script type="application/javascript" src="../scripts/jquery/dist/jquery.min.js"></script>

<script type="application/javascript" src="../scripts/getUserData.js"></script>

<script type="application/javascript" src="../scripts/lightBox.js"></script>

</html>
<?php
require_once '../php/Controller/Controller.class.php';
Controller::testLogIn();
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>health</title>


    <link rel="stylesheet" href="../styles/health-content.css">
    <link rel="stylesheet" href="../styles/main-header.css">
    <!--<link rel="stylesheet" href="styles/lightBox.css">-->

    <link rel="stylesheet" href="../fonts/font-awesome-4.4.0/css/font-awesome.min.css"/>
</head>
<body onload="

addListChosenListener('side_nav_list', 'tabbed-block');
getQuestion();
getActivity();
getHealthInfo();
">
<header>
    <section id="main-header">
        <img src="../images/yellow-pin.png" id="logo">

        <p id="app-name"><a href="health.php">Fit</a></p>

        <form>
            <p class="action">
                <a href="login.php" class="fa fa-sign-out"> log out</a>
            </p>
            <p class="action">
                <a href="account.php" class="fa fa-user"> account</a>
            </p>
        </form>

        <br>
    </section>

    <!--<div id="light-box">-->
        <!--<h3>Log in</h3>-->

        <!--<form method="post" id="register">-->
            <!--<label for="email">Email address</label>-->
            <!--<br>-->
            <!--<input type="email" name="email" id="email"-->
                   <!--placeholder="xxx@example.com" required>-->
            <!--<br>-->
            <!--<label for="pw">Password</label>-->
            <!--<br>-->
            <!--<input type="password" name="pw" id="pw"-->
                   <!--placeholder="******" required>-->
            <!--<br>-->
            <!--<input type="submit" value="Log in">-->
            <!--<br>-->
        <!--</form>-->

    <!--</div>-->

</header>

<div id="main-container" class="flex-container-large">
    <nav id="side_nav" class="none">
        <ul id="side_nav_list">
            <li>
                <a href="#">健康管理</a>
            </li>
            <li>
                <a href="#">健身活动</a>
            </li>
            <li>
                <a href="#">健康建议</a>
            </li>
        </ul>
    </nav>

    <div id="tabbed-block" class="flex1">

        <div class="container" id="health-container">
            <section class="health_block">
                <table id="health">
                    <caption>今日健康概况</caption>
                    <colgroup>
                        <col span="1">
                        <col span="1">
                        <col span="1">
                    </colgroup>
                    <thead>
                    <tr>
                        <th>Content</th>
                        <th>Yours</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <th>Weight</th>
                        <td id="sta_weight"></td>
                    </tr>
                    <tr>
                        <th>心率</th>
                        <td id="sta_heart-rate">..</td>
                    </tr>
                    <tr>
                        <th colspan="3"><a href="account.php?now=1">More</a></th>
                    </tr>
                    </tbody>
                </table>
            </section>

            <section class="health_block">
                <table id="plan">
                    <caption>健身饮食计划</caption>
                    <thead>
                    <tr>
                        <th>Time</th>
                        <th>Content</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <th class="fixed">Breakfast</th>
                        <td id="breakfast"></td>
                    </tr>
                    <tr>
                        <th class="fixed">Lunch</th>
                        <td id="lunch"></td>
                    </tr>
                    <tr>
                        <th class="fixed">Dinner</th>
                        <td id="dinner"></td>
                    </tr>
                    <tr>
                        <th class="fixed">Exercise</th>
                        <td id="exercise"></td>
                    </tr>
                    <tr>
                        <th colspan="3">
                            <a href="javascript:void(0);"
                               onclick="enableTDEdit(this)">
                                Edit
                            </a>
                            <a href="javascript:void(0);"
                               onclick="submitUpdate(this, 'editPlan')"
                               style="display: none"
                            >
                                Submit
                            </a>
                        </th>
                    </tr>
                    </tbody>
                </table>
            </section>

            <section class="health_block">
                <table id="fit">
                    <caption>今日运动数据</caption>
                    <thead>
                    <tr>
                        <th>Content</th>
                        <th>Yours</th>
                        <th>Aims</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <th>步数</th>
                        <td id="sta_walk">1000</td>
                        <td id="goal_walk">2000</td>
                    </tr>
                    <tr>
                        <th>上肢锻炼时间</th>
                        <td id="sta_upper">20</td>
                        <td id="goal_upper">20</td>
                    </tr>
                    <tr>
                        <th colspan="3"><a href="account.php?now=1">More</a></th>
                    </tr>
                    </tbody>
                </table>
            </section>

            <section class="health_block">
                <div class="horizontal-center">
                    <h4>xx数据跟踪 <a href="account.php">Choose other</a></h4>
                </div>
                <canvas id="myChart"></canvas>
            </section>
            <br>
        </div>

        <div class="container" id="campaign">
            <div class="post" id="post">
                <div class="horizontal-center"><h3>Posts</h3></div>
                <div class="notice">
                    <span>10/23/2015</span> -- <span>This is a notice: ...</span>
                </div>
            </div>
            <section class="activity" style="display: none;">
                <div class="horizontal-center"><p>Running Man</p></div>
                <div class="activity-content">
                    <article>graph of n vertices without a circle and has a n-1 edges is a tree, and the n-1 edges is as
                        small
                        as possible
                        proof: suppose the n-1 edges is not a connected graph, then we add some edges to make it
                        connected. Now, we have x (x>=n) edges, n vertices,
                        but without a circle and it is impossible. So the n-1 edges is connected and without a circle,
                        so it's a tree.
                    </article>
                    <a href="#" class="right-float">More</a>
                    <br class="clear-right">
                </div>
                <div class="activity-join horizontal-center">
                    <form action="post">
                        <input type="submit" value="join">
                    </form>
                </div>
            </section>
            <br>
        </div>

        <div class="container" id="advice">

            <section class="advice_head flex-container-large">
                <form class="flex1 horizontal-center inner-search" id="advice-search">
                    <label class="fa fa-search">
                        <input type="search" class="search-input" placeholder="advice">
                    </label>
                </form>

                <ul id="advice-tab" class="flex2">
                    <li class="horizontal-li">
                        <a href="#advice">Tags</a>
                    </li>
                    <li class="horizontal-li">
                        <a href="ask-question.php?now=0">Ask public</a>
                    </li>
                    <li class="horizontal-li">
                        <a href="ask-question.php?now=1">Ask private</a>
                    </li>
                    <br>
                </ul>
            </section>
            <h3>Questions:</h3>
            <section class="question-block">
                <div class="question-statistic">
                    <h3 class="vote">0</h3>

                    <p>votes</p>
                </div>
                <div class="question-statistic">
                    <h3>2</h3>

                    <p>answers</p>
                </div>
                <div class="question-statistic">
                    <h3>20</h3>

                    <p>views</p>
                </div>
                <div class="question-body">
                    <a class="question-title" href="question.php">How to keep fit</a>

                    <p class="q-content">I am ...</p>
                </div>
                <br>
            </section>

        </div>
    </div>
</div>
</body>

<?php require("footer.php"); ?>

<script type="application/javascript" src="../scripts/chosen.js"></script>
<!--<script type="application/javascript" src="scripts/lightBox.js"></script>-->
<script type="application/javascript" src="../scripts/Chart.js-2.0-dev/Chart.js"></script>
<script type="application/javascript" src="../scripts/useBarChart.js"></script>
<script type="application/javascript" src="../scripts/jquery/dist/jquery.min.js"></script>
<script type="application/javascript" src="../scripts/personal.js"></script>
<script type="application/javascript" src="../scripts/health.js"></script>

</html>
<?php
require_once '../php/Controller/Controller.class.php';
Controller::testLogIn();
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>account</title>

    <link rel="stylesheet" href="../styles/account-content.css">
    <link rel="stylesheet" href="../styles/main-header.css">
    <link rel="stylesheet" href="../styles/lightBox.css">


    <link rel="stylesheet" href="../fonts/font-awesome-4.4.0/css/font-awesome.min.css"/>

</head>
<body onload="
addLightBox('apply-link');
addListChosenListener('side_nav_list', 'tabbed-block');
getHealthInfo();
getAdviceInfo();
getActivity();
getSetting();
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

        <p id="app-name"><a href="health.php">Fit</a></p>

        <form>
            <p class="action">
                <a href="login.php" class="fa fa-user"> log out</a>
            </p>

            <p class="action">
                <a href="health.php" class="fa fa-home"> home</a>
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
                <a href="#">数据分析
                    <span class="float-num">5</span>
                    <br>
                </a>
            </li>
            <li>
                <a href="#">你的建议
                    <span class="float-num">3</span>
                    <br>
                </a>
            </li>
            <li>
                <a href="#">你的活动
                    <span class="float-num">3</span>
                    <br>
                </a>
            </li>
            <li>
                <a href="#">自定义
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
                        <form enctype="multipart/form-data" id="avatar-form">
                            <input type="file" name="photo" title="choose a picture" onchange="checkFileAndUpload(this)">
                            <input type="hidden" name="funcName" value="updateAvatar">
                        </form>
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

        <div class="container" id="analysis">
            <section>
                <div class="horizontal-center">
                    <h3>今日数据详情</h3></div>
                <div class="all-health-data">
                    <table id="health">
                        <caption>今日健康数据 <br>
                            <a href="javascript:void(0);"
                               onclick="readTodayData(this)" class="fa fa-refresh"></a>
                            <a href="javascript:void(0);"
                               onclick="readTodayData(this)">更新今日数据</a>
                        </caption>
                        <colgroup>
                            <col span="1">
                            <col span="1">
                            <col span="1">
                        </colgroup>
                        <thead>
                        <tr>
                            <th>Content</th>
                            <th>Statistic</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <th>Weight（kg）</th>
                            <td id="sta_weight"></td>
                        </tr>
                        <tr>
                            <th>心率（次/min）</th>
                            <td id="sta_heart-rate"></td>
                        </tr>
                        <tr>
                            <th>睡眠时间（min）</th>
                            <td id="sta_slumber"></td>
                        </tr>
                        </tbody>
                    </table>
                    <div id="plans">
                        <table id="plan1" onchange="updatePlan(this)">
                            <caption>健身饮食计划:第1天</caption>
                            <thead>
                            <tr>
                                <th class="fixed">Time</th>
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
                                <td id="exercise">
                                </td>
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
                            <tr>
                                <th colspan="3">
                                    <a href="javascript:void(0);"
                                       class="fa fa-plus-circle"
                                       onclick="addPlan(this)"> copy & add plan</a>
                                </th>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <table id="fit">
                        <caption>今日运动动数据</caption>
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
                            <td id="sta_walk"></td>
                            <td id="goal_walk"></td>
                        </tr>
                        <tr>
                            <th>上肢锻炼时间（min）</th>
                            <td id="sta_upper"></td>
                            <td id="goal_upper"></td>
                        </tr>
                        <tr>
                            <th>下肢锻炼时间（min）</th>
                            <td id="sta_lower"></td>
                            <td id="goal_lower"></td>
                        </tr>
                        <tr>
                            <th colspan="3">
                                <a href="javascript:void(0);"
                                   onclick="enableTDEdit(this)">
                                    Edit
                                </a>
                                <a href="javascript:void(0);"
                                   onclick="submitUpdate(this, 'editAim')"
                                   style="display: none"
                                >
                                    Submit
                                </a>

                            </th>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </section>

            <section class="analysis_block">
                <div class="horizontal-center">
                    <h3>数据关联分析</h3>
                </div>
                <form action="" class="horizontal-center">
                    <label>
                        因:
                        <input type="text">
                    </label>
                </form>
                <form action="" class="horizontal-center">
                    <br>
                    <label>
                        果:
                        <input type="text">
                    </label>
                </form>
                <div class="graph">
                    <canvas id="relation-line-chart"></canvas>
                </div>
            </section>

            <section class="analysis_block">
                <div class="horizontal-center">
                    <h3>历史数据对比</h3>
                </div>
                <form action="" class="horizontal-center">
                    <label>
                        数据
                        <input type="text">
                    </label>
                </form>
                <div class="graph">
                    <canvas id="cmp-line-chart"></canvas>
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

                <ul id="advice-tab" class="flex1">
                    <li class="horizontal-li">
                        <a href="#advice">Tags</a>
                    </li>
                    <li class="horizontal-li">
                        <a href="ask-question.php">Ask public</a>
                    </li>
                    <li class="horizontal-li">
                        <a href="ask-question.php">Ask private</a>
                    </li>
                    <br>
                </ul>
            </section>

            <section id="user-question-list" class="user-list">
                <h3>Questions</h3>
                <section class="question-block">
                    <div class="question-statistic">
                        <h3>2</h3>

                        <p>votes</p>
                    </div>
                    <div class="question-body">
                        <a class="question-title" href="">How to keep fit</a>

                        <p class="q-content">I am ...</p>
                    </div>
                    <br>
                </section>

            </section>

            <section id="user-answer-list" class="user-list">
                <h3>Answers</h3>
                <section class="question-block">
                    <div class="question-statistic">
                        <h3>2</h3>

                        <p>votes</p>
                    </div>
                    <div class="question-body">
                        <a class="question-title" href="">How to keep fit</a>

                        <p class="q-content">I am ...asdfasdffffffffffffffffffffffffffffffffffffffffffffffffffff</p>
                    </div>
                    <br>
                </section>
            </section>
            <br>
        </div>

        <div class="container" id="campaign">
            <div class="post" id="post">
                <div class="horizontal-center"><h3>Posts</h3></div>
                <div class="notice">
                    <span>10/23/2015</span>--<span>This is a notice: ...</span>
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
                        <input type="submit" value="quit">
                    </form>
                </div>
            </section>
            <br>
        </div>

        <div class="container" id="settings">
            <h3>Your homepage settings</h3>
            <section class="user-setting">
                <div class="">
                    <h4>健康数据显示选项</h4>
                    <label>
                        <input tabindex="1" type="checkbox" form="setting-sub"
                               name="weight"
                        >weight
                    </label>
                    <label>
                        <input tabindex="1" type="checkbox" form="setting-sub"
                               name="heart_rate"
                        >heart-rate
                    </label>
                    <label>
                        <input tabindex="1" type="checkbox" form="setting-sub"
                               name="slumber"
                        >slumber
                    </label>
                    <h4>运动数据显示选项</h4>
                    <label>
                        <input tabindex="1" type="checkbox" form="setting-sub"
                               name="walk"
                        >walk
                    </label>
                    <label>
                        <input tabindex="1" type="checkbox" form="setting-sub"
                               name="upper_limb"
                        >上肢锻炼
                    </label>
                    <label>
                        <input tabindex="1" type="checkbox" form="setting-sub"
                               name="lower_limb"
                        >下肢锻炼
                    </label>

                </div>

            </section>
            <br>

            <h3>Your account settings</h3>

            <div>
                <label class="fa fa-search">
                    <input type="search" id="setting-search" placeholder="settings">
                </label>
            </div>
            <section class="user-setting">
                <label>
                    <input tabindex="1" type="checkbox" form="setting-sub"
                           name="send"
                    >Send you campaign notification by email
                </label>
                <br>
                <label>
                    Times to sync your data everyday:
                    <input tabindex="1" type="number" min="1" form="setting-sub"
                           name="sync"
                    >
                </label>
                <br>

            </section>

            <input type="submit" value="Submit changes" onclick="setUserSetting(this)">
            <input type="hidden" name="funcName" value="updateSetting">
        </div>

    </div>
</div>
</body>

<?php require("footer.php"); ?>

<script type="application/javascript" src="../scripts/chosen.js"></script>
<script type="application/javascript" src="../scripts/lightBox.js"></script>
<script type="application/javascript" src="../scripts/Chart.js-2.0-dev/Chart.js"></script>
<script type="application/javascript" src="../scripts/useLineChart.js"></script>
<script type="application/javascript" src="../scripts/jquery/dist/jquery.min.js"></script>

<script type="application/javascript" src="../scripts/personal.js"></script>
<script type="application/javascript" src="../scripts/getUserData.js"></script>

<script type="text/javascript">
    $(getUserAccountInfo);
</script>

</html>

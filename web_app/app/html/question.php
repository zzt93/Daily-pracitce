<?php
require_once '../php/Controller/Controller.class.php';
Controller::testLogIn();
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Question</title>

    <link rel="stylesheet" href="../styles/ask-content.css">
    <link rel="stylesheet" href="../styles/main-header.css">

    <link rel="stylesheet" href="../fonts/font-awesome-4.4.0/css/font-awesome.min.css"/>
</head>
<body onload="getAdviceInfo()">
<header>
    <section id="main-header">
        <img src="../images/yellow-pin.png" id="logo">

        <p id="app-name">Fit</p>

        <form><p class="action">
                <a href="#" class="fa fa-user"> log out</a>
            </p>
        </form>

        <br>
    </section>
</header>

<div id="main-container" class="flex-container-large">

    <nav id="side_nav" class="none">
        <ul id="side_nav_list">
            <li>
                <a href="health.php?now=0" onclick="nowSelected.changeTo(0)">健康管理</a>
            </li>
            <li>
                <a href="health.php?now=1" onclick="nowSelected.changeTo(1)">健身活动</a>
            </li>
            <li class="tab-chosen">
                <a href="health.php?now=2" onclick="nowSelected.changeTo(2)">健康建议</a>
            </li>
        </ul>
    </nav>

    <div class="flex1">

        <div class="container">
            <section class="advice_head flex-container-large">
                <form class="flex1 inner-search" id="advice-search">
                    <label class="fa fa-search">
                        <input type="search" class="search-input" placeholder="advice">
                    </label>
                </form>

                <ul id="advice-tab" class="flex2">
                    <li class="horizontal-li">
                        <a href="#">Tags</a>
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
        </div>

        <div class="container" id="tab-ask">

            <section class="ask-question-block" id="question">
                <h3>How to make a plan for fit?</h3>

                <div class="asked-question flex-container template">
                    <div class="vote none">
                        <a class="fa fa-2x fa-caret-up"
                           title="This question shows research effort; it is useful and clear"></a>
                        <br>
                        <span class="vote-count">4</span>
                        <br>
                        <a class="fa fa-2x fa-caret-down"
                           title="This question does not show any research effort; it is unclear or not useful"></a>
                    </div>
                    <div class="flex1">
                        <article>graph of n vertices without a circle and has a n-1 edges is a tree, and the n-1 edges is as
                            small
                            as possible
                            proof: suppose the n-1 edges is not a connected graph, then we add some edges to make it
                            connected. Now, we have x (x>=n) edges, n vertices,
                            but without a circle and it is impossible. So the n-1 edges is connected and without a
                            circle,
                            so it's a tree.
                        </article>

                        <div class="advice-tags">
                            <h4>Tags: </h4>
                            <span class="tags">Fit</span>
                            <span class="tags">Plan</span>
                        </div>
                        <section>
                            <span class="question-info">user ze</span><br>
                            asked:
                            <span class="question-info">
                             2015-11-11 00:00:00
                            </span>
                        </section>
                    </div>
                </div>

            </section>

            <section class="ask-question-block" id="advice">

                <h3>Answers:</h3>

                <div class="asked-question flex-container template">
                    <div class="vote none">
                        <a class="fa fa-2x fa-caret-up"
                           title="This question shows research effort; it is useful and clear">
                        </a>
                        <br>
                        <span class="vote-count">4</span>
                        <br>
                        <a class="fa fa-2x fa-caret-down"
                           title="This question does not show any research effort; it is unclear or not useful">
                        </a>
                    </div>
                    <div class="flex1">
                        <article>graph of n vertices without a circle and has a n-1 edges is a tree, and the n-1
                            edges is as
                            small
                            as possible
                            proof: suppose the n-1 edges is not a connected graph, then we add some edges to make it
                            connected. Now, we have x (x>=n) edges, n vertices,
                            but without a circle and it is impossible. So the n-1 edges is connected and without a
                            circle,
                            so it's a tree.
                        </article>
                        <br>
                        <section>
                            <span class="question-info">user ze</span><br>
                            answered:
                            <span class="question-info">
                             2015-11-11 00:00:00
                            </span>
                        </section>
                    </div>
                </div>

            </section>

            <section class="advices-body" id="answer">
                <h3>Your answer:</h3>

                <div class="advice-detail">
                    <label for="pri-question-detail"></label>
                    <textarea form="sub_answer" name="content" id="pri-question-detail"
                              cols="30" rows="10"></textarea>
                </div>
                <form action="../php/Controller/QuestionController.class.php" method="post" id="sub_answer" onsubmit="setQid()">
                    <input type="submit" value="Submit your answer">
                    <input type="hidden" name="funcName" value="answerQuestion">
                    <input type="hidden" name="qid" value="">
                </form>
            </section>
        </div>
    </div>
</div>
</body>

<?php require("footer.php"); ?>

<script type="application/javascript" src="../scripts/chosen.js"></script>
<script type="application/javascript" src="../scripts/jquery/dist/jquery.min.js"></script>
<script type="application/javascript" src="../scripts/question.js"></script>


</html>
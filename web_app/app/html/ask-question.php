<?php
require_once '../php/Controller/Controller.class.php';
Controller::testLogIn();
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Ask question</title>

    <link rel="stylesheet" href="../styles/ask-content.css">
    <link rel="stylesheet" href="../styles/main-header.css">

    <link rel="stylesheet" href="../fonts/font-awesome-4.4.0/css/font-awesome.min.css"/>
</head>
<body onload="addListChosenListener('advice-tab', 'tab-ask');">
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
                <a href="health.php?now=0">健康管理</a>
            </li>
            <li>
                <a href="health.php?now=1">活动管理</a>
            </li>
            <li class="tab-chosen">
                <a href="health.php?now=2">建议管理</a>
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
                        <a href="#">Ask public</a>
                    </li>
                    <li class="horizontal-li">
                        <a href="#">Ask private</a>
                    </li>
                    <br>
                </ul>
            </section>
        </div>

        <div class="container" id="tab-ask">

            <section class="advices-body">
                <h3>Ask in public:</h3>

                <div class="advice-title">
                    <label for="pub-question-title">Title:</label>
                    <br>
                    <input form="ask-public" name="title" type="text" id="pub-question-title" required>
                </div>
                <div class="advice-detail">
                    <label for="pub-question-detail">Details( be specific):</label>
                    <br>
                    <textarea form="ask-public" name="content" id="pub-question-detail" cols="30" rows="10"
                              required></textarea>
                </div>
                <div class="advice-title">
                    <label for="pub-question-tag">Tags:</label>

                    <span class="tags">Fit</span>
                    <span class="tags">Plan</span>
                    <br>
                    <input form="ask-public" name="tag" type="text" id="pub-question-tag" required>
                </div>
                <form id="ask-public" action="../php/Controller/QuestionController.class.php" method="post">
                    <input type="hidden" name="funcName" value="askQuestion">
                    <input type="hidden" name="type" value="0">
                    <input type="submit">
                </form>
            </section>

            <section class="advices-body">
                <h3>Ask in private:</h3>

                <div class="advice-title">
                    <label for="pri-question-target">To who:</label>
                    <br>
                    <input form="ask-private" name="to_user" type="text" id="pri-question-target" required>
                </div>
                <div class="advice-title">
                    <label for="pri-question-title">Title:</label>
                    <br>
                    <input form="ask-private" name="title" type="text" id="pri-question-title" required>
                </div>
                <div class="advice-detail">
                    <label for="pri-question-detail">Details( be specific):</label>
                    <br>
                    <textarea form="ask-private" name="content" id="pri-question-detail" cols="30" rows="10"
                              required></textarea>
                </div>
                <div class="advice-title">
                    <label for="pri-question-tag">Tags:</label>

                    <span class="tags">Fit</span>
                    <span class="tags">Plan</span>
                    <br>
                    <input form="ask-private" name="tag" type="text" id="pri-question-tag" required>
                </div>
                <form id="ask-private" action="../php/Controller/QuestionController.class.php" method="post">
                    <input type="hidden" name="funcName" value="askQuestion">
                    <input type="hidden" name="type" value="1">
                    <input type="submit">
                </form>
            </section>

        </div>
    </div>
</div>


</body>
<?php require("footer.php"); ?>
<script type="application/javascript" src="../scripts/chosen.js"></script>


</html>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Ask question</title>

    <link rel="stylesheet" href="styles/ask-content.css">
    <link rel="stylesheet" href="styles/main-header.css">

    <link rel="stylesheet" href="fonts/font-awesome-4.4.0/css/font-awesome.min.css"/>
</head>
<body>
<header>
    <section id="main-header">
        <img src="images/yellow-pin.png" id="logo">

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
                <a href="health.php">健康管理</a>
            </li>
            <li>
                <a href="health.php">活动管理</a>
            </li>
            <li class="tab-chosen">
                <a href="health.php">建议管理</a>
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
                    <a href="#">Ask public</a>
                </li>
                <li class="horizontal-li">
                    <a href="#">Ask private</a>
                </li>
                <br>
            </ul>
        </section></div>

        <div class="container" id="tab-ask">
            <section>
                <h3 class="advices-body">Tags:</h3>
                <section class="question-block">
                    <div class="question-statistic">
                        <h3>0</h3>

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
                <section class="question-block">
                    <div class="question-statistic">
                        <h3>0</h3>

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
            </section>
            <section class="advices-body">
                <h3>Ask in public:</h3>
                <div class="advice-title">
                    <label for="pub-question-title">Title:</label>
                    <br>
                    <input type="text" id="pub-question-title">
                </div>
                <div class="advice-detail">
                    <label for="pub-question-detail">Details( be specific):</label>
                    <br>
                <textarea name="question-detail" id="pub-question-detail" cols="30" rows="10">

                </textarea>
                </div>
                <div class="advice-title">
                    <label for="pub-question-tag">Tags:</label>
                    <br>
                    <input type="text" id="pub-question-tag">
                </div>
                <form action="post">
                    <input type="submit">
                </form>
            </section>
            <section class="advices-body">
                <h3>Ask in private:</h3>
                <div class="advice-title">
                    <label for="pri-question-target">To who:</label>
                    <br>
                    <input type="text" id="pri-question-target">
                </div>
                <div class="advice-title">
                    <label for="pri-question-title">Title:</label>
                    <br>
                    <input type="text" id="pri-question-title">
                </div>
                <div class="advice-detail">
                    <label for="pri-question-detail">Details( be specific):</label>
                    <br>
                <textarea name="question-detail" id="pri-question-detail" cols="30" rows="10">

                </textarea>
                </div>
                <div class="advice-title">
                    <label for="pri-question-tag">Tags:</label>
                    <br>
                    <input type="text" id="pri-question-tag">
                </div>
                <form action="post">
                    <input type="submit">
                </form>
            </section>
        </div>
    </div>
</div>

<?php require("footer.php"); ?>


</body>
<footer>
    <!-- copy from myindex.html -->
</footer>
<!--<script type="application/javascript" src="scripts/chosen.js"></script>-->


</html>
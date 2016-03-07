<%--
  Created by IntelliJ IDEA.
  User: zzt
  Date: 2/22/16
  Time: 11:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<html>
<head>
    <title>Main branch waiter</title>

    <link rel="stylesheet" href="../styles/account-content.css">
    <link rel="stylesheet" href="../styles/main-header.css">
    <link rel="stylesheet" href="../styles/halfArea.css">


    <link rel="stylesheet" href="../fonts/font-awesome-4.4.0/css/font-awesome.min.css"/>

    <!-- jTable Metro theme -->
    <link href="../scripts/jtable.2.4.0/themes/metro/blue/jtable.css" rel="stylesheet" type="text/css"/>
    <link href="../scripts/jquery-ui-1.11.4/jquery-ui.min.css" rel="stylesheet"
          type="text/css"/>

</head>
<body onload="
addListChosenListener('side_nav_list', 'tabbed-block');
">

<header>
    <section id="main-header">
        <img src="../images/yellow-pin.png" id="logo">

        <p id="app-name"><a href="#">Staff Id: ${sessionScope.sid}</a></p>

        <form>
            <p class="action">
                <a href="<s:url action='InnerLogin_logOut'/>" class="fa fa-user"> log out</a>
            </p>

        </form>
        <br>
    </section>
</header>

<div id="main-container" class="flex-container-large">
    <nav id="side_nav" class="flex-none">
        <ul id="side_nav_list">
            <li>
                <a href="#">Plan </a>
            </li>
            <li>
                <a href="#">Branches </a>
            </li>
            <li>
                <a href="#">Users </a>
            </li>
            <li>
                <a href="#">Statistic </a>
            </li>
        </ul>
    </nav>

    <div id="tabbed-block" class="flex1">

        <div class="container">
            <div id="plan"></div>
            <div class="horizontal-center">
                <input type="submit" onclick="approvePlan()" value="Approve"/>
                <pre>  </pre>
                <input type="submit" onclick="rejectPlan()" value="Reject"/>
            </div>
        </div>

        <div class="container">
            <div id="branch"></div>
        </div>

        <div class="container">
            <div id="userCard"></div>
        </div>

        <div class="container">
            <div class="half">
                <div class="horizontal-center">
                    <h4>Age and Gender</h4>
                </div>
                <canvas id="ageChart"></canvas>
            </div>
            <div class="half">
                <div class="horizontal-center">
                    <h4>Profit</h4>
                </div>
                <canvas id="profitChart"></canvas>
            </div>
            <div class="half">
                <div class="horizontal-center">
                    <h4>Top 3 dessert</h4>
                </div>
                <canvas id="dessertChart"></canvas>
            </div>
        </div>

    </div>
</div>


<script type="application/javascript" src="../scripts/chosen.js"></script>
<script type="application/javascript" src="../scripts/jquery/dist/jquery.min.js"></script>
<!-- jTable script file. -->
<script src="../scripts/jquery-ui-1.11.4/jquery-ui.min.js" type="text/javascript"></script>
<script src="../scripts/jtable.2.4.0/jquery.jtable.js" type="text/javascript"></script>
<script type="application/javascript" src="../scripts/Chart.js-2.0-dev/Chart.js"></script>
<script type="application/javascript" src="../scripts/useBarChart.js"></script>
<script type="application/javascript" src="../scripts/manager.js"></script>

<script type="text/javascript">
    $(tables);

    $(getChartData);

</script>

</body>
</html>

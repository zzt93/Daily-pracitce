<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: zzt
  Date: 2/13/16
  Time: 11:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page session="false" %>


<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Branches</title>

    <link rel="stylesheet" href="../styles/account-content.css">
    <link rel="stylesheet" href="../styles/main-header.css">
    <link href="../styles/branches.css" type="text/css" rel="stylesheet">


    <link rel="stylesheet" href="../fonts/font-awesome-4.4.0/css/font-awesome.min.css"/>

</head>
<body onload="
addListChosenListener('side_nav_list', 'tabbed-block');
">


<header>
    <section id="main-header">
        <img src="../images/yellow-pin.png" id="logo">

        <p id="app-name"><a href="<s:url action="Branches"/> ">Dessert</a></p>

        <form>
            <p class="action">
                <a href="<s:url action='Login_input'/>" class="fa fa-user"> log out</a>
            </p>

            <p class="action">
                <a href="<s:url action='Account'/> " class="fa fa-home"> ${sessionScope.userName}</a>
            </p>
        </form>
        <br>
    </section>
</header>

<div id="main-container" class="flex-container-large">
    <nav id="side_nav" class="flex-none">
        <ul id="side_nav_list">
            <li>
                <a href="#">Branches </a>
            </li>
            <li>
                <a href="#">Consumption </a>
            </li>
        </ul>
    </nav>

    <div id="tabbed-block" class="flex1">

        <div class="container">

            <div class="branch half">
                <s:url action="Branch" var="branchLink">
                    <s:param name="branchNum">0</s:param>
                </s:url>
                <h3>
                    <a href="${branchLink}">Main Branch</a>
                </h3>
                <h5>
                    The hilariously misnamed Pancake Dessert House in Chinatown's Midcity Arcade specializes in
                    fantastic Hong
                    Kong cuisine, in fact it seems to serve up almost everything...except pancake desserts (though I'm
                    sure they
                    have some somewhere). The sheer amount of variety on the menu can be challenging for first timers
                    but most
                    dishes are pretty well done.
                </h5>
            </div>
            <div class="branch half">
                <s:url action="Branch" var="branchLink">
                    <s:param name="branchNum">1</s:param>
                </s:url>
                <h3>
                    <a href="${branchLink}">Branch one</a>
                </h3>
                <h5>
                    The hilariously misnamed Pancake Dessert House in Chinatown's Midcity Arcade specializes in
                    fantastic Hong
                    Kong cuisine, in fact it seems to serve up almost everything...except pancake desserts (though I'm
                    sure they
                    have some somewhere). The sheer amount of variety on the menu can be challenging for first timers
                    but most
                    dishes are pretty well done.
                </h5>
            </div>
            <div class="branch half">
                <s:url action="Branch" var="branchLink">
                    <s:param name="branchNum">1</s:param>
                </s:url>
                <h3>
                    <a href="${branchLink}">Branch one</a>
                </h3>
                <h5>
                    The hilariously misnamed Pancake Dessert House in Chinatown's Midcity Arcade specializes in
                    fantastic Hong
                    Kong cuisine, in fact it seems to serve up almost everything...except pancake desserts (though I'm
                    sure they
                    have some somewhere). The sheer amount of variety on the menu can be challenging for first timers
                    but most
                    dishes are pretty well done.
                </h5>
            </div>

            <br class="clear-left"/>
        </div>

        <div class="container" id="personal">
            <h3>Your public information</h3>
            <section class="personal-info flex-container-large" id="public-info">
                <div class="avatar-wrapper none">
                    <img id="avatar" class="resize" src="../images/user.png" alt="a user">

                    <div id="upload-file-container">
                        <form enctype="multipart/form-data" id="avatar-form">
                            <input type="file" name="photo" title="choose a picture"
                                   onchange="checkFileAndUpload(this)">
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
                    <h4> Your card
                    </h4>
                    <ul id="roles">
                        <li>silver</li>
                    </ul>

                    <div class="apply-container" id="apply-container">
                        <a href="#" id="apply-link"> Apply for higher rank?
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

                    <%--<s:radio list="gender"/>--%>

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
                    <br>
                    <s:textfield label="BackCard Number" readonly="true"/>

                </div>

            </section>
            <br>
        </div>

    </div>
</div>
</body>

<%@include file="../html/footer.html" %>


<script type="application/javascript" src="../scripts/chosen.js"></script>
<script type="application/javascript" src="../scripts/Chart.js-2.0-dev/Chart.js"></script>
<script type="application/javascript" src="../scripts/useLineChart.js"></script>
<script type="application/javascript" src="../scripts/jquery/dist/jquery.min.js"></script>


</html>

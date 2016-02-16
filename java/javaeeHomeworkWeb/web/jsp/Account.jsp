<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%--
  Created by IntelliJ IDEA.
  User: zzt
  Date: 2/13/16
  Time: 11:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>account</title>

    <link rel="stylesheet" href="../styles/account-content.css">
    <link rel="stylesheet" href="../styles/main-header.css">


    <link rel="stylesheet" href="../fonts/font-awesome-4.4.0/css/font-awesome.min.css"/>

</head>
<body onload="
addListChosenListener('side_nav_list', 'tabbed-block');
">


<header>
    <section id="main-header">
        <img src="../images/yellow-pin.png" id="logo">

        <p id="app-name"><a href="Branches.jsp">Dessert</a></p>

        <form>
            <p class="action">
                <a href="<s:url action='Login_input'/>" class="fa fa-user"> log out</a>
            </p>

            <p class="action">
                <a href="<s:url action='Branches'/> " class="fa fa-home"> home</a>
            </p>
        </form>
        <br>
    </section>
</header>

<div id="main-container" class="flex-container-large">
    <nav id="side_nav" class="none">
        <ul id="side_nav_list">
            <li>
                <a href="#">Personal </a>
            </li>
            <li>
                <a href="#">Consumption </a>
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

        <div class="container" id="consumption">
            <h3>Your payment history</h3>

            <s:url action="Account" var="accountLink">
                <s:param name="now">1</s:param>
            </s:url>
            <%--size="32" partialList="true"--%>
            <display:table id="studentTable" name="students" pagesize="5" cellpadding="5px;"
                           cellspacing="5px;" style="margin-left:50px;margin-top:20px;"
                           requestURI="${accountLink}">
                <display:column property="rollNo" title="Roll No"/>
                <display:column property="studentName" title="Student Name"/>
                <display:column property="department" title="Department"/>
                <display:column property="rank" title="Rank"/>
            </display:table>
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

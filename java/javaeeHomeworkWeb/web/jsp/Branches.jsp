<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zzt
  Date: 2/13/16
  Time: 11:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page session="true" %>


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
        </ul>
    </nav>

    <div id="tabbed-block" class="flex1">

        <div class="container">

            <c:forEach items="${branches}" var="branch">
                <div class="branch half">
                    <s:url action="Branch" var="branchLink">
                        <s:param name="branchNum">${branch.bid}</s:param>
                    </s:url>
                    <h3>
                        <a href="${branchLink}">${branch.addr}</a>
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
            </c:forEach>

            <br class="clear-left"/>
        </div>

    </div>
</div>
</body>

<%@include file="../html/footer.html" %>


<script type="application/javascript" src="../scripts/chosen.js"></script>
<script type="application/javascript" src="../scripts/Chart.js-2.0-dev/Chart.js"></script>
<script type="application/javascript" src="../scripts/jquery/dist/jquery.min.js"></script>


</html>

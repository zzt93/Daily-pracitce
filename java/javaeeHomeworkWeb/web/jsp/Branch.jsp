<%--
  Created by IntelliJ IDEA.
  User: zzt
  Date: 2/14/16
  Time: 11:57 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>

<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="../styles/sign-head.css" type="text/css" rel="stylesheet">
    <link href="../styles/sign-content.css" type="text/css" rel="stylesheet">
    <link href="../styles/anchor.css" type="text/css" rel="stylesheet">

    <link rel="stylesheet" href="../fonts/font-awesome-4.4.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="../styles/main-header.css">
    <link rel="stylesheet" href="../styles/branches.css">
    <link rel="stylesheet" href="../styles/util.css">
    <link rel="stylesheet" href="../styles/dessert.css">


    <title>Branch ${branchNum}</title>
    <s:head/>
</head>
<body onload="useToolTip('#register')">

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

<div id="headline">
    <div class="branch-container ">

        <header>
            <h1>Dessert house</h1>

            <h3>Branch ${branchNum}</h3>

        </header>
        <!-- Elements after a floating element will flow around it.
         To avoid this, use the clear property.
        The clear property specifies on which sides of an element
         floating elements are not allowed to float:-->
    </div>
</div>

<div id="example">
    <div class="branch-container flex-container">

        <nav class="flex1 right-split">
            <h5>Phone Number:</h5>
            <h3>025-83593186</h3>
            <h5>Address:</h5>
            <h3>NanJing, NJU</h3>

            <a class="dessert-div" href="http://cn.bing.com/ditu/?FORM=Z9LH4#JnE9LiUyNXU1MzU3JTI1dTRlYWMlMjV1NTkyNyUyNXU1YjY2JTI1dTlmMTMlMjV1Njk3YyU3ZXNzdC4wJTdlcGcuMSZiYj0zMi4wNTkwOTEwNTM4NTk2JTdlMTE4Ljc5MDQ1NzA3NDg4NiU3ZTMyLjA0OTg4ODc3ODA5NTMlN2UxMTguNzY4Njc3NTM3Njg1">
                <img class="map" src="../images/map.png">
            </a>
        </nav>

        <div class="flex2">
            <h3>Orders</h3>
            <h3>Photos</h3>
            <div>
                <c:forEach items="${plan.details}" var="detail">
                    <div class="dessert-div">
                        <img class="dessert" src="../images/${detail.did}.jpg">
                    </div>
                </c:forEach>

                <div class="dessert-div">
                    <img class="dessert" src="../images/1.jpg">
                </div>
                <div class="dessert-div">
                    <img class="dessert" src="../images/2.jpg">
                </div>
                <div class="dessert-div">
                    <img class="dessert" src="../images/3.jpg">
                </div>
            </div>
        </div>

    </div>
</div>


<%@include file="../html/footer.html" %>


<script type="application/javascript" src="../scripts/jquery/dist/jquery.min.js"></script>
<script type="application/javascript" src="../scripts/jquery/jquery.tools.min.js"></script>
<script type="application/javascript" src="../scripts/formToolTip.js"></script>


</body>
</html>


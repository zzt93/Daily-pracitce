<%--
  Created by IntelliJ IDEA.
  User: zzt
  Date: 2/12/16
  Time: 10:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page session="false" %>

<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!--<link href="styles/fonts.css" type="text/css" rel="stylesheet">-->
    <link href="../styles/sign-head.css" type="text/css" rel="stylesheet">
    <link href="../styles/sign-footer.css" type="text/css" rel="stylesheet">
    <link href="../styles/sign-content.css" type="text/css" rel="stylesheet">
    <link href="../styles/anchor.css" type="text/css" rel="stylesheet">
    <link href="../styles/tooltip.css" type="text/css" rel="stylesheet">


    <title>Login</title>
    <s:head/>
</head>
<body onload="useToolTip('#register')">

<div id="headline">
    <div class="container">

        <header>
            <h1>Dessert house</h1>

        </header>
        <s:form action="Login_execute" id="register">

            <h2 class="inline-h2">Log in </h2>
            <h2 class="smaller-font inline-h2"> | try <a href="<s:url action='Register_input'/> " class="on-logpanel">
                sign up</a></h2>
            <%--The most common use of the property tag is used to "get" the value returned
            by calling a public get method (of the Action class) and
            then to include that value in the HTML returned to the browser.--%>
            <s:textfield name="name" label="user name" required="required"/>
            <s:password name="pw" label="password" required="required"/>
            <s:submit value="submit"/>
        </s:form>
        <!-- Elements after a floating element will flow around it.
         To avoid this, use the clear property.
        The clear property specifies on which sides of an element
         floating elements are not allowed to float:-->
        <br>
    </div>
</div>

<div id="example">
    <div class="container">
        <h2>Our Users</h2>

        <p>some comments and app screen shots</p>

        <div class="avatar">
            <img src="../images/user.png" alt="Li is diving">

            <p class="quote">
                Lee:<br>
                "I love their pancake so much"
            </p>
        </div>
        <ul>
            <li>Li's comment and health statistic</li>
            <li>Hong's comment and statistic analysis</li>
        </ul>
    </div>
</div>


<%@include file="../html/footer.html" %>


<script type="application/javascript" src="../scripts/jquery/dist/jquery.min.js"></script>
<script type="application/javascript" src="../scripts/jquery/jquery.tools.min.js"></script>
<script type="application/javascript" src="../scripts/formToolTip.js"></script>


</body>
</html>

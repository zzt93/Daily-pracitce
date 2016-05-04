<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: zzt
  Date: 2/14/16
  Time: 3:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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


    <title>Register</title>

    <s:head/>
</head>
<body onload="useToolTip('#register')">

<div id="headline">
    <div class="container">

        <header>
            <h1>Dessert house</h1>

        </header>
        <h3>Register by completing this form.</h3>

        <s:form action="Register_" id="register">
            <h2 class="inline-h2">Register </h2>
            <h2 class="smaller-font inline-h2"> | try <a href="<s:url action='Login_input'/> " class="on-logpanel">
                Log in</a></h2>
            <s:textfield name="name" label="user name" title="how to name you in website"
                         required="required"/>
            <s:password name="pw" label="password" pattern=".{6,}" title="at least 6 chars" required="required"/>

            <s:password name="pw2" label="confirm password" pattern=".{6,}" title="input it again" required="required"/>

            <s:submit/>

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
                "I follow the advice of my fitness to dive,
                and I feel so good now."
            </p>
        </div>
        <div class="avatar">
            <img src="../images/user.png" alt="Li is diving">
            <p class="quote">
                Lee:<br>
                "I follow the advice of my fitness to dive,
                and I feel so good now."
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

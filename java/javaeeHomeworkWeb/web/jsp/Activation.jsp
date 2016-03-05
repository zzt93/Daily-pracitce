<%--
  Created by IntelliJ IDEA.
  User: zzt
  Date: 2/23/16
  Time: 3:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: zzt
  Date: 2/14/16
  Time: 3:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

    <title>Activation</title>


    <s:head/>
</head>
<body onload="useToolTip('#register')">

<div id="headline">
    <div class="container">

        <header>
            <h1>Dessert house</h1>

        </header>
        <h3>Activate your account by completing this form.</h3>

        <s:form action="Activation_" id="register">
            <h2 class="inline-h2">Activate </h2>


            <s:textfield name="money" label="money"
                         required="required"/>
            <s:textfield name="bankCard" label="bank card" pattern="\d{19}" title="bank card number must 19 number"
                         required="required"/>

            <s:submit value="activate by cash"/>
            <s:submit value="activate by card"/>

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
    </div>
</div>


<%@include file="../html/footer.html" %>


<script type="application/javascript" src="../scripts/jquery/dist/jquery.min.js"></script>
<script type="application/javascript" src="../scripts/jquery/jquery.tools.min.js"></script>
<script type="application/javascript" src="../scripts/formToolTip.js"></script>


</body>
</html>

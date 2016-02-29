<%--
  Created by IntelliJ IDEA.
  User: zzt
  Date: 2/19/16
  Time: 3:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
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


    <title>Staff Register</title>

    <s:head/>
</head>
<body>

<div id="headline">
    <div class="container">

        <header>
            <h1>Dessert house inner register</h1>

        </header>
        <h3>Register by completing this form.</h3>

        <%--use wrong action name also casue error about `set xxx ['xxx', ]`
        e.g. use `InnerRegster`
        --%>
        <s:form action="InnerRegister_" id="register">
            <h2 class="inline-h2">Register </h2>

            <s:password name="adminPW" label="admin password" required="required"/>
            <s:select label="Choose type"
                      name="type"
                      list="%{types}" required="required"/>
            <%--<s:radio list="types" required="required"/>--%>
            <s:textfield name="sid" label="staff id" readonly="true"/>
            <s:textfield name="bid" label="branch id" title="how to name you in website"/>
            <s:password name="pw" label="password" title="have to longer than 6 chars" required="required"/>

            <s:password name="pw2" label="confirm password" title="input it again" required="required"/>

            <s:submit/>

        </s:form>

        <!-- Elements after a floating element will flow around it.
         To avoid this, use the clear property.
        The clear property specifies on which sides of an element
         floating elements are not allowed to float:-->
        <br>
    </div>
</div>


</body>
</html>


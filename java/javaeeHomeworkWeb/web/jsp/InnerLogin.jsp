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


    <title>Staff Login</title>
    <s:head/>
</head>
<body>

<div id="headline">
    <div class="container">

        <header>
            <h1>Staff login </h1>

        </header>
        <s:form action="InnerLogin"  id="register">

            <h2 class="inline-h2">Log in </h2>
            <%--The most common use of the property tag is used to "get" the value returned
            by calling a public get method (of the Action class) and
            then to include that value in the HTML returned to the browser.--%>
            <s:select label="Choose type"
                      name="type"
                      list="%{types}" required="required"/>
            <s:textfield name="sid" label="working id" required="required"/>
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


<%@include file="../html/footer.html" %>



</body>
</html>

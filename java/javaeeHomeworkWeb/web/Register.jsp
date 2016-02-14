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
    <s:head />
    <title>Register</title>
</head>
<body>
<h3>Register by completing this form.</h3>

<s:form action="Register">

    <s:textfield name="name" label="user name" required="required"/>
    <s:textfield name="pw" label="password" required="required"/>
    <s:textfield name="pw2" label="confirm password" required="required"/>

    <s:submit/>

</s:form>

</body>
</html>

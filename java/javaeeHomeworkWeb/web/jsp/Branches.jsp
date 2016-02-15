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

<html>
<head>
    <title>Branches</title>
    <link href="../styles/branches.css" type="text/css" rel="stylesheet">
</head>
<body>
<div class="container flex-container">
    <div class="flex1 branch">
        <h3>
            <s:url action="Branch">
                <s:param name="branchName">Bruce Phillips</s:param>
            </s:url>
        </h3>
    </div>
</div>
</body>
</html>

<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %><%--
  Created by IntelliJ IDEA.
  User: zzt
  Date: 12/10/15
  Time: 2:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         session="false" %>

<html>
<head>
    <title>index</title>
</head>
<body>
hello
<br>
<%
    out.print("<p><b>Hello World!</b>");
    Map<String, String[]> parameterMap = request.getParameterMap();
    Set<String> parameters = request.getParameterMap().keySet();
    for (String s : parameters) {
%>
<%=(s + "->" + Arrays.toString(parameterMap.get(s)))%>
<%
    }

%>
<br>
<hr>

<%
    for (String parameter : parameters) {
        out.print(parameter + "->" + Arrays.toString(parameterMap.get(parameter)));
    }
%>
<h3><%= new Date()%>
</h3>

<%--static include--%>
<%@include file="html/footer.html" %>
<%--dynamic include: dispatcher.include--%>
<jsp:include page="html/footer.html"/>
</body>
</html>

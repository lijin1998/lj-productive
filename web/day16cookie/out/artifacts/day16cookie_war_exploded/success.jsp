<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 2020-05-24
  Time: 21:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1><%=request.getSession().getAttribute("user") %>,欢迎你！</h1>
</body>
</html>

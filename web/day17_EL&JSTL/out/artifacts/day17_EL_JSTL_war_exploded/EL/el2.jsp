<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 2020-05-25
  Time: 12:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>el获取域中的数据</title>
</head>
<body>
<%
    //在域中存储数据
    request.setAttribute("name","战士");
    request.setAttribute("str","");
    session.setAttribute("name","李四");
    session.setAttribute("age","23");
%>

<h3>el获取值</h3>>
${requestScope.name}
${sessionScope.age}
${pageScope.s}
${applicationScope.s}


</body>
</html>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 2020-05-25
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${3>4}
<h3>算数运算符</h3>
${3 + 4}<br>
${3 / 4}<br>
${3 div 4}<br>
${3 * 4}<br>
${3 mod 4}<br>
<h3>比较运算符</h3>
${3 == 4}<br>
<h3>逻辑运算符</h3>
${3 > 4  && 3 < 4}<br>
${3 > 4  and 3 < 4}<br>
<h4>empty运算符</h4>
<%
    String s = "";
    request.setAttribute("s",s);

    List list = new ArrayList();
    request.setAttribute("list",list);
%>

${not empty s}
${not empty list}
\${3>4}
</body>
</html>

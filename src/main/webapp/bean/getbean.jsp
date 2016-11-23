<%--
  Created by IntelliJ IDEA.
  User: kpfromer
  Date: 11/17/16
  Time: 10:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Get Bean</title>
</head>
<body>

<jsp:useBean id="user" class="com.kylepfromer.beans.User" scope="session"></jsp:useBean>
<jsp:getProperty name="user" property="password"></jsp:getProperty>
<jsp:getProperty name="user" property="email"></jsp:getProperty>

</body>
</html>

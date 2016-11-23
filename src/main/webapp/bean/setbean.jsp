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
    <title>Setting Bean</title>
</head>
<body>

<jsp:useBean id="user" class="com.kylepfromer.beans.User" scope="session"></jsp:useBean>
<jsp:setProperty name="user" property="email" value="kpfromer2@gmail.com"></jsp:setProperty>
<jsp:setProperty name="user" property="password" value="kpfromer"></jsp:setProperty>

</body>
</html>

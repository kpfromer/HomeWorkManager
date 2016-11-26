<%--
  Created by IntelliJ IDEA.
  User: kpfromer
  Date: 11/16/16
  Time: 1:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
login
<form action="/?action=login" method="post">
    <div>
        <label for="username">Username:</label>
        <input type="text" id="username" name="user_name"/>
    </div>
    <div>
        <label for="password">Password:</label>
        <input type="password" id="password" name="user_password"/>
    </div>
    <div class="button">
        <button type="submit" value="Submit">Sumbit</button>
    </div>
</form>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: kpfromer
  Date: 11/17/16
  Time: 12:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Signup</title>
</head>
<body>
<%--<%--%>
<%--HttpSession sessionExist = request.getSession(false);--%>
<%--sessionExist.setAttribute("user", "dakd;sadk;");--%>
<%--if (sessionExist != null || sessionExist.equals(request.getSession(true))){--%>
<%--out.println("dajsmflkdsjglksdjglksdjfglksd");--%>
<%--out.println(sessionExist.equals(request.getSession(true)));--%>
<%--} else {--%>
<%--out.print("!!!!!!!!!!!!!!!!!!!");--%>
<%--}--%>
<%--%>--%>
<form action="/?action=signup" method="post">
    <div>
        <label for="username">Username:</label>
        <input type="text" id="username" name="user_name" />
    </div>
    <div>
        <label for="mail">E-mail:</label>
        <input type="email" id="mail" name="user_mail" />
    </div>
    <div>
        <label for="password">Password:</label>
        <input type="password" id="password" name="user_password" />
    </div>
    <div class="button">
        <button type="submit" value="Submit">Sumbit</button>
    </div>
</form>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: kpfromer
  Date: 11/30/16
  Time: 9:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--todo: Make better todo system, no form all on one page--%>
<form method="post" action="/?action=createTask">
    <div>
        <label for="task">Task:</label>
        <input type="text" id="task" name="task_text"/>
    </div>
    <div class="button">
        <button type="submit" value="Submit">Sumbit</button>
    </div>
</form>
</body>
</html>

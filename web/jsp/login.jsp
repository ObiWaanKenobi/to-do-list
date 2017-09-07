<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <c:import url="headIncludes.jsp"/>
</head>
<body>
<c:import url="error.jsp"/>

<form action="/login" method="post">
    <label for="loginField">Login:</label>
    <br>
    <input type="text" name="login" id="loginField">
    <br>
    <label for="passwordField">Password:</label>
    <br>
    <input type="password" name="password" id="passwordField">
    <br><br>
    <input type="submit" value="Log In">
</form>

<c:import url="footer.jsp"/>

</body>
</html>

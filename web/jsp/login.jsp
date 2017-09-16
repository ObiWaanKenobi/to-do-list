<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <c:import url="headIncludes.jsp"/>
</head>
<body>
<div class="container">
    <c:import url="error.jsp"/>
    <div class="row">
        <div class="col-sm-4">
            <form action="/login" method="post">
                <div class="form-group">
                    <label for="loginField">Login:</label>
                    <input name="login" placeholder="Login" id="loginField" class="form-control">
                </div>
                <div class="form-group">
                    <label for="passwordField">Password:</label>
                    <input type="password" name="password" placeholder="Password" id="passwordField" class="form-control">
                </div>
                <input type="submit" value="Log In">
            </form>
        </div>
    </div>
    <c:import url="footer.jsp"/>
</div>
</body>
</html>

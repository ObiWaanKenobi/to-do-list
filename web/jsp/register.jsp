<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <c:import url="headIncludes.jsp"/>
</head>
<body>
<div class="container">
    <c:import url="error.jsp" />
    <div class="row">
        <div class="col-sm-4">
            <form action="/register" method="post">
                <div class="form-group">
                    <label for="loginField">Login:</label>
                    <input name="login" placeholder="Login" id="loginField" class="form-control">
                </div>
                <div class="form-group">
                    <label for="passwordField">Password:</label>
                    <input type="password" name="password" placeholder="Password" id="passwordField" class="form-control">
                </div>
                <div class="form-group">
                    <label for="passConfField">Confirm your password:</label>
                    <input type="password" name="confirmPassword" placeholder="Repeat password" id="passConfField" class="form-control">
                </div>
                <button class="btn btn-success">Sign Up</button>
            </form>
        </div>
    </div>
        <c:import url="footer.jsp" />
</div>
</body>
</html>

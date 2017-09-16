<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<p>User:&nbsp
    <c:choose>
        <c:when test="${not empty user}">
            <span>${user.login}</span>&nbsp&nbsp
            <button formmethod="post" onclick="location.href='/logout'">Log Out</button>
        </c:when>
        <c:otherwise>
            <span class="bold">guest</span>&nbsp&nbsp
            <button formmethod="post" onclick="location.href='/jsp/login.jsp'">Login</button>
            <button formmethod="post" onclick="location.href='/jsp/registrate.jsp'">Registrate</button>
        </c:otherwise>
    </c:choose>
</p>
<hr>

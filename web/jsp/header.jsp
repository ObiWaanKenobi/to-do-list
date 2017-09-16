<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<p>
    <c:choose>
        <c:when test="${not empty user}">
            <span>User: ${user.login}</span>&nbsp&nbsp
            <button formmethod="post" onclick="location.href='/logout'">Log Out</button>
        </c:when>
        <c:otherwise>
            <ul class="nav nav-pills">
                <li class="nav-item">
                    <a class="nav-link active" href="/jsp/login.jsp">Login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/jsp/register.jsp">Register</a>
                </li>
            </ul>
        </c:otherwise>
    </c:choose>
</p>
<hr>

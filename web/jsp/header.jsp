<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<p>User:&nbsp
    <c:choose>
        <c:when test="${not empty user}">
          <span>${user.login}</span>&nbsp&nbsp
             <input type="button" value="Log Out" formmethod="post" onclick='location.href="/logout"'>
        </c:when>
        <c:otherwise>
            <span class="bold">guest</span>&nbsp&nbsp
                <input type="button" value="Login" onClick='location.href="/jsp/login.jsp"'>
                <input type="button" value="Registrate" onClick='location.href="/jsp/register.jsp"'>
        </c:otherwise>
    </c:choose>
</p>
<hr>

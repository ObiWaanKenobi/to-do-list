<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add Task</title>
    <c:import url="headIncludes.jsp"/>
</head>
<body>
<div class="container">
    <c:import url="error.jsp"/>

    <div class="row section-row">
        <div class="col-sm-4">
            <form method="post" action="/addtask" onsubmit="setDate('${sessionScope.get('taskType')}')">
                <input type="hidden" name="taskDate" id="taskDate">
                <div class="form-group">
                    <label for="nameId">Enter task name:</label>
                    <input id="nameId" name="taskName" placeholder="Task Name" class="form-control">
                </div>
                <c:if test="${sessionScope.get('taskType').equals('SOMEDAY')}">
                    <div class="form-group">
                        <label for="customDate">Enter task date:</label>
                        <input type="date" id="customDate" name="customDate" placeholder="Task Date" class="form-control">
                    </div>
                </c:if>
                <button class="btn btn-primary">Add Task</button>
            </form>

        </div>
    </div>

    <div class="rov section-row">
        <div class="col">
            <c:import url="footer.jsp"/>
        </div>
    </div>

</div>
</body>
</html>

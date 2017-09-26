<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <title>To-Do List</title>
    <c:import url="headIncludes.jsp"/>
</head>
<body>
<div class="container">
    <input type="hidden" id="currentTaskType" value="${sessionScope.get("taskType")}">

    <div class="row section-row">
        <div class="col">
            <c:import url="header.jsp"/>
            <c:import url="error.jsp"/>
        </div>
    </div>


    <div class="row section-row">
        <div class="col">
            <div id="taskTypeSwitcher" class="btn-group">
                <button class="btn btn-success" value="TODAY" onclick="loadTasks('TODAY')">Today</button>
                <button class="btn btn-success" value="TOMORROW" onclick="loadTasks('TOMORROW')">Tomorrow</button>
                <button class="btn btn-success" value="SOMEDAY" onclick="loadTasks('SOMEDAY')">Someday</button>
                <button class="btn btn-success" value="FIXED" onclick="loadTasks('FIXED')">Fixed</button>
                <button class="btn btn-success" value="RECYCLE_BIN" onclick="loadTasks('RECYCLE_BIN')">Recycle Bin
                </button>
            </div>
        </div>
    </div>

    <div class="row section-row">
        <div class="col tasks-table">
            <table id="tasksTable" class="table table-striped"></table>
        </div>
    </div>

    <div class="row section-row">
        <div class="col">
            <c:import url="taskActions.jsp"/>
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




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
            <form method="post" action="/showtasks">
                <div id="taskTypeSwitcher" class="btn-group">
                    <button class="btn btn-success" name="taskType" value="TODAY">Today</button>
                    <button class="btn btn-success" name="taskType" value="TOMORROW">Tomorrow</button>
                    <button class="btn btn-success" name="taskType" value="SOMEDAY">Someday</button>
                    <button class="btn btn-success" name="taskType" value="FIXED">Fixed</button>
                    <button class="btn btn-success" name="taskType" value="RECYCLE_BIN">Recycle Bin</button>
                </div>
            </form>
        </div>
    </div>

    <div class="row section-row">
        <div class="col tasks-table">
            <table id="tasksTable" class="table table-striped">
                <tbody>
                <tr>
                    <th><input type="checkbox" id="select-all" title="Select All"></th>
                    <th>Task Name</th>
                    <c:if test="${sessionScope.get('taskType').matches('SOMEDAY')}">
                        <th>Task Date</th>
                    </c:if>
                    <th colspan="2">Task File</th>
                </tr>
                <c:forEach var="task" items="${tasks}">
                    <tr>
                        <td class="check-box-cell">
                            <input type="checkbox" name="checkedTask" value="${task.taskId}">
                        </td>
                        <td class="text-cell">
                            <span><c:out value="${task.taskName}"/></span>
                        </td>
                        <c:if test="${sessionScope.get('taskType').matches('SOMEDAY')}">
                            <td id="taskDate" class="text-cell">
                                <span><c:out value="${task.taskDate}"/></span>
                            </td>
                        </c:if>
                        <td>
                            <c:choose>
                            <c:when test="${empty task.fileName}">
                                <form method="post" action="/uploadFile" enctype="multipart/form-data">
                                    <div class="row">
                                        <div class="col">
                                            <label class="custom-file">
                                                <input data-toggle="custom-file"
                                                       data-target="#upload-file-${task.taskId}"
                                                       type="file" name="file" class="custom-file-input">
                                                <span id="upload-file-${task.taskId}"
                                                      class="custom-file-control custom-file-name"
                                                      data-content="Choose file..."></span>
                                            </label>
                                        </div>
                                        <div class="col">
                                            <button class="btn btn-secondary" name="taskId" value="${task.taskId}">Upload File</button>
                                        </div>
                                    </div>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <div class="row">
                                    <div class="col"><span><a
                                            href="/downloadFile?fileName=${task.fileName}">${task.shortFileName}</a></span>
                                    </div>
                                    <div class="col">
                                        <form method="post" action="/deleteFile">
                                            <button class="btn btn-danger" name="fileName" value="${task.fileName}">Delete
                                                File
                                            </button>
                                        </form>
                                    </div>
                                </div>
                            </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
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




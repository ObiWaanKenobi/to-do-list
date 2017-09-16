<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${sessionScope.get('taskType').matches('TODAY|TOMORROW|SOMEDAY')}">
    <form class="form-inline" method="post" action="/jsp/addTask.jsp">
        <div class="btn-group">
            <button class="btn btn-primary">Add Task</button>
            <button class="btn btn-primary" type="button" value="MOVE_TO_FIXED" onclick="updateTasks('movetask', 'moveType=' + this.value)">Move To Fixed</button>
            <button class="btn btn-primary" type="button" value="MOVE_TO_BIN" onclick="updateTasks('movetask', 'moveType=' + this.value)">Move To Bin</button>
        </div>
    </form>
</c:if>

<c:if test="${sessionScope.get('taskType').equals('FIXED')}">
    <div class="btn-group">
        <button class="btn btn-primary" value="MOVE_TO_BIN" onclick="updateTasks('movetask', 'moveType=' + this.value)">Move To Bin</button>
        <button class="btn btn-primary" value="RESTORE_FIXED" onclick="updateTasks('movetask', 'moveType=' + this.value)">Restore Task</button>
    </div>
</c:if>

<c:if test="${sessionScope.get('taskType').equals('RECYCLE_BIN')}">
    <div class="btn-group">
        <button class="btn btn-primary" value="RESTORE" onclick="updateTasks('movetask', 'moveType=' + this.value)">Restore Task</button>
        <button class="btn btn-danger" onclick="updateTasks('deleteTask')">Delete Task</button>
    </div>
</c:if>

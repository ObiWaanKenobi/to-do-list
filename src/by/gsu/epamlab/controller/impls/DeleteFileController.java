package by.gsu.epamlab.controller.impls;

import by.gsu.epamlab.controller.ifaces.AbstractController;
import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.exceptions.TaskDaoException;
import by.gsu.epamlab.model.services.taskservice.ITaskService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static by.gsu.epamlab.controller.constants.Constants.*;

public class DeleteFileController extends AbstractController {
    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String taskId = request.getParameter("taskId");
        ITaskService iTaskService = (ITaskService) getServletContext().getAttribute(ITASK_SERVICE);
        String taskType = String.valueOf(request.getSession().getAttribute(TASK_TYPE));
        String userId = String.valueOf(request.getSession().getAttribute(USER_ID));

        try {
            iTaskService.deleteTaskFile(taskId);
            List<Task> tasks = iTaskService.getTasks(taskType, userId);
            request.getSession().setAttribute(TASKS, tasks);
        } catch (TaskDaoException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/jsp/tasks.jsp");

    }
}

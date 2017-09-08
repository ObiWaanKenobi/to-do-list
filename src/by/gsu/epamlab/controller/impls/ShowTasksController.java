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

public class ShowTasksController extends AbstractController {
    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String taskType = request.getParameter(TASK_TYPE);
        ITaskService iTaskService = (ITaskService) getServletContext().getAttribute(ITASK_SERVICE);
        String userId = String.valueOf(request.getSession().getAttribute(USER_ID));

        if (taskType != null) {
            try {
                List<Task> tasks = null;
                tasks = iTaskService.getTasks(taskType, userId);

                request.getSession().setAttribute(TASK_TYPE, taskType);
                request.getSession().setAttribute(TASKS, tasks);
                jumpForward(TASKS_PAGE, request, response);
            } catch (TaskDaoException e) {
                e.printStackTrace();
            }
        }
        //todo rework
        else {
            jumpForward(TASKS_PAGE, request, response);
        }

    }
}

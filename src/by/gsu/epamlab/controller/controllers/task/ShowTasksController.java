package by.gsu.epamlab.controller.controllers.task;

import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.exceptions.TaskDaoException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static by.gsu.epamlab.controller.constants.Constants.*;

@WebServlet(name = "ShowTaskController", urlPatterns = "/showtasks")
public class ShowTasksController extends AbstractTaskController {
    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String taskType = request.getParameter(TASK_TYPE);
        String userId = String.valueOf(session.getAttribute(USER_ID));

        if (taskType != null) {
            try {
                List<Task> taskList = iTaskService.getTasks(taskType, userId);
                session.setAttribute(TASK_TYPE, taskType);
                session.setAttribute(TASKS, taskList);
                jumpForward(TASKS_PAGE, request, response);
            } catch (TaskDaoException e) {
                jumpError(TASKS_PAGE, e.getMessage(), request, response);
            }
        }
        else {
            jumpForward(TASKS_PAGE, request, response);
        }
    }
}

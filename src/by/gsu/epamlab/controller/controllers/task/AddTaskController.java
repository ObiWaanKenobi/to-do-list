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

@WebServlet(name = "AddTaskController", urlPatterns = "/addTask")
public class AddTaskController extends AbstractTaskController {
    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String taskName = request.getParameter(TASK_NAME);
        String taskDate = request.getParameter(TASK_DATE);
        String taskType = String.valueOf(session.getAttribute(TASK_TYPE));
        System.out.println(taskType);
        String userId = String.valueOf(session.getAttribute(USER_ID));

        try {
            iTaskService.addTask(taskName, taskDate, userId);
            List<Task> tasks = iTaskService.getTasks(taskType, userId);
            session.setAttribute(TASKS, tasks);
            jumpRedirect(TASKS_PAGE, response);
        } catch (TaskDaoException e) {
            jumpError(ADDTASK_PAGE, e.getMessage(), request, response);
        }
    }
}

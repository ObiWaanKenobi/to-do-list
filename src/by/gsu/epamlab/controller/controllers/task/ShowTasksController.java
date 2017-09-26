package by.gsu.epamlab.controller.controllers.task;

import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.exceptions.TaskDaoException;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static by.gsu.epamlab.controller.constants.Constants.*;

@WebServlet(name = "ShowTaskController", urlPatterns = "/showtasks")
public class ShowTasksController extends AbstractTaskController {
    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String taskType = request.getParameter(TASK_TYPE);
        String userId = String.valueOf(session.getAttribute(USER_ID));

        if (taskType != null) {
            try {
                session.setAttribute(TASK_TYPE, taskType);
                List<Task> taskList = iTaskService.getTasks(taskType, userId);
                String tasks = new Gson().toJson(taskList);
                out.write(tasks);
            } catch (TaskDaoException e) {
                jumpError(TASKS_PAGE, e.getMessage(), request, response);
            }
        }
        else {
            jumpForward(TASKS_PAGE, request, response);
        }
    }
}

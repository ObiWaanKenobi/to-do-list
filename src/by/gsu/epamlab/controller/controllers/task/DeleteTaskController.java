package by.gsu.epamlab.controller.controllers.task;

import by.gsu.epamlab.model.exceptions.TaskDaoException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.gsu.epamlab.controller.constants.Constants.CHECKED_TASK;
import static by.gsu.epamlab.controller.constants.Constants.TASKS_PAGE;

@WebServlet(name = "DeleteTaskController", urlPatterns = "/deleteTask")
public class DeleteTaskController extends AbstractTaskController {

    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] checkedTasks = request.getParameterValues(CHECKED_TASK);
        try {
            iTaskService.deleteTask(checkedTasks);
        } catch (TaskDaoException e) {
            jumpError(TASKS_PAGE, e.getMessage(), request, response);
        }
    }
}

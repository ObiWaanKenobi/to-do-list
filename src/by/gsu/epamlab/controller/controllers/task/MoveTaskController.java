package by.gsu.epamlab.controller.controllers.task;

import by.gsu.epamlab.model.exceptions.TaskDaoException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.gsu.epamlab.controller.constants.Constants.*;

@WebServlet(name = "MoveTaskController", urlPatterns = "/movetask")
public class MoveTaskController extends AbstractTaskController {

    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String moveType = request.getParameter(MOVE_TYPE);
        String[] checkedTasks = request.getParameterValues(CHECKED_TASK);

        try {
            iTaskService.moveTask(moveType, checkedTasks);
        } catch (TaskDaoException e) {
            jumpError(TASKS_PAGE, e.getMessage(), request, response);
        }
    }
}

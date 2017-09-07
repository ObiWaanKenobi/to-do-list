package by.gsu.epamlab.controller.impls;

import by.gsu.epamlab.controller.ifaces.AbstractController;
import by.gsu.epamlab.model.exceptions.TaskDaoException;
import by.gsu.epamlab.model.services.ITaskService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static by.gsu.epamlab.controller.constants.Constants.*;

public class MoveTaskController extends AbstractController {

    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String moveType = request.getParameter(MOVE_TYPE);
        String[] checkedTasks = request.getParameterValues(CHECKED_TASKS);

        ITaskService iTaskService = (ITaskService) getServletContext().getAttribute(ITASK_SERVICE);

        //todo exc
        try {
            iTaskService.moveTask(moveType, checkedTasks);
        } catch (TaskDaoException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

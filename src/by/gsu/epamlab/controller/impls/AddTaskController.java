package by.gsu.epamlab.controller.impls;

import by.gsu.epamlab.controller.ifaces.AbstractController;
import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.exceptions.TaskDaoException;
import by.gsu.epamlab.model.services.ITaskService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static by.gsu.epamlab.controller.constants.Constants.*;

public class AddTaskController extends AbstractController {
    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String taskName = request.getParameter(TASK_NAME);
        String taskDate = request.getParameter(TASK_DATE);
        String taskType = String.valueOf(session.getAttribute(TASK_TYPE));
        String userId = String.valueOf(request.getSession().getAttribute(USER_ID));


        //todo exc
        ITaskService iTaskService = (ITaskService) getServletContext().getAttribute(ITASK_SERVICE);
        try {
            iTaskService.addTask(taskName, taskDate, userId);
            List<Task> tasks = iTaskService.getTasks(taskType, userId);
            session.setAttribute(TASKS, tasks);
            jumpRedirect(TASKS_PAGE, response);
        } catch (TaskDaoException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

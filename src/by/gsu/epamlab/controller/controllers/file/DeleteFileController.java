package by.gsu.epamlab.controller.controllers.file;

import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.exceptions.TaskDaoException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static by.gsu.epamlab.controller.constants.Constants.*;

@WebServlet(name = "DeleteFileController", urlPatterns = "/deleteFile")
public class DeleteFileController extends AbstractFileController {

    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getParameter(FILE_NAME);
        String taskType = String.valueOf(request.getSession().getAttribute(TASK_TYPE));
        String userId = String.valueOf(request.getSession().getAttribute(USER_ID));

        try {
            File file = new File(filePath + fileName);
            iTaskService.deleteTaskFile(fileName);
            file.delete();
            List<Task> tasks = iTaskService.getTasks(taskType, userId);
            request.getSession().setAttribute(TASKS, tasks);
        } catch (TaskDaoException e) {
            jumpError(TASKS_PAGE, e.getMessage(), request, response);
        }
        response.sendRedirect(TASKS_PAGE);
    }
}

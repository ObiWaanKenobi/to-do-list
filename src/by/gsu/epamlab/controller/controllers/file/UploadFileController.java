package by.gsu.epamlab.controller.controllers.file;

import by.gsu.epamlab.model.beans.Task;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static by.gsu.epamlab.controller.constants.Constants.*;

@WebServlet(name = "UploadFileController", urlPatterns = "/uploadFile")
public class UploadFileController extends AbstractFileController {
    private ServletFileUpload upload;

    @Override
    public void init() throws ServletException {
        super.init();
        DiskFileItemFactory factory = new DiskFileItemFactory();
        File filesDir = (File) getServletContext().getAttribute(FILES_DIR_FILE);
        factory.setRepository(filesDir);
        upload = new ServletFileUpload(factory);

    }

    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String taskId = null;
        String fileName = null;
        String taskType = String.valueOf(request.getSession().getAttribute(TASK_TYPE));
        String userId = String.valueOf(request.getSession().getAttribute(USER_ID));

        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                List <FileItem> multiparts = upload.parseRequest(request);
                double hash = Math.random();
                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                        fileName = new File(item.getName()).getName();
                        if (!fileName.equals("")){
                            item.write( new File(filePath + hash + FILE_NAME_DELIMITER + fileName));
                        }
                    }else {
                        if (item.getFieldName().equals(TASK_ID)){
                            taskId = item.getString();
                        }
                    }
                }
                if (!fileName.equals("")) {
                    iTaskService.addTaskFile(hash + FILE_NAME_DELIMITER + fileName, taskId);
                    List<Task> tasks = iTaskService.getTasks(taskType, userId);
                    request.getSession().setAttribute(TASKS, tasks);
                }

            } catch (Exception ex) {
                jumpError(TASKS_PAGE, ex.getMessage(), request, response);
            }
            response.sendRedirect(TASKS_PAGE);
        }else {
            jumpForward(TASKS_PAGE, request, response);
        }
    }
}






package by.gsu.epamlab.controller.impls;

import by.gsu.epamlab.controller.ifaces.AbstractController;
import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.services.taskservice.ITaskService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import static by.gsu.epamlab.controller.constants.Constants.*;

public class UploadFileController extends AbstractController {
    private static final long serialVersionUID = 1L;
    private String filePath;
    private int maxFileSize = 1024 * 1024;
    private int maxMemSize = 4 * 1024;
    private File file;

    @Override
    public void init() throws ServletException {
        filePath = getServletContext().getInitParameter("file-upload");
    }

    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String taskId = null;
        String fileName = null;
        String filePath = getServletContext().getInitParameter("file-upload");
        ITaskService iTaskService = (ITaskService) getServletContext().getAttribute(ITASK_SERVICE);
        String taskType = String.valueOf(request.getSession().getAttribute(TASK_TYPE));
        String userId = String.valueOf(request.getSession().getAttribute(USER_ID));

        if (ServletFileUpload.isMultipartContent(request)) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(maxMemSize);
            factory.setRepository(new File("r:\\"));
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setSizeMax(maxFileSize);

            try {
                List<FileItem> fileItems = upload.parseRequest(request);
                Iterator i = fileItems.iterator();

                while (i.hasNext()) {
                    FileItem fi = (FileItem) i.next();
                    if (!fi.isFormField()) {
                        fileName = fi.getName();

                        if (fileName.lastIndexOf("\\") >= 0) {
                            file = new File(filePath +
                                    fileName.substring(fileName.lastIndexOf("\\")));
                        } else {
                            file = new File(filePath +
                                    fileName.substring(fileName.lastIndexOf("\\") + 1));
                        }
                        fi.write(file);
                    }
                    else {
                        if (fi.getFieldName().equals("taskId")){
                            taskId = fi.getString();
                        }
                    }
                }
                iTaskService.addTaskFile(fileName, taskId);
                List<Task> tasks = iTaskService.getTasks(taskType, userId);
                request.getSession().setAttribute(TASKS, tasks);

            } catch (Exception ex) {
                System.out.println(ex);
            }
            response.sendRedirect("/jsp/tasks.jsp");
        }
    }
}

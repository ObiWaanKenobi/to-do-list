package by.gsu.epamlab.controller.controllers.file;

import by.gsu.epamlab.controller.constants.Constants;
import by.gsu.epamlab.controller.controllers.task.AbstractTaskController;

import javax.servlet.ServletException;
import java.io.File;

public abstract class AbstractFileController extends AbstractTaskController {
    protected String filePath;

    @Override
    public void init() throws ServletException {
        super.init();
        filePath = String.valueOf(getServletContext().getAttribute(Constants.FILES_DIR)) + File.separator;
    }
}

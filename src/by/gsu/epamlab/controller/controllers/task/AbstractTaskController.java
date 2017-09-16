package by.gsu.epamlab.controller.controllers.task;

import by.gsu.epamlab.controller.controllers.AbstractController;
import by.gsu.epamlab.model.services.task.ITaskService;

import javax.servlet.ServletException;

import static by.gsu.epamlab.controller.constants.Constants.ITASK_SERVICE;

public abstract class AbstractTaskController extends AbstractController {
    protected ITaskService iTaskService;

    @Override
    public void init() throws ServletException {
        iTaskService = (ITaskService) getServletContext().getAttribute(ITASK_SERVICE);
    }
}

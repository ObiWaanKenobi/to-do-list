package by.gsu.epamlab.controller.controllers.user;

import by.gsu.epamlab.controller.controllers.AbstractController;
import by.gsu.epamlab.model.services.user.IUserService;

import javax.servlet.ServletException;

import static by.gsu.epamlab.controller.constants.Constants.IUSER_SERVICE;

public abstract class AbstractUserController extends AbstractController {
    protected IUserService iUserService;

    @Override
    public void init() throws ServletException {
        iUserService = (IUserService) getServletContext().getAttribute(IUSER_SERVICE);

    }
}

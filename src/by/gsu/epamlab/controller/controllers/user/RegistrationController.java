package by.gsu.epamlab.controller.controllers.user;

import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.exceptions.UserDaoException;
import by.gsu.epamlab.model.exceptions.ValidationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.gsu.epamlab.controller.constants.Constants.*;

@WebServlet(name = "RegistrationController", urlPatterns = "/register")
public class RegistrationController extends AbstractUserController {
    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String confirmPassword = request.getParameter(CONFIRM_PASSWORD);

        try {
            checkInput(login,password,confirmPassword);
            User user = iUserService.addUser(login,password);
            int userId = iUserService.getUserId(login);
            HttpSession session = request.getSession();
            session.setAttribute(USER, user);
            session.setAttribute(USER_ID, userId);
            jumpRedirect(TASKS_PAGE, response);
        } catch (UserDaoException | ValidationException e) {
            jumpError(REGISTRATION_PAGE, e.getMessage(), request, response);
        }
    }


    private static void checkInput(String login, String password, String confirmPassword) throws ValidationException {
        if(login.isEmpty() && password.isEmpty() && confirmPassword.isEmpty()){
            throw new ValidationException(EMPTY_LOGIN_AND_PASSWORD);
        }
        if(login.isEmpty()){
            throw new ValidationException(EMPTY_LOGIN);
        }
        if(password.isEmpty()){
            throw new ValidationException(EMPTY_PASSWORD);
        }
        if(confirmPassword.isEmpty()){
            throw new ValidationException(EMPTY_CONFIRM_PASSWORD);
        }
        if (!password.equals(confirmPassword)){
            throw new ValidationException(CONFIRM_NOT_EQUAL_TO_PASSWORD);
        }

    }

}

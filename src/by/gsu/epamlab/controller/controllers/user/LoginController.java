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

@WebServlet(name = "LoginController", urlPatterns = "/login")
public class LoginController extends AbstractUserController {

    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        try {
            checkInput(login, password);
            User user = iUserService.getUser(login, password);
            int userId = iUserService.getUserId(login);
            HttpSession session = request.getSession();
            session.setAttribute(USER, user);
            session.setAttribute(USER_ID, userId);
            jumpRedirect(TASKS_PAGE, response);
        } catch (UserDaoException | ValidationException e) {
            jumpError(LOGIN_PAGE, e.getMessage(), request, response);
        }
    }


    private static void checkInput(String login, String password) throws ValidationException {
        if(login.isEmpty() && password.isEmpty()){
            throw new ValidationException(EMPTY_LOGIN_AND_PASSWORD);
        }
        if(login.isEmpty()){
            throw new ValidationException(EMPTY_LOGIN);
        }
        if(password.isEmpty()){
            throw new ValidationException(EMPTY_PASSWORD);
        }

    }

}

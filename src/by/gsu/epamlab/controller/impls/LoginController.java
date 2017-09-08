package by.gsu.epamlab.controller.impls;

import by.gsu.epamlab.controller.ifaces.AbstractController;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.exceptions.UserDaoException;
import by.gsu.epamlab.model.services.userservice.IUserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static by.gsu.epamlab.controller.constants.Constants.*;

public class LoginController extends AbstractController {

    //todo exceptions
    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        login.trim();

        if(login.isEmpty() && password.isEmpty()){
            jumpError(LOGIN_PAGE, EMPTY_LOGIN_AND_PASSWORD, request, response);
            return;
        }
        if(login.isEmpty()){
            jumpError(LOGIN_PAGE, EMPTY_LOGIN, request, response);
            return;
        }
        if(password.isEmpty()){
            jumpError(LOGIN_PAGE, EMPTY_PASSWORD, request, response);
            return;
        }

        //todo rework user service?
        IUserService iUserService = (IUserService) getServletContext().getAttribute(IUSER_SERVICE);

        try {
            User user = iUserService.getUser(login, password);
            //todo userId field
            int userId = iUserService.getUserId(login);
            HttpSession session = request.getSession();
            session.setAttribute(USER, user);
            session.setAttribute(USER_ID, userId);
            jumpRedirect(TASKS_PAGE, response);
        } catch (UserDaoException e) {
            jumpError(LOGIN_PAGE, e.getMessage(), request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

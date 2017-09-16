package by.gsu.epamlab.controller.controllers.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.gsu.epamlab.controller.constants.Constants.INDEX_PAGE;

@WebServlet(name = "LogoutController", urlPatterns = "/logout")
public class LogoutController extends AbstractUserController {
    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        jumpRedirect(INDEX_PAGE, response);
    }

}

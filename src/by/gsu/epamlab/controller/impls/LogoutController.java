package by.gsu.epamlab.controller.impls;

import by.gsu.epamlab.controller.ifaces.AbstractController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.gsu.epamlab.controller.constants.Constants.*;

public class LogoutController extends AbstractController {
    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        jumpRedirect(INDEX_PAGE, response);
    }

}

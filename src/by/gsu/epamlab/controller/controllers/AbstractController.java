package by.gsu.epamlab.controller.controllers;

import by.gsu.epamlab.controller.constants.Constants;
import by.gsu.epamlab.model.services.task.ITaskService;
import by.gsu.epamlab.model.services.user.IUserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.gsu.epamlab.controller.constants.Constants.ITASK_SERVICE;
import static by.gsu.epamlab.controller.constants.Constants.IUSER_SERVICE;

public abstract class AbstractController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        performTask(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        performTask(request, response);
    }

    protected void jumpForward(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    protected void jumpRedirect(String url, HttpServletResponse response) throws ServletException, IOException{
        response.sendRedirect(url);
    }

    protected void jumpError(String url, String message, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(Constants.ERROR_ATTRIBUTE, message);
        jumpForward(url, request, response);
    }

    abstract protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}

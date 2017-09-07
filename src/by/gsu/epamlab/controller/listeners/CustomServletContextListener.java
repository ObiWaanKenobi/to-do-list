package by.gsu.epamlab.controller.listeners;

import by.gsu.epamlab.model.db.ConnectionPool;
import by.gsu.epamlab.model.factories.TaskDAOFactory;
import by.gsu.epamlab.model.factories.TaskServiceFactory;
import by.gsu.epamlab.model.factories.UserDAOFactory;
import by.gsu.epamlab.model.factories.UserServiceFactory;
import by.gsu.epamlab.model.services.ITaskService;
import by.gsu.epamlab.model.services.IUserService;

import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionIdListener;
import javax.servlet.http.HttpSessionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class CustomServletContextListener implements ServletContextListener {
    //session con list??
    //todo wrong use of pool

    private Connection connection = null;

    public void contextInitialized(ServletContextEvent contextEvent) {
        try {
            connection = ConnectionPool.getConnection();
            IUserService iUserService = UserServiceFactory.getUserService(UserDAOFactory.getUserDao(connection));
            ITaskService iTaskService= TaskServiceFactory.getTaskService(TaskDAOFactory.getTaskDAO(connection));
            contextEvent.getServletContext().setAttribute("IUserService", iUserService);
            contextEvent.getServletContext().setAttribute("ITaskService", iTaskService);
        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (NamingException e1) {
            e1.printStackTrace();
        }
    }

    public void contextDestroyed(ServletContextEvent contextEvent) {
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }


    /*
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        try {
            connection = ConnectionPool.getConnection();
            IUserService iUserService = UserServiceFactory.getUserService(UserDAOFactory.getUserDao(connection));
            ITaskService iTaskService= TaskServiceFactory.getTaskService(TaskDAOFactory.getTaskDAO(connection));
            httpSessionEvent.getSession().setAttribute("IUserService", iUserService);
            httpSessionEvent.getSession().setAttribute("ITaskService", iTaskService);
        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (NamingException e1) {
            e1.printStackTrace();
        }

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

    }
    */
}


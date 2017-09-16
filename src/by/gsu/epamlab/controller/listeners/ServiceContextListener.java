package by.gsu.epamlab.controller.listeners;

import by.gsu.epamlab.model.db.ConnectionPool;
import by.gsu.epamlab.model.exceptions.FactoryException;
import by.gsu.epamlab.model.factories.dao.DAOFactory;
import by.gsu.epamlab.model.factories.service.TaskServiceFactory;
import by.gsu.epamlab.model.factories.service.UserServiceFactory;
import by.gsu.epamlab.model.managers.ConfigurationManager;
import by.gsu.epamlab.model.services.task.ITaskService;
import by.gsu.epamlab.model.services.user.IUserService;

import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.SQLException;

import static by.gsu.epamlab.controller.constants.Constants.*;

public class ServiceContextListener implements ServletContextListener {

    private Connection connection = null;

    public void contextInitialized(ServletContextEvent contextEvent) {
        try {
            connection = ConnectionPool.getConnection();
            DAOFactory factory = DAOFactory.getDAOFactory(ConfigurationManager.getProperty(SOURCE_PROPERTY));
            IUserService iUserService = UserServiceFactory.getUserService(factory.getUserDAO(connection));
            ITaskService iTaskService= TaskServiceFactory.getTaskService(factory.getTaskDAO(connection));
            contextEvent.getServletContext().setAttribute(IUSER_SERVICE, iUserService);
            contextEvent.getServletContext().setAttribute(ITASK_SERVICE, iTaskService);
        } catch (SQLException | NamingException | FactoryException e) {
            e.printStackTrace();
        }
    }

    public void contextDestroyed(ServletContextEvent contextEvent) {
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}


package by.gsu.epamlab.model.factories.dao;

import by.gsu.epamlab.model.constants.CommonConstants;
import by.gsu.epamlab.model.dao.task.ITaskDAO;
import by.gsu.epamlab.model.dao.user.IUserDAO;
import by.gsu.epamlab.model.exceptions.FactoryException;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class DAOFactory {

    public abstract IUserDAO getUserDAO(Connection connection) throws SQLException;
    public abstract ITaskDAO getTaskDAO(Connection connection) throws SQLException;


    public static DAOFactory getDAOFactory(String sourceType) throws FactoryException {
        DAOFactory returnDAO = null;
        switch (sourceType){
            case "MYSQL":
                returnDAO = new MySQLDAOFactory();
                break;
            default:
                throw new FactoryException(CommonConstants.UNKNOWN_SOURCE);
        }
        return returnDAO;
    }
}

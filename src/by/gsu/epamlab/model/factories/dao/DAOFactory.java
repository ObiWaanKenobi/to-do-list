package by.gsu.epamlab.model.factories.dao;

import by.gsu.epamlab.model.dao.taskdao.ITaskDAO;
import by.gsu.epamlab.model.dao.userdao.IUserDAO;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class DAOFactory {

    public abstract IUserDAO getUserDAO(Connection connection) throws SQLException;
    public abstract ITaskDAO getTaskDAO(Connection connection) throws SQLException;

    //todo throw exc
    public static DAOFactory getDAOFactory(String sourceType){
        switch (sourceType.toUpperCase()){
            case "MYSQL":
                return new MySQLDAOFactory();
            default:
                return null;
        }
    }
}

package by.gsu.epamlab.model.factories.dao;

import by.gsu.epamlab.model.dao.taskDAO.MySQLTaskDAO;
import by.gsu.epamlab.model.dao.taskDAO.ITaskDAO;
import by.gsu.epamlab.model.dao.userDAO.MySQLUserDAO;
import by.gsu.epamlab.model.dao.userDAO.IUserDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class MySQLDAOFactory extends DAOFactory {
    @Override
    public IUserDAO getUserDAO(Connection connection) throws SQLException {
        return new MySQLUserDAO(connection);
    }

    @Override
    public ITaskDAO getTaskDAO(Connection connection) throws SQLException {
        return new MySQLTaskDAO(connection);
    }
}

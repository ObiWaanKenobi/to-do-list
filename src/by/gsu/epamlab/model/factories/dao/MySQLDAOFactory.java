package by.gsu.epamlab.model.factories.dao;

import by.gsu.epamlab.model.dao.task.MySQLTaskDAO;
import by.gsu.epamlab.model.dao.task.ITaskDAO;
import by.gsu.epamlab.model.dao.user.MySQLUserDAO;
import by.gsu.epamlab.model.dao.user.IUserDAO;

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


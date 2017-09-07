package by.gsu.epamlab.model.factories;

import by.gsu.epamlab.model.dao.userDAO.DBUserDAO;
import by.gsu.epamlab.model.dao.userDAO.IUserDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class UserDAOFactory {
    //todo
    public static IUserDAO getUserDao(Connection connection) throws SQLException {
        return new DBUserDAO(connection);
    }
}

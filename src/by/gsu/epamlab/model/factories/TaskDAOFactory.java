package by.gsu.epamlab.model.factories;

import by.gsu.epamlab.model.dao.taskDAO.DBTaskDAO;
import by.gsu.epamlab.model.dao.taskDAO.ITaskDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class TaskDAOFactory {
    //todo
    public static ITaskDAO getTaskDAO(Connection connection) throws SQLException {
        return new DBTaskDAO(connection);
    }
}

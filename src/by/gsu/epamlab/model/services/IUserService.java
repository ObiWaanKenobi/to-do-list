package by.gsu.epamlab.model.services;

import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.exceptions.UserDaoException;

import java.sql.SQLException;

public interface IUserService {
    public User getUser(String login, String password) throws UserDaoException, SQLException;
    public User addUser(String login, String password) throws UserDaoException, SQLException;
    public int getUserId(String login) throws UserDaoException, SQLException;
}

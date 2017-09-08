package by.gsu.epamlab.model.dao.userDAO;

import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.exceptions.UserDaoException;

public interface IUserDAO {
    public User getUser(String login, String password) throws UserDaoException;
    public User addUser(String login, String password) throws UserDaoException;
    public int getUserId(String login) throws UserDaoException;
    public void endTransaction() throws UserDaoException;
}

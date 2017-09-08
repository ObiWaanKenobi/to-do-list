package by.gsu.epamlab.model.services.userservice;

import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.dao.userDAO.IUserDAO;
import by.gsu.epamlab.model.exceptions.UserDaoException;

import java.sql.SQLException;

public class IUserServiceImpl implements IUserService {
    private IUserDAO iUserDAO;

    public IUserServiceImpl() {
    }

    public IUserServiceImpl(IUserDAO iUserDAO) {
        this.iUserDAO = iUserDAO;
    }

    @Override
    public int getUserId(String login) throws UserDaoException, SQLException {
        int userId = iUserDAO.getUserId(login);
        iUserDAO.endTransaction();
        return userId;
    }

    @Override
    public User getUser(String login, String password) throws UserDaoException, SQLException {
        User user = iUserDAO.getUser(login, password);
        iUserDAO.endTransaction();
        return user;
    }

    @Override
    public User addUser(String login, String password) throws UserDaoException, SQLException {
        User user = iUserDAO.addUser(login, password);
        iUserDAO.endTransaction();
        return user;
    }


}
package by.gsu.epamlab.model.services.user;

import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.dao.user.IUserDAO;
import by.gsu.epamlab.model.exceptions.UserDaoException;

public class IUserServiceImpl implements IUserService {
    private IUserDAO iUserDAO;

    public IUserServiceImpl() {
    }

    public IUserServiceImpl(IUserDAO iUserDAO) {
        this.iUserDAO = iUserDAO;
    }

    @Override
    public int getUserId(String login) throws UserDaoException{
        int userId = iUserDAO.getUserId(login);
        iUserDAO.endTransaction();
        return userId;
    }

    @Override
    public User getUser(String login, String password) throws UserDaoException{
        User user = iUserDAO.getUser(login, password);
        iUserDAO.endTransaction();
        return user;
    }

    @Override
    public User addUser(String login, String password) throws UserDaoException{
        User user = iUserDAO.addUser(login, password);
        iUserDAO.endTransaction();
        return user;
    }


}

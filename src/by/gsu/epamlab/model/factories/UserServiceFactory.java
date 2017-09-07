package by.gsu.epamlab.model.factories;

import by.gsu.epamlab.model.dao.userDAO.IUserDAO;
import by.gsu.epamlab.model.services.IUserService;
import by.gsu.epamlab.model.services.IUserServiceImpl;

public class UserServiceFactory {
    public static IUserService getUserService(IUserDAO iUserDAO){
        return new IUserServiceImpl(iUserDAO);
    }
}

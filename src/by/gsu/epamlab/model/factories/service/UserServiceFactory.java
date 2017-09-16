package by.gsu.epamlab.model.factories.service;

import by.gsu.epamlab.model.dao.user.IUserDAO;
import by.gsu.epamlab.model.services.user.IUserService;
import by.gsu.epamlab.model.services.user.IUserServiceImpl;

public class UserServiceFactory {
    public static IUserService getUserService(IUserDAO iUserDAO){
        return new IUserServiceImpl(iUserDAO);
    }
}

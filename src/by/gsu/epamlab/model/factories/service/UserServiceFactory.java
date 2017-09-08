package by.gsu.epamlab.model.factories.service;

import by.gsu.epamlab.model.dao.userDAO.IUserDAO;
import by.gsu.epamlab.model.services.userservice.IUserService;
import by.gsu.epamlab.model.services.userservice.IUserServiceImpl;

public class UserServiceFactory {
    public static IUserService getUserService(IUserDAO iUserDAO){
        return new IUserServiceImpl(iUserDAO);
    }
}

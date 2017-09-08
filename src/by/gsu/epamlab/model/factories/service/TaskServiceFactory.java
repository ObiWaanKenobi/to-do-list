package by.gsu.epamlab.model.factories.service;

import by.gsu.epamlab.model.dao.taskDAO.ITaskDAO;
import by.gsu.epamlab.model.services.taskservice.ITaskService;
import by.gsu.epamlab.model.services.taskservice.ITaskServiceImpl;

public class TaskServiceFactory {
    public static ITaskService getTaskService(ITaskDAO iTaskDAO){
        return new ITaskServiceImpl(iTaskDAO);
    }
}

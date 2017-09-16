package by.gsu.epamlab.model.factories.service;

import by.gsu.epamlab.model.dao.task.ITaskDAO;
import by.gsu.epamlab.model.services.task.ITaskService;
import by.gsu.epamlab.model.services.task.ITaskServiceImpl;

public class TaskServiceFactory {
    public static ITaskService getTaskService(ITaskDAO iTaskDAO){
        return new ITaskServiceImpl(iTaskDAO);
    }
}

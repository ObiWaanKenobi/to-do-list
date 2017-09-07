package by.gsu.epamlab.model.factories;

import by.gsu.epamlab.model.dao.taskDAO.ITaskDAO;
import by.gsu.epamlab.model.services.ITaskService;
import by.gsu.epamlab.model.services.ITaskServiceImpl;

public class TaskServiceFactory {
    //todo
    public static ITaskService getTaskService(ITaskDAO iTaskDAO){
        return new ITaskServiceImpl(iTaskDAO);
    }
}

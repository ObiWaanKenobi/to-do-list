package by.gsu.epamlab.model.services.task;

import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.constants.CommonConstants;
import by.gsu.epamlab.model.dao.task.ITaskDAO;
import by.gsu.epamlab.model.exceptions.TaskDaoException;
import by.gsu.epamlab.model.managers.ConfigurationManager;

import java.io.File;
import java.util.List;

import static by.gsu.epamlab.controller.constants.Constants.SOURCE_PROPERTY;

public class ITaskServiceImpl implements ITaskService {

    private ITaskDAO iTaskDAO;

    public ITaskServiceImpl() {
    }

    public ITaskServiceImpl(ITaskDAO iTaskDAO) {
        this.iTaskDAO = iTaskDAO;
    }

    @Override
    public void moveTask(String moveTaskType, String... tasksId) throws TaskDaoException{
        if (tasksId != null){
            for (String taskId : tasksId) {
                iTaskDAO.moveTask(moveTaskType, taskId);
            }
            iTaskDAO.endTransaction();
        }
    }

    @Override
    public void deleteTask(String... tasksId) throws TaskDaoException {
        if (tasksId != null){
            String filePath = ConfigurationManager.getProperty(CommonConstants.FILES_DIR_PROPERTY);
            for (String taskId : tasksId) {
                String fileName = getTaskFileNameById(taskId);
                File file = new File(filePath + File.separator + fileName);
                if (file.exists()){
                    file.delete();
                }
                iTaskDAO.deleteTask(taskId);
            }
            iTaskDAO.endTransaction();
        }
    }

    @Override
    public List<Task> getTasks(String taskType, String userId) throws TaskDaoException{
        List<Task> tasks = iTaskDAO.getTasks(taskType, userId);
        iTaskDAO.endTransaction();
        return tasks;
    }

    @Override
    public Task addTask(String taskName, String taskDate, String userId) throws TaskDaoException{
        Task task = iTaskDAO.addTask(taskName, taskDate, userId);
        iTaskDAO.endTransaction();
        return task;
    }

    @Override
    public void addTaskFile(String fileName, String taskId) throws TaskDaoException{
        iTaskDAO.addTaskFile(fileName, taskId);
        iTaskDAO.endTransaction();
    }

    @Override
    public void deleteTaskFile(String fileName) throws TaskDaoException{
        iTaskDAO.deleteTaskFile(fileName);
        iTaskDAO.endTransaction();
    }

    @Override
    public String getTaskFileNameById(String taskId) throws TaskDaoException {
        String fileName = iTaskDAO.getTaskFileNameById(taskId);
        iTaskDAO.endTransaction();
        return fileName;
    }
}

package by.gsu.epamlab.model.services;

import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.dao.taskDAO.ITaskDAO;
import by.gsu.epamlab.model.exceptions.TaskDaoException;

import java.sql.SQLException;
import java.util.List;

public class ITaskServiceImpl implements ITaskService {

    private ITaskDAO iTaskDAO;

    public ITaskServiceImpl() {
    }

    public ITaskServiceImpl(ITaskDAO iTaskDAO) {
        this.iTaskDAO = iTaskDAO;
    }

    @Override
    public void moveTask(String moveTaskType, String... tasksId) throws TaskDaoException, SQLException {
        for (String taskId : tasksId) {
            iTaskDAO.moveTask(moveTaskType, taskId);
        }
        iTaskDAO.endTransaction();
    }

    @Override
    public List<Task> getTasks(String taskType, String userId) throws TaskDaoException, SQLException {
        List<Task> tasks = iTaskDAO.getTasks(taskType, userId);
        iTaskDAO.endTransaction();
        return tasks;
    }

    @Override
    public Task addTask(String taskName, String taskDate, String userId) throws TaskDaoException, SQLException {
        Task task = iTaskDAO.addTask(taskName, taskDate, userId);
        iTaskDAO.endTransaction();
        return task;
    }

    @Override
    public void addTaskFile(String fileName, String taskId) throws TaskDaoException, SQLException {
        iTaskDAO.addTaskFile(fileName, taskId);
        iTaskDAO.endTransaction();
    }

    @Override
    public void deleteTaskFile(String taskId) throws TaskDaoException, SQLException {
        iTaskDAO.deleteTaskFile(taskId);
        iTaskDAO.endTransaction();
    }
}

package by.gsu.epamlab.model.services;

import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.exceptions.TaskDaoException;

import java.sql.SQLException;
import java.util.List;

public interface ITaskService {
    public List<Task> getTasks(String taskType, String userId) throws TaskDaoException, SQLException;
    public Task addTask(String taskName, String taskDate, String userId) throws TaskDaoException, SQLException;
    public void moveTask(String moveTaskType, String...tasksId) throws TaskDaoException, SQLException;
    public void addTaskFile(String fileName, String taskId) throws TaskDaoException, SQLException;
    public void deleteTaskFile(String taskId) throws TaskDaoException, SQLException;
}

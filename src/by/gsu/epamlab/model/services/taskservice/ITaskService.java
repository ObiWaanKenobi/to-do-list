package by.gsu.epamlab.model.services.taskservice;

import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.exceptions.TaskDaoException;

import java.sql.SQLException;
import java.util.List;

public interface ITaskService {
    public List<Task> getTasks(String taskType, String userId) throws TaskDaoException;
    public Task addTask(String taskName, String taskDate, String userId) throws TaskDaoException;
    public void moveTask(String moveTaskType, String...tasksId) throws TaskDaoException;
    public void addTaskFile(String fileName, String taskId) throws TaskDaoException;
    public void deleteTaskFile(String taskId) throws TaskDaoException;
}

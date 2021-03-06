package by.gsu.epamlab.model.dao.task;

import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.exceptions.TaskDaoException;

import java.util.List;

public interface ITaskDAO {
    public List<Task> getTasks(String taskType, String userId) throws TaskDaoException;
    public Task addTask(String taskName, String taskDate, String userId) throws TaskDaoException;
    public void moveTask(String moveTaskType, String taskId) throws TaskDaoException;
    public void deleteTask(String taskId) throws TaskDaoException;
    public void addTaskFile(String fileName, String taskId) throws TaskDaoException;
    public void deleteTaskFile(String fileName) throws TaskDaoException;
    public String getTaskFileNameById(String taskId) throws TaskDaoException;
    public void endTransaction() throws TaskDaoException;
}

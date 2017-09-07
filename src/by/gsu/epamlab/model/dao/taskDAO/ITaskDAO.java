package by.gsu.epamlab.model.dao.taskDAO;

import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.enums.MoveTaskType;
import by.gsu.epamlab.model.enums.TaskType;
import by.gsu.epamlab.model.exceptions.TaskDaoException;

import java.sql.SQLException;
import java.util.List;

public interface ITaskDAO {
    public List<Task> getTasks(String taskType, String userId) throws TaskDaoException, SQLException;
    public Task addTask(String taskName, String taskDate, String userId) throws TaskDaoException;
    public void moveTask(String moveTaskType, String taskId) throws TaskDaoException, SQLException;
    //public Task editTask(Task task) throws TaskDaoException;
    public void endTransaction() throws SQLException;

    //todo exc
    public void addTaskFile(String fileName, String taskId);
    public void deleteTaskFile(String taskId);
}

package by.gsu.epamlab.model.dao.taskDAO;

import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.constants.SQLConstants;
import by.gsu.epamlab.model.enums.MoveTaskType;
import by.gsu.epamlab.model.enums.TaskType;
import by.gsu.epamlab.model.exceptions.TaskDaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.gsu.epamlab.model.constants.Constants.*;
import static by.gsu.epamlab.model.constants.SQLConstants.*;

public class DBTaskDAO implements ITaskDAO {

    private Connection connection = null;

    public DBTaskDAO() {
    }

    public DBTaskDAO(Connection connection) throws SQLException {
        this.connection = connection;
        connection.setAutoCommit(false);
    }

    @Override
    public void endTransaction() throws SQLException {
        connection.commit();
    }

    //sqlexcept change
    @Override
    public List<Task> getTasks(String taskType, String userId) throws TaskDaoException, SQLException {
        List<Task> tasks = new ArrayList<>();
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(TaskType.valueOf(taskType).getTaskQuery())) {

            preparedStatement.setString(USER_ID_IND, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Task task = new Task(resultSet.getInt(TASKID_IND), resultSet.getString(DESCRIPTION_IND), resultSet.getDate(DATE_IND), resultSet.getBoolean(ISFIXED_IND), resultSet.getBoolean(ISBINNED_IND), resultSet.getString(FILENAME_IND));
                tasks.add(task);
            }
            return tasks;
        } catch (SQLException e) {
            //todo
            e.printStackTrace();
            return new ArrayList<Task>();
        }
    }

    //sqlexcept change
    @Override
    public Task addTask(String taskName, String taskDate, String userId) throws TaskDaoException {
        Task task = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TASK_INTO_TASKS)) {
            task = findTask(taskName, taskDate, userId);
            if (task != null) {
                task = new Task();
                throw new TaskDaoException(TASK_IS_EXIST);
            }
            synchronized (DBTaskDAO.class) {
                preparedStatement.setString(TASK_NAME_IND, taskName);
                preparedStatement.setString(TASK_DATE_IND, taskDate);
                preparedStatement.setString(TASK_USER_ID_IND, userId);
                preparedStatement.executeUpdate();
            }
            task = findTask(taskName, taskDate, userId);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return task;
    }

    //sqlexcept change
    @Override
    public void moveTask(String moveTaskType, String taskId) throws TaskDaoException, SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(MoveTaskType.valueOf(moveTaskType).getQuery())) {
            synchronized (DBTaskDAO.class) {
                preparedStatement.setString(TASKID_IND, taskId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private Task findTask(String taskName, String taskDate, String userId) throws SQLException {
        ResultSet resultSet = null;
        Task task = null;

        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TASK_FROM_TASKS)) {
            preparedStatement.setString(TASK_NAME_IND, taskName);
            preparedStatement.setString(TASK_DATE_IND, taskDate);
            preparedStatement.setString(TASK_USER_ID_IND, userId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                task = new Task(resultSet.getInt(TASKID_IND), resultSet.getString(DESCRIPTION_IND), resultSet.getDate(DATE_IND), resultSet.getBoolean(ISFIXED_IND), resultSet.getBoolean(ISBINNED_IND), resultSet.getString(FILENAME_IND));
            }
        }
        return task;
    }

    @Override
    public void addTaskFile(String fileName, String taskId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.ADD_FILE_TO_TASK)) {
            synchronized (DBTaskDAO.class) {
                preparedStatement.setString(1, fileName);
                preparedStatement.setString(2, taskId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteTaskFile(String taskId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.DELETE_FILE_FROM_TASK)) {
            synchronized (DBTaskDAO.class) {
                preparedStatement.setString(1, taskId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}



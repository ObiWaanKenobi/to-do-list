package by.gsu.epamlab.model.dao.task;

import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.constants.SQLConstants;
import by.gsu.epamlab.model.enums.MoveTaskType;
import by.gsu.epamlab.model.enums.TaskType;
import by.gsu.epamlab.model.exceptions.TaskDaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.gsu.epamlab.model.constants.CommonConstants.*;
import static by.gsu.epamlab.model.constants.SQLConstants.*;

public class MySQLTaskDAO implements ITaskDAO {

    private Connection connection = null;

    public MySQLTaskDAO() {
    }

    public MySQLTaskDAO(Connection connection) throws SQLException {
        this.connection = connection;
        connection.setAutoCommit(false);
    }

    @Override
    public void endTransaction() throws TaskDaoException {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new TaskDaoException(DATABASE_ERROR, e);
        }
    }


    @Override
    public List<Task> getTasks(String taskType, String userId) throws TaskDaoException {
        List<Task> tasks = new ArrayList<>();
        ResultSet resultSet;
        try (PreparedStatement preparedStatement = connection.prepareStatement(TaskType.valueOf(taskType).getTaskQuery())) {
            preparedStatement.setString(USER_ID_IND, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Task task = new Task(resultSet.getInt(TASKID_IND), resultSet.getString(DESCRIPTION_IND), resultSet.getDate(DATE_IND),
                                     resultSet.getBoolean(ISFIXED_IND), resultSet.getBoolean(ISBINNED_IND), resultSet.getString(FILENAME_IND));
                tasks.add(task);
            }
            return tasks;
        } catch (SQLException e) {
            throw new TaskDaoException(DATABASE_ERROR, e);
        }
    }


    @Override
    public Task addTask(String taskName, String taskDate, String userId) throws TaskDaoException {
        Task task;
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TASK_INTO_TASKS)) {
            task = findTask(taskName, taskDate, userId);
            if (task != null) {
                throw new TaskDaoException(TASK_IS_EXIST);
            }
            synchronized (MySQLTaskDAO.class) {
                preparedStatement.setString(TASK_NAME_IND, taskName);
                preparedStatement.setString(TASK_DATE_IND, taskDate);
                preparedStatement.setString(TASK_USER_ID_IND, userId);
                preparedStatement.executeUpdate();
            }
            task = findTask(taskName, taskDate, userId);
        } catch (SQLException e) {
            throw new TaskDaoException(DATABASE_ERROR, e);
        }
        return task;
    }


    @Override
    public void moveTask(String moveTaskType, String taskId) throws TaskDaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(MoveTaskType.valueOf(moveTaskType).getQuery())){
            synchronized (MySQLTaskDAO.class) {
                preparedStatement.setString(TASKID_IND, taskId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new TaskDaoException(DATABASE_ERROR, e);
        }
    }

    @Override
    public void deleteTask(String taskId) throws TaskDaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.DELETE_TASK_FROM_TASKS)) {
            synchronized (MySQLTaskDAO.class) {
                preparedStatement.setString(TASKID_IND, taskId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new TaskDaoException(DATABASE_ERROR, e);
        }

    }

    private Task findTask(String taskName, String taskDate, String userId) throws SQLException {
        ResultSet resultSet;
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
    public void addTaskFile(String fileName, String taskId) throws TaskDaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.ADD_FILE_TO_TASK)) {
            synchronized (MySQLTaskDAO.class) {
                preparedStatement.setString(ADD_FILE_NAME_IND, fileName);
                preparedStatement.setString(ADD_FILE_TASKID_IND, taskId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new TaskDaoException(DATABASE_ERROR, e);
        }

    }

    @Override
    public void deleteTaskFile(String fileName) throws TaskDaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.DELETE_FILE_FROM_TASK)) {
            synchronized (MySQLTaskDAO.class) {
                preparedStatement.setString(DELETE_FILE_IND, fileName);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new TaskDaoException(DATABASE_ERROR, e);
        }
    }

    @Override
    public String getTaskFileNameById(String taskId) throws TaskDaoException {
        ResultSet resultSet;
        String fileName = null;

        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FILE_NAME_FROM_TASKS)) {
            preparedStatement.setString(TASKID_IND, taskId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                fileName = resultSet.getString(1);
            }
        } catch (SQLException e) {
            throw new TaskDaoException(DATABASE_ERROR, e);
        }
        return fileName;
    }
}



package by.gsu.epamlab.model.dao.userdao;

import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.exceptions.UserDaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.gsu.epamlab.model.constants.Constants.*;
import static by.gsu.epamlab.model.constants.SQLConstants.*;

public class MySQLUserDAO implements IUserDAO {

    private Connection connection = null;

    public MySQLUserDAO() {
    }

    public MySQLUserDAO(Connection connection) throws SQLException {
        this.connection = connection;
        connection.setAutoCommit(false);
    }

    @Override
    public void endTransaction() throws UserDaoException {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new UserDaoException();
        }
    }

    @Override
    public User getUser(String login, String password) throws UserDaoException {
        User user;
        try {
            user = findUser(login);
            if (user != null) {
                if (!user.getPassword().equals(password)) {
                    throw new UserDaoException(INCORRECT_PASSWORD);
                }
            } else {
                throw new UserDaoException(INCORRECT_LOGIN);
            }

        } catch (SQLException e) {
            throw new UserDaoException(DATABASE_ERROR);
        }
        return user;
    }


    @Override
    public User addUser(String login, String password) throws UserDaoException {
        User user;
        try {
            User findUser = findUser(login);
            if (findUser != null) {
                throw new UserDaoException(USER_IS_EXIST);
            } else {
                user = new User(login, password);
                insertUserIntoDB(user);
            }
        } catch (SQLException e) {
            throw new UserDaoException(DATABASE_ERROR);
        }
        return user;
    }

    //todo
    @Override
    public int getUserId(String login) throws UserDaoException {
        int userId = 0;
        ResultSet resultSet;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setString(LOGIN_IND, login);
            synchronized (MySQLUserDAO.class) {
                resultSet = preparedStatement.executeQuery();
            }
            if (resultSet.next()) {
                userId = resultSet.getInt(USER_ID_IND);
            }
        } catch (SQLException e) {
            throw new UserDaoException(DATABASE_ERROR);
        }
        return userId;
    }

    private void insertUserIntoDB(User user) throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_INTO_USERS)) {
            preparedStatement.setString(LOGIN_IND, user.getLogin());
            preparedStatement.setString(PASSWORD_IND, user.getPassword());
            synchronized (MySQLUserDAO.class) {
                preparedStatement.executeUpdate();
            }
        }
    }


    private User findUser(String login) throws SQLException {
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_FROM_USERS)) {
            preparedStatement.setString(LOGIN_IND, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            //synchro?
            if (resultSet.next()) {
                user = new User(resultSet.getString(LOGIN_IND), resultSet.getString(PASSWORD_IND));
            }
        }
        return user;
    }
}

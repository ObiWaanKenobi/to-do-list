package by.gsu.epamlab.model.exceptions;

public class UserDaoException extends Exception {

    public UserDaoException() {
    }

    public UserDaoException(String message) {
        super(message);
    }

    public UserDaoException(String message, Throwable cause) {
        super(message, cause);
    }

}

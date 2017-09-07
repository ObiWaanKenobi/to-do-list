package by.gsu.epamlab.model.exceptions;

public class TaskDaoException extends Exception {
    public TaskDaoException() {
    }

    public TaskDaoException(String message) {
        super(message);
    }

    public TaskDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}

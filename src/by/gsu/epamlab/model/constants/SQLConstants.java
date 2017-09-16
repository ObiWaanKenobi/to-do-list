package by.gsu.epamlab.model.constants;

public class SQLConstants {

    //users table
    public final static String SELECT_USER_FROM_USERS = "SELECT login,password FROM users WHERE login = ?";
    public final static String INSERT_USER_INTO_USERS = "INSERT INTO users(login,password) VALUES (?,?)";
    public final static String SELECT_USER_BY_ID = "SELECT id FROM users WHERE login = ?";
    public final static int LOGIN_IND = 1;
    public final static int PASSWORD_IND = 2;
    public final static int USER_ID_IND = 1;


    //tasks table
    public final static String SELECT_TASK_FROM_TASKS = "SELECT * FROM tasks WHERE description = ? AND date = ? AND userId = ?";
    public final static String SELECT_FILE_NAME_FROM_TASKS = "SELECT fileName FROM tasks WHERE id = ?";
    public final static String INSERT_TASK_INTO_TASKS = "INSERT INTO tasks (description, date, isFixed, isBinned, userId) VALUES (?,?,FALSE,FALSE,?)";
    public final static String ADD_FILE_TO_TASK = "UPDATE tasks SET fileName = ? WHERE id = ?";
    public final static String DELETE_FILE_FROM_TASK = "UPDATE tasks SET fileName = null WHERE fileName = ?";
    public final static String MOVE_TASK_TO_BIN = "UPDATE tasks SET isBinned = TRUE, isFixed = FALSE WHERE id = ?";
    public final static String MOVE_TASK_TO_FIXED ="UPDATE tasks SET isFixed = TRUE, isBinned = FALSE WHERE id = ?";
    public final static String RESTORE_TASK = "UPDATE tasks SET isBinned = FALSE WHERE id = ?";
    public final static String RESTORE_FIXED_TASK = "UPDATE tasks SET isFixed = FALSE WHERE id = ?";
    public final static String DELETE_TASK_FROM_TASKS = "DELETE FROM tasks WHERE id = ?";

    public final static String SHOW_TODAY_TASKS = "SELECT id, description, date, isFixed, isBinned, fileName FROM tasks WHERE date <= current_date AND isFixed = FALSE AND isBinned = FALSE AND userId=?";
    public final static String SHOW_TOMORROW_TASKS = "SELECT id, description, date, isFixed, isBinned, fileName FROM tasks WHERE date = current_date + INTERVAL 1 DAY AND isFixed = FALSE AND isBinned = FALSE AND userId=?";
    public final static String SHOW_SOMEDAY_TASKS = "SELECT id, description, date, isFixed, isBinned, fileName FROM tasks WHERE date > current_date + INTERVAL 1 DAY AND isFixed = FALSE AND isBinned = FALSE AND userId=?";
    public final static String SHOW_FIXED_TASKS = "SELECT id, description, date, isFixed, isBinned, fileName FROM tasks WHERE isFixed = TRUE AND isBinned = FALSE AND userId=?";
    public final static String SHOW_BINNED_TASKS = "SELECT id, description, date, isFixed, isBinned, fileName FROM tasks WHERE isBinned = TRUE AND isFIXED = FALSE AND userId=?";

    public final static int DELETE_FILE_IND = 1;
    public final static int ADD_FILE_NAME_IND = 1;
    public final static int ADD_FILE_TASKID_IND = 2;;
    public final static int TASK_NAME_IND = 1;
    public final static int TASK_DATE_IND = 2;
    public final static int TASK_USER_ID_IND = 3;
}

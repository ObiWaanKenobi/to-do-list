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
    public final static String INSERT_TASK_INTO_TASKS = "INSERT INTO tasks (description, date, isFixed, isBinned, userId) VALUES (?,?,FALSE,FALSE,?)";
    public final static String ADD_FILE_TO_TASK = "UPDATE tasks SET fileName = ? WHERE id = ?";
    public final static String DELETE_FILE_FROM_TASK = "UPDATE tasks SET fileName = null WHERE id = ?";
    public final static int TASK_NAME_IND = 1;
    public final static int TASK_DATE_IND = 2;
    public final static int TASK_USER_ID_IND = 3;
}

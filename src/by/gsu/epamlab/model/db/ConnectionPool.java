package by.gsu.epamlab.model.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

    public static Connection getConnection() throws SQLException, NamingException {
        Connection connection = null;
        Context envCtx = (Context) (new InitialContext().lookup("java:comp/env"));
        DataSource ds = (DataSource) envCtx.lookup("web_project_db");
        connection = ds.getConnection();
        return connection;
    }
}

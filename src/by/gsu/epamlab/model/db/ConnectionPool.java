package by.gsu.epamlab.model.db;

import by.gsu.epamlab.model.constants.CommonConstants;

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
        DataSource ds = (DataSource) envCtx.lookup(CommonConstants.CONNECTION_POOL_DB_NAME);
        connection = ds.getConnection();
        return connection;
    }
}

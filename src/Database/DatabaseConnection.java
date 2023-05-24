package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;

    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";

    private static final String userName = "system";

    private static final String password = "system";

    private DatabaseConnection() {

    }

    public static Connection getSession() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(URL, userName, password);
        }
        return connection;
    }
}
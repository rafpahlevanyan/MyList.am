package mylist.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectionProvider {

    private static DBConnectionProvider instance = new DBConnectionProvider();

    private Connection connection;

    private String driverName;
    private String dbUrl;
    private String username;
    private String password;

    private DBConnectionProvider() {
        try {
            loadProperties();
            Class.forName(driverName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static DBConnectionProvider getInstance() {
        return instance;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(dbUrl, username, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return connection;
    }

    private void loadProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("C:\\Users\\User\\IdeaProjects\\MyList.am\\src\\main\\resources\\config.properties"));
        driverName = properties.getProperty("db.driver.name");
        dbUrl = properties.getProperty("db.url");
        username = properties.getProperty("db.username");
        password = properties.getProperty("db.password");

    }

    public void close() {
        try {
            if (!connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



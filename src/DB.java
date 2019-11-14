import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {
    private static Connection connection;

    public static synchronized Connection getConnection() {
        if (connection == null) {
            try {
                InputStream input = new FileInputStream("config.properties");
                Properties prop = new Properties();
                prop.load(input);
                System.out.println(prop.getProperty("url"));
                connection = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("password"));
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}

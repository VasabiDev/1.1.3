package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String userName = "root";
    private static final String pass = "SQLserverPass";
    private static final String url = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
    static Connection connection = null;

    public static Connection connection() {

        {
            try {
                connection = DriverManager.getConnection(url, userName, pass);
                if (connection.isClosed() || connection == null) {
                    System.out.println("Подключение к базе прошло успешно");
                }
            } catch (SQLException e) {
                System.out.println("Неудачное подключение к базе");
                e.printStackTrace();
            }
        }
        return connection;
    }

}

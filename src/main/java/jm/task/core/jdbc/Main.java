package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;

import java.lang.*;
import java.sql.*;

public class Main {
    private static final String userName = "root";
    private static final String pass = "SQLserverPass";
    private static final String url = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
    private static final String tableName = "DBUSERS";


    public static void main(String[] args) {
        User user1 = new User();
        user1.setId(1L);
        user1.setName("Joseph");
        user1.setLastName("Joestar");
        user1.setAge((byte) 24);

        User user2 = new User();
        //   user2.setId(2L);
        user2.setName("Jotaro");
        user2.setLastName("Kujo");
        user2.setAge((byte) 18);

        User user3 = new User();
        //    user3.setId(3L);
        user3.setName("Josuke");
        user3.setLastName("Higashikata");
        user3.setAge((byte) 18);

        User user4 = new User();
        //   user4.setId(4L);
        user4.setName("Jolyne");
        user4.setLastName("Cujoh");
        user4.setAge((byte) 20);

        try (Connection connection = DriverManager.getConnection(url, userName, pass)) {
            System.out.println("Соединение установлено");
            Statement statement = connection.createStatement();

            String usersTable = "CREATE TABLE " + tableName + " ("
                    + "id BIGINT(64) NOT NULL AUTO_INCREMENT,"
                    + "name VARCHAR(64),"
                    + "lastName VARCHAR(64),"
                    + "age TINYINT(4), "
                    + "PRIMARY KEY(id))";
            DatabaseMetaData md = connection.getMetaData();
            ResultSet rs = md.getTables(null, null, tableName, null);
            if (!rs.next()) {
                statement.executeQuery(usersTable);
                System.out.println("Таблица была успешно создана");

                statement.executeUpdate(register(user1));
                statement.executeUpdate(register(user2));
                statement.executeUpdate(register(user3));
                statement.executeUpdate(register(user4));

            } else if (rs.next()) {
                System.out.println("Такая таблица уже существует");
            }
            DatabaseMetaData md1 = connection.getMetaData();
            ResultSet rs1 = md1.getTables(null, null, tableName, null);
            if (rs1.next()) {

                //    statement.executeUpdate("DROP TABLE " + tableName);
                //  System.out.println("Таблица была успешно удалена");
                System.out.println("______________________________________________________________");
                statement.executeQuery("SELECT * FROM " + tableName+" WHERE id="+ user2.getId());
                System.out.println("Выбран пользлователь с id "+user2.getId());
                System.out.println("______________________________________________________________");
                statement.executeQuery("SELECT * FROM " + tableName);
                System.out.println("Все пользователи ");
                System.out.println("______________________________________________________________");
                statement.executeUpdate("DELETE FROM " + tableName+" WHERE id="+ user1.getId());
                System.out.println("удален пользователь с id "+user1.getId());
                System.out.println("______________________________________________________________");
                statement.executeUpdate("TRUNCATE TABLE " + tableName);
                System.out.println("Таблица была успешно очищена");
                System.out.println("______________________________________________________________");
            } else if (!rs1.next()) {
                System.out.println("Таблицы не существует");
            }
        } catch (SQLException e) {
            System.out.println("Неудачное подключение к базе");
            e.printStackTrace();
        }
    }

    public static String register(User user) {
        String sqlInsert = "INSERT INTO " + tableName + " SET" +
                " id = " + "'" + user.getId() + "'" +
                ", name = " + "'" + user.getName() + "'" +
                ", lastName = " + "'" + user.getLastName() + "'" +
                ", age = " + "'" + user.getAge() + "'";
        System.out.println("User с именем – " + user.getName() + " добавлен в базу данных");
        return sqlInsert;
    }
}

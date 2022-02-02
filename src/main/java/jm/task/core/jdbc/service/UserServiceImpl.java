package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserServiceImpl implements UserService {

    private static final String userName = "root";
    private static final String pass = "SQLserverPass";
    private static final String url = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
    public static final String tableName = "USERSDB";
    Connection connection = null;


    // коннектимся к базе
    {
        try {
            connection = DriverManager.getConnection(url, userName, pass);
            System.out.println("Подключение к базе прошло успешно");
        } catch (SQLException e) {
            System.out.println("Неудачное подключение к базе");
            e.printStackTrace();
        }
    }

    // done
    public void createUsersTable() {
        // создаем таблицу если ее еще не существует
        String usersTable = "CREATE TABLE " + tableName + " ("
                + "id BIGINT(64) NOT NULL AUTO_INCREMENT,"
                + "name VARCHAR(64),"
                + "lastName VARCHAR(64),"
                + "age TINYINT(4), "
                + "PRIMARY KEY(id))";
        try (Statement statement = connection.createStatement()) {
            DatabaseMetaData md1 = connection.getMetaData();
            ResultSet rs1 = md1.getTables(null, null, tableName, null);
            if (!rs1.next()) {
                statement.executeUpdate(usersTable);
            } else {
                System.out.println("Такая таблица уже существует");
            }
        } catch (SQLException e) {
            System.out.println("Неудачное подключение к базе");
            e.printStackTrace();
        }
    }

    // done
    public void dropUsersTable() {
        // удаление таблицы
        try (Statement statement = connection.createStatement()) {
            DatabaseMetaData md1 = connection.getMetaData();
            ResultSet rs1 = md1.getTables(null, null, tableName, null);
            if (rs1.next()) {
                statement.executeUpdate("DROP TABLE " + tableName);
                System.out.println("Таблица была успешно удалена");
            } else {
                System.out.println("Такой таблицы не существует");
            }
        } catch (SQLException e) {
            System.out.println("Неудачное подключение к базе");
            e.printStackTrace();
        }
    }

    // done
    public void saveUser(String name, String lastName, byte age) {
        // создаем текстовый шаблон запроса
        String sqlInsert = "INSERT INTO " + tableName + " SET" +
                " name = " + "'" + name + "'" +
                ", lastName = " + "'" + lastName + "'" +
                ", age = " + "'" + age + "'";
        System.out.println("User с именем – " + name + " добавлен в базу данных");
        // делаем запрос к базе
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlInsert);

        } catch (SQLException e) {
            System.out.println("Неудачное подключение к базе");
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM " + tableName + " WHERE id=" + id);
            System.out.println("удален пользователь с id " + id);

        } catch (SQLException e) {
            System.out.println("Неудачное подключение к базе");
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> resultList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT * FROM " + tableName);

            while (result.next()) {
                User templUser = new User();
                templUser.setId(result.getLong("id"));
                templUser.setName(result.getString("name"));
                templUser.setLastName(result.getString("lastName"));
                templUser.setAge(result.getByte("age"));
                resultList.add(templUser);
            }
        } catch (SQLException e) {
            System.out.println("Неудачное подключение к базе");
            e.printStackTrace();
        }
        return resultList;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE " + tableName);
            System.out.println("Таблица была успешно очищена");
        } catch (SQLException e) {
            System.out.println("Неудачное подключение к базе");
            e.printStackTrace();
        }
    }


}

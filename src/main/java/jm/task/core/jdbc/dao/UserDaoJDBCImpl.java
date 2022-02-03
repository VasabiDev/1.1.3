package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final String tableName = "USERSDB";

    public UserDaoJDBCImpl() {

    }

    private static final String userName = "root";
    private static final String pass = "SQLserverPass";
    private static final String url = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
    static Connection connection = null;

    {
        try {
            connection = DriverManager.getConnection(url, userName, pass);
            System.out.println("Подключение к базе прошло успешно");
        } catch (SQLException e) {
            System.out.println("Неудачное подключение к базе");
            e.printStackTrace();
        }
    }

    public void createUsersTable() {

        // создаем таблицу если ее еще не существует
        String usersTable = "CREATE TABLE " + tableName + " ("
                + "id BIGINT(64) NOT NULL AUTO_INCREMENT,"
                + "name VARCHAR(64),"
                + "lastName VARCHAR(64),"
                + "age TINYINT(4), "
                + "PRIMARY KEY(id))";
        try (Statement statement = Util.connection().createStatement()) {
            DatabaseMetaData md1 = Util.connection().getMetaData();
            ResultSet rs1 = md1.getTables(null, null, tableName, null);
            if (!rs1.next()) {
                statement.executeUpdate("START TRANSACTION");
                statement.executeUpdate(usersTable);
                System.out.println("Таблица была успешно создана");
                statement.executeUpdate("ROLLBACK");
            } else {
                System.out.println("Такая таблица уже существует");
            }
        } catch (SQLException e) {
            System.out.println("Неудачное подключение к базе");
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        // удаление таблицы
        try (Statement statement = Util.connection().createStatement()) {
            DatabaseMetaData md1 = Util.connection().getMetaData();
            ResultSet rs1 = md1.getTables(null, null, tableName, null);
            if (rs1.next()) {
                statement.executeUpdate("START TRANSACTION");
                statement.executeUpdate("DROP TABLE " + tableName);
                System.out.println("Таблица была успешно удалена");
                statement.executeUpdate("ROLLBACK");
            } else {
                System.out.println("Такой таблицы не существует");
            }
        } catch (SQLException e) {
            System.out.println("Неудачное подключение к базе");
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        // создаем текстовый шаблон запроса
        String sqlInsert = "INSERT INTO " + tableName + " SET" + " name = " + "'" + name + "'" +
                ", lastName = " + "'" + lastName + "'" + ", age = " + "'" + age + "'";

        // делаем запрос к базе
        try (Statement statement1 = Util.connection().createStatement()) {
            //   statement.executeUpdate("START TRANSACTION");
            statement1.executeUpdate(sqlInsert);
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        //    statement.executeUpdate("ROLLBACK");

        } catch (SQLException e) {
            System.out.println("Неудачное подключение к базе");
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Statement statement = Util.connection().createStatement()) {
            statement.executeUpdate("START TRANSACTION");
            statement.executeUpdate("DELETE FROM " + tableName + " WHERE id=" + id);
            System.out.println("удален пользователь с id " + id);
            statement.executeUpdate("ROLLBACK");
        } catch (SQLException e) {
            System.out.println("Неудачное подключение к базе");
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> resultList = new ArrayList<>();
        try (Statement statement = Util.connection().createStatement()) {
            statement.executeUpdate("START TRANSACTION");
            ResultSet result = statement.executeQuery("SELECT * FROM " + tableName);

            while (result.next()) {
                User templUser = new User();
                templUser.setId(result.getLong("id"));
                templUser.setName(result.getString("name"));
                templUser.setLastName(result.getString("lastName"));
                templUser.setAge(result.getByte("age"));
                resultList.add(templUser);

                for (User u : resultList){
                    System.out.println(u.toString());
                }

            }

            statement.executeUpdate("ROLLBACK");
        } catch (SQLException e) {
            System.out.println("Неудачное подключение к базе");
            e.printStackTrace();
        }
        return resultList;

    }

    public void cleanUsersTable() {
        try (Statement statement = Util.connection().createStatement()) {
            statement.executeUpdate("START TRANSACTION");
            statement.executeUpdate("TRUNCATE TABLE " + tableName);
            System.out.println("Таблица была успешно очищена");
            statement.executeUpdate("ROLLBACK");
        } catch (SQLException e) {
            System.out.println("Неудачное подключение к базе");
            e.printStackTrace();
        }
    }

    public void dropTable() {
        DatabaseMetaData md = null;
        try (Statement statement = Util.connection().createStatement()) {
            md = Util.connection().getMetaData();
            ResultSet rs1 = md.getTables(null, null, tableName, null);
            if (rs1.next()) {
                statement.executeUpdate("START TRANSACTION");
                statement.executeUpdate("DROP TABLE " + tableName);
                System.out.println("Таблица была успешно удалена");
                statement.executeUpdate("ROLLBACK");
            } else if (!rs1.next()) {
                System.out.println("Таблицы не существует");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

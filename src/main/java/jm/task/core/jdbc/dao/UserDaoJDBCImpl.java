package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final String tableName = "USERSDB";

    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {

    }

    @Override
    public void dropUsersTable() {

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }
//
//    private static final String userName = "root";
//    private static final String pass = "SQLserverPass";
//    private static final String url = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
//    private final Connection connection = Util.connection();
//
//    public void createUsersTable() {
//
//        // создаем таблицу если ее еще не существует
//        String usersTable = "CREATE TABLE " + tableName + " ("
//                + "id BIGINT(64) NOT NULL AUTO_INCREMENT,"
//                + "name VARCHAR(64),"
//                + "lastName VARCHAR(64),"
//                + "age TINYINT(4), "
//                + "PRIMARY KEY(id))";
//        try (Statement statement = connection.createStatement()) {
//            DatabaseMetaData md1 = connection.getMetaData();
//            ResultSet rs1 = md1.getTables(null, null, tableName, null);
//            if (!rs1.next()) {
//                connection.setAutoCommit(false);
//
//                statement.executeUpdate(usersTable);
//                System.out.println("Таблица была успешно создана");
//                connection.commit();
//            } else {
//                System.out.println("Такая таблица уже существует");
//            }
//        } catch (SQLException e) {
//            try {
//                connection.rollback();
//            } catch (SQLException ex) {
//                System.out.println("во время отката произошло исключение");
//                ex.printStackTrace();
//            }
//            System.out.println("Неудачное подключение к базе");
//            e.printStackTrace();
//        }
//    }
//
//    public void dropUsersTable() {
//        // удаление таблицы
//        try (Statement statement = connection.createStatement()) {
//            DatabaseMetaData md1 = connection.getMetaData();
//            ResultSet rs1 = md1.getTables(null, null, tableName, null);
//            if (rs1.next()) {
//                connection.setAutoCommit(false);
//
//                statement.executeUpdate("DROP TABLE " + tableName);
//                System.out.println("Таблица была успешно удалена");
//                connection.commit();
//            } else {
//                System.out.println("Такой таблицы не существует");
//            }
//        } catch (SQLException e) {
//            try {
//                connection.rollback();
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//            System.out.println("Неудачное подключение к базе");
//            e.printStackTrace();
//        }
//    }
//
//    public void saveUser(String name, String lastName, byte age) {
//        // создаем текстовый шаблон запроса
//        String sqlInsert = "INSERT INTO " + tableName + " SET" + " name = " + "'" + name + "'" +
//                ", lastName = " + "'" + lastName + "'" + ", age = " + "'" + age + "'";
//
//        // делаем запрос к базе
//        try (Statement statement1 = connection.createStatement()) {
//            connection.setAutoCommit(false);
//            statement1.executeUpdate(sqlInsert);
//            System.out.println("User с именем – " + name + " добавлен в базу данных");
//            connection.commit();
//
//        } catch (SQLException e) {
//            try {
//                connection.rollback();
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//            System.out.println("Неудачное подключение к базе");
//            e.printStackTrace();
//        }
//    }
//
//    public void removeUserById(long id) {
//        try (Statement statement = connection.createStatement()) {
//            connection.setAutoCommit(false);
//            statement.executeUpdate("DELETE FROM " + tableName + " WHERE id=" + id);
//            System.out.println("удален пользователь с id " + id);
//            connection.commit();
//        } catch (SQLException e) {
//            try {
//                connection.rollback();
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//            System.out.println("Неудачное подключение к базе");
//            e.printStackTrace();
//        }
//    }
//
//    public List<User> getAllUsers() {
//        List<User> resultList = new ArrayList<>();
//        try (Statement statement = connection.createStatement()) {
//            connection.setAutoCommit(false);
//            ResultSet result = statement.executeQuery("SELECT * FROM " + tableName);
//            connection.commit();
//            while (result.next()) {
//                User tempUser = new User();
//                tempUser.setId(result.getLong("id"));
//                tempUser.setName(result.getString("name"));
//                tempUser.setLastName(result.getString("lastName"));
//                tempUser.setAge(result.getByte("age"));
//                resultList.add(tempUser);
//
//
//            }
//            for (User u : resultList) {
//                System.out.println(u.toString());
//            }
//        } catch (SQLException e) {
//            try {
//                connection.rollback();
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//            System.out.println("Неудачное подключение к базе");
//            e.printStackTrace();
//        }
//        return resultList;
//
//    }
//
//    public void cleanUsersTable() {
//        try (Statement statement = connection.createStatement()) {
//            connection.setAutoCommit(false);
//            statement.executeUpdate("TRUNCATE TABLE " + tableName);
//            System.out.println("Таблица была успешно очищена");
//            connection.commit();
//        } catch (SQLException e) {
//            try {
//                connection.rollback();
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//            System.out.println("Неудачное подключение к базе");
//            e.printStackTrace();
//        }
//    }
//
//
}

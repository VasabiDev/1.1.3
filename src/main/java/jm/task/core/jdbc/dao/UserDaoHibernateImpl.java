package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static final String tableName = "USERSDB";
    public UserDaoHibernateImpl() {

    }

    private final SessionFactory sessionFactory = Util.getSessionFactory();


    @Override
    public void createUsersTable() {
        String usersTable = "CREATE TABLE " + tableName + " ("
                + "id BIGINT(64) NOT NULL AUTO_INCREMENT,"
                + "name VARCHAR(64),"
                + "lastName VARCHAR(64),"
                + "age TINYINT(4), "
                + "PRIMARY KEY(id))";
        try( Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery(usersTable).executeUpdate();
            System.out.println("Таблица была успешно создана");
            session.getTransaction().commit();
        }catch (Exception e){

        }





    }

    @Override
    public void dropUsersTable() {
        try( Session session = sessionFactory.openSession()) {

        }catch (Exception e){

        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sqlInsert = "INSERT INTO " + tableName + " SET" + " name = " + "'" + name + "'" +
                ", lastName = " + "'" + lastName + "'" + ", age = " + "'" + age + "'";

        try( Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery(sqlInsert).executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
            session.getTransaction().commit();
        }catch (Exception e){

        }


    }

    @Override
    public void removeUserById(long id) {
        try( Session session = sessionFactory.openSession()) {
            User tempUser = (User) session.load(User.class, id);
            if(tempUser!=null){
                session.delete(tempUser);
            }
            System.out.println("удален пользователь с id " + id);
        }catch (Exception e){

        }

    }

    @Override
    public List<User> getAllUsers() {

        List<User> resultList = new ArrayList<>();
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String queryStr = "SELECT USER FROM "+tableName+" USER";
            resultList = session.createQuery(queryStr).list();

            session.getTransaction().commit();

            for (User u : resultList) {
                System.out.println(u.toString());
            }
        }catch (Exception e){
        }
        return resultList;
    }

    @Override
    public void cleanUsersTable() {
        try( Session session = sessionFactory.openSession()) {

        }catch (Exception e){

        }

    }
}

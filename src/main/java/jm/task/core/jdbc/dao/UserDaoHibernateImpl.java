package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static final String tableName = "USERSDB";

    public UserDaoHibernateImpl() {
    }

    private final SessionFactory sessionFactory = Util.getSessionFactory();

    @Override
    public void createUsersTable() {

        String usersTable = "CREATE TABLE IF NOT EXISTS " + tableName + " ("
                + "id BIGINT(64) NOT NULL AUTO_INCREMENT,"
                + "name VARCHAR(64),"
                + "lastName VARCHAR(64),"
                + "age TINYINT(4), "
                + "PRIMARY KEY(id))";
        try (Session session = sessionFactory.openSession()) {

            session.beginTransaction();
            session.createSQLQuery(usersTable).executeUpdate();
            System.out.println("Таблица была успешно создана");
            session.getTransaction().commit();
        } catch (Exception e) {
            sessionFactory.getCurrentSession().getTransaction().rollback();

        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS " + tableName).executeUpdate();
            System.out.println("Таблица успешно удалена");
            session.getTransaction().commit();
        } catch (Exception e) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sqlInsert = "INSERT INTO " + tableName + " SET" + " name = " + "'" + name + "'" +
                ", lastName = " + "'" + lastName + "'" + ", age = " + "'" + age + "'";

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery(sqlInsert).executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
            session.getTransaction().commit();
        } catch (Exception e) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User tempUser = (User) session.load(User.class, id);
            if (tempUser != null) {
                session.delete(tempUser);
            }
            session.getTransaction().commit();
            System.out.println("удален пользователь с id " + id);
        } catch (Exception e) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
        }
    }

    @Override
    public List<User> getAllUsers() {

        List<User> resultList = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteria = builder.createQuery(User.class);
            criteria.from(User.class);
            resultList = session.createQuery(criteria).getResultList();
            session.getTransaction().commit();
            for (User u : resultList) {
                System.out.println(u.toString());
            }
        } catch (Exception e) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
        }
        return resultList;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE " + tableName).executeUpdate();
            System.out.println("Таблица была успешно очищена");
            session.getTransaction().commit();
        } catch (Exception e) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
        }
    }
}

package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static final String tableName = "USERSDB";
    public UserDaoHibernateImpl() {

    }
private final SessionFactory sessionFactory = (SessionFactory) Util.connection();


    @Override
    public void createUsersTable() {
        Session session = this.sessionFactory.getCurrentSession();

    }

    @Override
    public void dropUsersTable() {
        Session session = this.sessionFactory.getCurrentSession();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(name);
        session.persist(lastName);
        session.persist(age);
        System.out.println("User с именем – " + name + " добавлен в базу данных");
    }

    @Override
    public void removeUserById(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        User tempUser = (User) session.load(User.class, id);
        if(tempUser!=null){
            session.delete(tempUser);
        }
        System.out.println("удален пользователь с id " + id);

    }

    @Override
    public List<User> getAllUsers() {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> resultList = session.createSQLQuery(tableName).list();
        for (User u : resultList) {
            System.out.println(u.toString());
        }
        return resultList;
    }

    @Override
    public void cleanUsersTable() {

    }
}

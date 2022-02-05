package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao userDaoJDBC = new UserDaoJDBCImpl();
    private final UserDao userDaoJDBCHibernate = new UserDaoHibernateImpl();

    public void createUsersTable() {
        userDaoJDBCHibernate.createUsersTable();
        //userDaoJDBC.createUsersTable();
    }

    public void dropUsersTable() {

        userDaoJDBCHibernate.dropUsersTable();
        //userDaoJDBC.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDaoJDBCHibernate.saveUser(name, lastName, age);
        //userDaoJDBC.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        userDaoJDBCHibernate.removeUserById(id);
        //userDaoJDBC.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userDaoJDBCHibernate.getAllUsers();

        //return userDaoJDBC.getAllUsers();
    }

    public void cleanUsersTable() {
        userDaoJDBCHibernate.cleanUsersTable();
        //userDaoJDBC.cleanUsersTable();
    }


}

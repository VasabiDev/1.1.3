package jm.task.core.jdbc.util;



import java.util.Properties;
import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;

public class Util {


        private static final SessionFactory sessionFactory;
        static {
            try {
                Properties prop= new Properties();
                prop.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/test?serverTimezone=UTC");
                prop.setProperty("hibernate.connection.username", "root");
                prop.setProperty("hibernate.connection.password", "SQLserverPass");
                prop.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");

                sessionFactory = new Configuration()
                        .addPackage("com.concreteness.persistence")
                        .addProperties(prop)
                        .addAnnotatedClass(User.class)
                        .buildSessionFactory();
            } catch (Throwable ex) {
                throw new ExceptionInInitializerError(ex);
            }
        }
        public static SessionFactory getSessionFactory()
            throws HibernateException {
            return sessionFactory;
        }

}

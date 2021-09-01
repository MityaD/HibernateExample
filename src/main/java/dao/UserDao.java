package dao;

import models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;
import java.util.List;

public class UserDao {

    public User findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(User.class, id);
    }
    public void deleteUserId(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(findById(id));
        tx1.commit();
        session.close();
    }

    public void addUser(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

    public void update(int id, String lastName, String firstName, int age) {
        User newUser = new User(id, lastName, firstName, age);
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
//        session.delete(findById(id));
        session.save(newUser);
        tx1.commit();
        session.close();
    }

    public void deleteUser(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
        session.close();
    }

    public void deleteAllUser() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        List<User> users1 = (List<User>)  HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .createQuery("From User")
                .list();
        for (User user : users1) {
            session.delete(user);
        }
        tx1.commit();
        session.close();
    }



    public List<User> findAllUser() {
        List<User> users1 = (List<User>)  HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .createQuery("From User")
                .list();
        return users1;
    }
}
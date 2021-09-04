package dao;

import models.Address;
import models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateSessionFactoryUtil;
import java.util.List;

public class UserDao {

    public User getUserById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(User.class, id);
    }
    public void deleteUserById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(getUserById(id));
        tx1.commit();
        session.close();
    }

    public void addUserAndAddress(User user, Address address) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        session.save(address);
        tx1.commit();
        session.close();
    }
    public void update(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    public void updateUsersById(int id, String lastName, String firstName, int age) {
        User newUser = new User(id, lastName, firstName, age);
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
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

    public List<User> getAllUser() {
        List<User> users1 = (List<User>)  HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .createQuery("From User")
                .list();
        return users1;
    }

    public List<User> getUserByHouseNumber(String house) {
        Query query = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(
                "SELECT u FROM User u, Address a " +
                "WHERE a.house = '" + house + "'");
        List<User> users1 = query
                .list();
        return users1;
    }
}
package dao;

import models.Address;
import models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Logger;

public class UserDao {
    Logger logger = Logger.getLogger(UserDao.class.getName());

    public User getUserById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(User.class, id);
    }

    public void deleteUserById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(getUserById(id));
        logger.info( dataTime() + "Пользователь c id: " + id + " удален!!! ");
        tx1.commit();
        session.close();
    }

    public void addUserAndAddress(User user, Address address) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        session.save(address);
        logger.info(dataTime() + "FirstName: " + user.getFirstName()
                + "; LastName: " + user.getLastName()
                + "; Age: " + user.getAge()
                + "; City: " + address.getCity()
                + "; Street: " + address.getStreet()
                + "; House: " + address.getHouse() + "    Добавлен в БД!!!");
        tx1.commit();
        session.close();
    }

    public void update(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        logger.info(dataTime() + "FirstName: " + user.getFirstName()
                + "; LastName: " + user.getLastName()
                + "; Age: " + user.getAge()
                + "; City: " + user.getAddress().getCity()
                + "; Street: " + user.getAddress().getStreet()
                + "; House: " + user.getAddress().getHouse() + "    Обновлен в БД!!!");
        tx1.commit();
        session.close();
    }

    public void updateUsersById(int id, String lastName, String firstName, int age) {
        User newUser = new User(id, lastName, firstName, age);
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(newUser);
        logger.info(dataTime() + "FirstName: " + newUser.getFirstName()
                + "; LastName: " + newUser.getLastName()
                + "; Age: " + newUser.getAge()
                + "; City: " + newUser.getAddress().getCity()
                + "; Street: " + newUser.getAddress().getStreet()
                + "; House: " + newUser.getAddress().getHouse() + "    Пользователь с id: " + id + " обновлен!!!");
        tx1.commit();
        session.close();
    }

    public void deleteUser(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        logger.info(dataTime() + "FirstName: " + user.getFirstName()
                + "; LastName: " + user.getLastName()
                + "; Age: " + user.getAge()
                + "; City: " + user.getAddress().getCity()
                + "; Street: " + user.getAddress().getStreet()
                + "; House: " + user.getAddress().getHouse() + "    Пользователь удален!!!");
        tx1.commit();
        session.close();
    }

    public void deleteAllUser() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        List<User> users1 = (List<User>) HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .createQuery("From User")
                .list();
        for (User user : users1) {
            session.delete(user);
        }
        logger.info(dataTime() + "Все пользователи удалены!!!");
        tx1.commit();
        session.close();
    }

    public List<User> getAllUser() {
        List<User> users1 = (List<User>) HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .createQuery("From User")
                .list();
        for (User user : users1) {
            logger.info("FirstName: " + user.getFirstName()
                    + "; LastName: " + user.getLastName()
                    + "; Age: " + user.getAge()
                    + "; City: " + user.getAddress().getCity()
                    + "; Street: " + user.getAddress().getStreet()
                    + "; House: " + user.getAddress().getHouse() + "Получен пользователь!!!");
        }
        return users1;
    }

    public List<User> getUserByHouseNumber(String house) {
        List<User> users = (List<User>) HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .createQuery(
                "SELECT u FROM User u WHERE u.address.house = '" + house + "'").list();
        for (User user : users) {
            logger.info("FirstName: " + user.getFirstName()
                            + "; LastName: " + user.getLastName()
                            + "; Age: " + user.getAge()
                            + "; City: " + user.getAddress().getCity()
                            + "; Street: " + user.getAddress().getStreet()
                            + "; House: " + user.getAddress().getHouse() + "Получен пользователи из дома № " + house);
        }
        return users;
    }

    public String  dataTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("d MMMM, yyyy HH:mm:ss"));
    }
}
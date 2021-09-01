
import dao.UserDao;
import models.User;

public class Main {
    public static void main(String[] args) {
        User user1 = new User("Dima", "Yakut", 32);
        User user2 = new User("Lazic", "Sania", 29);
        User user3 = new User("Alex", "Sania", 35);
        User user4 = new User("Churic", "Dishko", 29);
        User user5 = new User(2,"Churic21212", "Dishko21212", 292);

        UserDao userDao = new UserDao();

        userDao.addUser(user1);
        userDao.addUser(user2);
        userDao.addUser(user3);
        userDao.addUser(user4);

//        userDao.deleteUserId(3);
//
//        System.out.println(userDao.findById(2));
//
//        System.out.print(userDao.findAllUser());
//
//        userDao.deleteUser(user1);
//
//        userDao.deleteAllUser();

        userDao.update(2,"Churic21212", "Dishko21212", 292);

    }
}

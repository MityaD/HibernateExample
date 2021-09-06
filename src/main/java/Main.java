
import dao.UserDao;
import models.Address;
import models.User;

public class Main {
    public static void main(String[] args) {
        User user1 = new User("Dima", "Yakut", 32);
        User user2 = new User("Lazic", "Sania", 29);
        User user3 = new User("Alex", "Sania", 35);
        User user4 = new User("Churic", "Dishko", 29);

        Address address1 = new Address("Bereza1", "Pupkina1", "400");
        Address address2 = new Address("Bereza2", "Pupkina2", "41");
        Address address3 = new Address("Bereza3", "Pupkina3", "402");
        Address address4 = new Address("Bereza4", "Pupkina4", "400");

        UserDao userDao = new UserDao();

        address1.setUser(user1);
        userDao.addUserAndAddress(user1, address1);
        address2.setUser(user2);
        userDao.addUserAndAddress(user2, address2);
        address3.setUser(user3);
        userDao.addUserAndAddress(user3, address3);
        address4.setUser(user4);
        userDao.addUserAndAddress(user4, address4);

        //userDao.getUserById(2);

        //userDao.deleteUserById(2);

        userDao.getUserByHouseNumber("400");
    }
}
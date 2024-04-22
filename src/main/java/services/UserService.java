package services;

import dao.UserDao;
import general.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {

    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void createUser(Long id, String name) {
        userDao.createNewUser(id, name);
    }

    public void deleteUser(Long id) {
        userDao.deleteUserById(id);
        System.out.println("User Deleted");
    }

    public User getUser(Long id) {
        System.out.println("Get user");
        return userDao.getUserById(id);
    }

    public List<User> getUsers() {
        System.out.println("Get all users");
        return userDao.getAllUsers();
    }

    public void testMethod() {
        System.out.println("Just a test");
    }
}

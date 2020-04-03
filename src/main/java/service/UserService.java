package service;

import model.User;

import java.util.List;

public interface UserService {

    User getUserById(long id);

    List<User> getAllUsers();

    boolean updateUser(User user);

    boolean deleteUser(User user);

    boolean addUser(User user);

    boolean validateInputData(String senderName);

    boolean validateUpdateUser(long id, String name);
}

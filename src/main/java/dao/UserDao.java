package dao;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    List<User> getAllUsers() throws SQLException;

    boolean validateUser(String name) throws SQLException;

    User getUserById(long id) throws SQLException;

    void addUser(User user) throws SQLException;

    void updateUser(User user) throws SQLException;

    void deleteUser(User user) throws SQLException;

    boolean validateUpdateUser(long id, String name) throws SQLException;
}

package dao;

import modelEntity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface UserDao {
    List<User> getAllUsers() throws SQLException;

    boolean validateUser(String name, String password) throws SQLException;

    User getUserById(long id) throws SQLException;

    void addUser(User user) throws SQLException;

    void UpdateUser(User user) throws SQLException;

    void deleteUser(User user) throws SQLException;

    void createTable() throws SQLException;

    void dropTable() throws SQLException;

    long getMaxID() throws SQLException;

    boolean IsUserByNameAndPassword(long id, String name, String password) throws SQLException;
}

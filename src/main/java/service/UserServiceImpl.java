package service;

import dao.DaoFactory;
import dao.UserDao;
import dao.UserHibernateDAO;
import model.User;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * class for data that's later must be performed in view
 */
public class UserServiceImpl implements UserService{

    private UserDao userDao;

    /**
     * singlton realisation
     */
    private UserServiceImpl() {
        try {
            userDao = DaoFactory.getInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private static class UserServiceImplHolder {
        private final static UserServiceImpl instance = new UserServiceImpl();
    }

    public static UserServiceImpl getInstance() {
        return UserServiceImplHolder.instance;
    }

    private UserDao getUserDAO() {
        return userDao;
    }

    public User getUserById(long id) {
        try {
            return getUserDAO().getUserById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean validateUpdateUser(long id, String name) {
        try {
            return getUserDAO().validateUpdateUser(id, name);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return true;
        }
    }

    public List<User> getAllUsers() {
        try {
            return getUserDAO().getAllUsers();
        } catch (SQLException ex) {
            return null;
        }
    }

    public boolean updateUser(User user) {
        try {
            if (user == null || checkEmptyUserFields(user) || validateUpdateUser(user.getId(), user.getName())) {
                return false;
            }
            getUserDAO().updateUser(user);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(User user) {
        try {
            if (getUserDAO().getUserById(user.getId()) != null) {
                getUserDAO().deleteUser(user);
                return true;
            }
            return false;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean addUser(User user) {
        if (checkEmptyUserFields(user) || validateInputData(user.getName())) {
            return false;
        }
        try {
            getUserDAO().addUser(user);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private boolean checkEmptyUserFields(User user) {
        return (user.getName().isEmpty() || user.getPassword().isEmpty());
    }

    public boolean validateInputData(String senderName) {
        try {
            return getUserDAO().validateUser(senderName);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}

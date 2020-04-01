package model;

import dao.UserDao;
import dao.UserHibernateDAO;
import dao.UserJDBCDao;
import modelEntity.User;
import org.hibernate.SessionFactory;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * class for data that's later must be performed in view
 */
public class UserService {

    private static Connection mySqlConnection;

    private static SessionFactory sessionFactory;

    private List<User> data;

    /**
     * singlton realisation
     */
    private UserService() {
        try {
            data = new ArrayList<User>();
            //mySqlConnection = createMysqlConnection();
            sessionFactory = HibernateConnector.getSessionFactory();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static class DataModelHolder {
        private final static UserService instance = new UserService();
    }

    public static UserService getInstance() {
        return DataModelHolder.instance;
    }

    /**
     * other methods overview
     */
    private static Connection createMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("smv?").          //db name db_example?
                    append("user=belob&").          //login root
                    append("password=admin1993");       //password root

            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    private static Connection getMySqlConnection() {
        if (mySqlConnection == null) {
            createMysqlConnection();
        }
        return mySqlConnection;
    }

    private static UserDao getUserDAO() {
        //return new UserJDBCDao(getMySqlConnection());
        return new UserHibernateDAO(sessionFactory.openSession());
    }

    public User getUserById(long id) {
        try {
            return getUserDAO().getUserById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public long configNewId() {
        try {
            return getUserDAO().getMaxID() + 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private boolean validateUserById(long id, String name, String password) {
        try {
            return getUserDAO().IsUserByNameAndPassword(id, name, password);
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
        UserDao dao = getUserDAO();
        try {
            if (user == null || checkEmptyUserFields(user) || validateUserById(user.getId(), user.getName(), user.getPassword())) {
                return false;
            }
            dao.UpdateUser(user);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(User user) {
        UserDao dao = getUserDAO();
        try {
            if (dao.getUserById(user.getId()) != null) {
                dao.deleteUser(user);
                return true;
            }
            return false;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean addUser(User user) {
        UserDao dao = getUserDAO();
        if (checkEmptyUserFields(user) || validateInputData(user.getName(), user.getPassword())) {
            return false;
        }
        try {
            if (dao.getUserById(user.getId()) == null) {
                dao.addUser(user);
                return true;
            }
            return false;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }


    private boolean checkEmptyUserFields(User user) {
        return (user.getName().isEmpty() || user.getPassword().isEmpty());
    }

    public void clearUsers() {
        UserDao dao = getUserDAO();
        try {
            dao.dropTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable() {
        UserDao dao = getUserDAO();
        try {
            dao.createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean validateInputData(String senderName, String senderPass) {
        try {
            return getUserDAO().validateUser(senderName, senderPass);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}

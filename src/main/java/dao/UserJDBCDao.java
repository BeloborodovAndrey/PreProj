package dao;

import dao.executor.Executor;
import model.User;
import util.DBHelper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for users sql queries
 */
public class UserJDBCDao implements UserDao {

    private Executor executor;

    private Connection sqlConnection;

    public UserJDBCDao(Connection connection) {
        sqlConnection = DBHelper.createMysqlConnection();
        this.executor = new Executor(connection);
    }

    public List<User> getAllUsers() throws SQLException {
        return executor.execQuery("select * from users", result -> {
            List<User> resultList = new ArrayList<User>();
            while (result.next()) {
                resultList.add(new User(result.getLong(1), result.getString(2),
                        result.getString(3)));
            }
            return resultList;
        });
    }

    public boolean validateUser(String name) throws SQLException {
        return executor.execQuery("select * from users where name = '" + name + "'", result -> result.next());
    }

    public User getUserById(long id) throws SQLException {
        return executor.execQuery("select * from users where id = " + id, result -> {
            if(result.next()) {
                return new User(result.getLong(1), result.getString(2),
                        result.getString(3));
            }
                return null;
        });
    }

    public void addUser(User user) throws SQLException {
        executor.execUpdate("insert into users (id, name, password) values (" + user.getId() + ", '"+ user.getName() +
                "', '" + user.getPassword() + "')");
    }

    public void updateUser(User user) throws SQLException {
        executor.execUpdateProtected("update users set name = '" + user.getName() + "', password = '" +
                user.getPassword() + "' where id = " + user.getId());
    }

    public void deleteUser(User user) throws SQLException {
        executor.execUpdateProtected("delete from users where name = '" + user.getName() + "'");
    }

    public void createTable() throws SQLException {
        executor.execUpdate("create table if not exists users " +
                "(id bigint auto_increment, name varchar(256), password varchar(256), primary key (id));");
    }

    public void dropTable() throws SQLException {
        executor.execUpdate("DROP TABLE IF EXISTS users");
    }

    public long getMaxID() throws SQLException {
        return executor.execQuery("select max(id) from users", resultSet -> {
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
            return Long.valueOf(0);
        });
    }

    @Override
    public boolean validateUpdateUser(long id, String name) throws SQLException {
        return executor.execQuery("select * from users where id != " + id +" and name = '" + name +"'", result -> {
            return result.next();

        });
    }
}

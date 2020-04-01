package dao;

import modelEntity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class UserHibernateDAO implements UserDao {

    private Session session;

    public UserHibernateDAO(Session session) {
        this.session = session;
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        List<User> users = session.createQuery("FROM User").list();
        session.close();
        return users;
    }

    @Override
    public boolean validateUser(String name, String password) throws SQLException {
        Query query = session.createQuery("FROM User where name = :name or password = :password");
        query.setParameter("name", name);
        query.setParameter("password", password);
        session.close();
        return query.list().size() > 0;
    }

    @Override
    public User getUserById(long id) throws SQLException {
        User user = session.get(User.class, id);
        session.close();
        return user;
    }

    @Override
    public void addUser(User user) throws SQLException {
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void UpdateUser(User user) throws SQLException {
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteUser(User user) throws SQLException {
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void createTable(){};

    @Override
    public void dropTable(){};

    @Override
    public long getMaxID() {
        long id = ((User) session.createQuery("select max(id) FROM User").getSingleResult()).getId();
        session.close();
        return id;
    }

    @Override
    public boolean IsUserByNameAndPassword(long id, String name, String password) {
        Query query = session.createQuery("FROM User where id != :id and (name = :name or password = :password)");
        query.setParameter("id", id);
        query.setParameter("name", name);
        query.setParameter("password", password);
        session.close();
        return query.list().size() > 0;
    }
}

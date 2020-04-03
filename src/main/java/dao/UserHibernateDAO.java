package dao;

import model.User;
import org.hibernate.*;
import util.DBHelper;

import java.sql.SQLException;
import java.util.List;

public class UserHibernateDAO implements UserDao {

    private SessionFactory sessionFactory;

    public UserHibernateDAO() {
        this.sessionFactory = DBHelper.getSessionFactory();
    }

    @Override
    public List<User> getAllUsers(){
            Session session = sessionFactory.openSession();
            List<User> users = session.createQuery("FROM User").list();
            session.close();
            return users;
    }

    @Override
    public boolean validateUser(String name) throws SQLException {
        int size;
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM User where name = " + name);
        size = query.list().size();
        session.close();
        return size > 0;
    }

    @Override
    public User getUserById(long id) throws SQLException {
        Session session = sessionFactory.openSession();
        User user = (User) session.get(User.class, id);
        session.close();
        return user;
    }

    @Override
    public void addUser(User user) throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(user);
            transaction.commit();
            session.close();
        } catch (HibernateException ex) {
            transaction.rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(user);
            transaction.commit();
            session.close();
        } catch (HibernateException ex) {
            transaction.rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteUser(User user) throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(user);
            transaction.commit();
            session.close();
        } catch (HibernateException ex) {
            transaction.rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public boolean validateUpdateUser(long id, String name) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM User where id != :id and name = :name");
        query.setParameter("id", id);
        query.setParameter("name", name);
        int size = query.list().size();
        session.close();
        return size > 0;
    }
}

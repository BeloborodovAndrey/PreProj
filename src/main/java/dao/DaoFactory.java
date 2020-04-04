package dao;


import java.io.*;
import java.lang.reflect.Constructor;
import java.util.Properties;

/**
 * class for DAO receiving
 */
public class DaoFactory {

    private static String daoType;
    private final String KEY = "daotype";

    private DaoFactory() throws IOException {
        Properties property = new Properties();
        String path = getClass().getClassLoader().getResource("config.properties").getPath();
        property.load(new FileInputStream(path));
        daoType = property.getProperty(KEY);
    }

    public static UserDao getInstance() throws IOException {
        new DaoFactory();
        try {
            Class dao = Class.forName(daoType);
            Constructor constructor = dao.getConstructor();
            constructor.setAccessible(true);
            return (UserDao) constructor.newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}


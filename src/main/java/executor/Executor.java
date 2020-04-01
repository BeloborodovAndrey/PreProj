package executor;

import java.sql.*;

/**
 * @author belob
 */
public class Executor {
    public final Connection connection;

    public Executor(Connection connection) {
        this.connection = connection;
    }

    public void execUpdate(String update) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(update);
        } catch (SQLException ex) {
            connection.rollback();
        }
    }

    public void execUpdateProtected(String query) throws SQLException {
        connection.setAutoCommit(false);
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            connection.setAutoCommit(true);
        }
    }

    public <T> T execQueryProtected(String query, String name, ResultHandler<T> handler)
            throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.executeQuery();
            ResultSet result = stmt.getResultSet();
            T value = handler.handle(result);
            result.close();
            return value;
        } catch (SQLException ex) {
            connection.rollback();
            return null;
        }
    }

    public <T> T execQuery(String query,
                           ResultHandler<T> handler)
            throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(query);
            ResultSet result = stmt.getResultSet();
            T value = handler.handle(result);
            result.close();
            return value;
        }
    }

}

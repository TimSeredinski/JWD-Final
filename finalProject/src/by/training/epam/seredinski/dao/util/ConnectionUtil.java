package by.training.epam.seredinski.dao.util;

import by.training.epam.seredinski.exception.DaoException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionUtil {

    private final static Logger logger = Logger.getLogger(ConnectionUtil.class);

    public static void rollBackConnection(Connection connection) throws DaoException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public static void closeConnection(ResultSet resultSet, PreparedStatement statement, Connection connection) throws DaoException{
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "ResultSet isn't closed.");
        }
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Statement isn't closed.");
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Connection isn't return to the pool. ");
        }
    }
}

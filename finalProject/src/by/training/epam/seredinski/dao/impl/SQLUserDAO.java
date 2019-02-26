package by.training.epam.seredinski.dao.impl;

import by.training.epam.seredinski.dao.util.ConnectionUtil;
import by.training.epam.seredinski.exception.DaoException;
import by.training.epam.seredinski.dao.UserDAO;
import by.training.epam.seredinski.entity.User;

import java.sql.*;

public class SQLUserDAO implements UserDAO {

    private static final String CREATE_USER = "INSERT INTO users (name, surname, mail, login, password) VALUES (?,?,?,?,?)";
    private static final String QUERY_CHECK_CREDENTIONALS = "SELECT * FROM users WHERE login=? and password=?";

    @Override
    public User authentification(String userLogin, String userPassword) throws DaoException {
        User user = null;
        Connection connection = ConnectionPool.getInstance().takeConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(QUERY_CHECK_CREDENTIONALS);
            statement.setString(1, userLogin);
            statement.setString(2, userPassword);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = createUser(resultSet);
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionUtil.closeConnection(resultSet, statement, connection);
        }

        return user;
    }


    private User createUser(ResultSet rs) throws SQLException {
        User user = new User();

        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        //....

        return user;

    }

    @Override
    public void registration(User userData) throws DaoException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(CREATE_USER);
            statement.setString(1, userData.getName());
            statement.setString(2, userData.getSurname());
            statement.setString(3, userData.getMail());
            statement.setString(4, userData.getLogin());
            statement.setString(5, userData.getPassword());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            ConnectionUtil.rollBackConnection(connection);
            throw new DaoException(e);
        } finally {
            ConnectionUtil.closeConnection(resultSet, statement, connection);
        }

    }


}

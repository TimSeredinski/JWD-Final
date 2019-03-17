package by.training.epam.seredinski.dao.impl;

import by.training.epam.seredinski.dao.UserDAO;
import by.training.epam.seredinski.entity.User;
import by.training.epam.seredinski.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLUserDAO implements UserDAO {

    protected static final String CREATE_USER = "INSERT INTO users (name, surname, mail, login, password) VALUES (?,?,?,?,?)";
    protected static final String QUERY_CHECK_CREDENTIALS = "SELECT * FROM users WHERE login=? and password=?";

    @Override
    public User authentification(String userLogin, String userPassword) throws DaoException {
        User user = null;
        Connection connection = ConnectionPool.getInstance().takeConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(QUERY_CHECK_CREDENTIALS);
            statement.setString(1, userLogin);
            statement.setString(2, userPassword);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = createUser(resultSet); // !!! Переделать
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getInstance().closeConnection(connection, statement, resultSet);
        }

        return user;
    }


    private User createUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setSurname(resultSet.getString("surname"));
        user.setMail(resultSet.getString("mail"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setRole(User.UserRole.valueOf(resultSet.getString("role")));
        return user;

    }

    @Override
    public void registration(User userData) throws DaoException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        PreparedStatement statement = null;

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
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            throw new DaoException(e);
        } finally {
            ConnectionPool.getInstance().closeConnection(connection, statement);
        }

    }


}

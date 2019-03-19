package by.training.epam.seredinski.dao.impl;

import by.training.epam.seredinski.dao.OrderDAO;
import by.training.epam.seredinski.entity.Dish;
import by.training.epam.seredinski.entity.Order;
import by.training.epam.seredinski.exception.DaoException;
import org.apache.log4j.Logger;

import java.sql.*;

public class SQLOrderDAO implements OrderDAO {

    private static final Logger logger = Logger.getLogger(SQLOrderDAO.class);


    protected static final String CREATE_ORDER = "INSERT INTO orders (date, userId, addressId) VALUES (?,?,?)";
    protected static final String CREATE_ORDER_DISH = "INSERT INTO order_dish (orderId, dishId, count_dishes) VALUES (?,?,?)";

    @Override
    public void save(Order order) throws DaoException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(CREATE_ORDER, Statement.RETURN_GENERATED_KEYS);
            statement.setTimestamp(1, new Timestamp(order.getDateTime().getTimeInMillis()));
            statement.setInt(2, order.getUserId());
            statement.setInt(3, order.getAddressId());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                order.setId(resultSet.getInt(1));
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DaoException("Exception in SQLOrderDAO.save()", e);
            }
            logger.error("Exception in SQLOrderDAO.save()", e);
            throw new DaoException("Exception in SQLOrderDAO.save()", e);
        } finally {
            ConnectionPool.getInstance().closeConnection(connection, statement);
        }
        connection = ConnectionPool.getInstance().takeConnection();
        try {
            connection.setAutoCommit(false);
            for (Dish dish : order.getDishes()) {
                try (PreparedStatement insertOrderDishPs = connection.prepareStatement(CREATE_ORDER_DISH)) {
                    insertOrderDishPs.setInt(1, order.getId());
                    insertOrderDishPs.setInt(2, dish.getId());
                    insertOrderDishPs.setInt(3, dish.getAmount());
                    insertOrderDishPs.executeUpdate();
                }
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DaoException("Exception in SQLOrderDAO.save()", e);
            }
            logger.error("Exception in SQLOrderDAO.save()", e);
            throw new DaoException("Exception in SQLOrderDAO.save()", e);
        } finally {
            ConnectionPool.getInstance().closeConnection(connection, statement);
        }
    }
}

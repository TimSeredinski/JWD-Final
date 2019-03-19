package by.training.epam.seredinski.dao.impl;

import by.training.epam.seredinski.constant.Constants;
import by.training.epam.seredinski.dao.DishDAO;
import by.training.epam.seredinski.entity.Dish;
import by.training.epam.seredinski.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLDishDAO implements DishDAO {

    protected static final String CREATE_DISH = "INSERT INTO dishes (name, description, type, weight, price) VALUES (?,?,?,?,?)";
    protected static final String UPDATE_DISH = "UPDATE dishes SET name=?, description=?, type=?, weight=?, price=? WHERE id=?";
    protected static final String GET_DISHES_BY_TYPE = "SELECT * FROM dishes WHERE type=?";
    protected static final String GET_DISHES_BY_ID = "SELECT * FROM dishes WHERE id=?";

    @Override
    public void createDish(Dish dish) throws DaoException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        PreparedStatement statement = null;

        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(CREATE_DISH);
            insertDishesProperties(statement, dish);
            statement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DaoException("Exception in SQLDishDAO.save()", e);
            }
            throw new DaoException("Exception in SQLDishDAO.save()", e);
        } finally {
            ConnectionPool.getInstance().closeConnection(connection, statement);
        }
    }

    @Override
    public void updateDish(Dish dish) throws DaoException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        PreparedStatement statement = null;

        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(UPDATE_DISH);
            insertDishesProperties(statement, dish);
            statement.setInt(6, dish.getId());
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

    @Override
    public List<Dish> getByType(Dish.DishType type) throws DaoException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Dish> dishes = new ArrayList<>();
        try {
            statement = connection.prepareStatement(GET_DISHES_BY_TYPE);
            statement.setString(1, type.toString());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                dishes.add(getDishWithResultSet(resultSet));
            }
            return dishes;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getInstance().closeConnection(connection, statement, resultSet);
        }
    }

    @Override
    public Dish getById(int id) throws DaoException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Dish dish = null;
        try {
            statement = connection.prepareStatement(GET_DISHES_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                dish = getDishWithResultSet(resultSet);
            }
            return dish;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getInstance().closeConnection(connection, statement, resultSet);
        }
    }

    private Dish getDishWithResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(Constants.PARAMETER_ID);
        String name = resultSet.getString(Constants.PARAMETER_NAME);
        String description = resultSet.getString(Constants.PARAMETER_DESCRIPTION);
        int weight = resultSet.getInt(Constants.PARAMETER_WEIGHT);
        int price = resultSet.getInt(Constants.PARAMETER_PRICE);
        Dish.DishType type = Dish.DishType.valueOf(resultSet.getString(Constants.PARAMETER_TYPE));
        return new Dish(id, name, description, type, weight, price);
    }

    private void insertDishesProperties(PreparedStatement statement, Dish dish) throws SQLException {
        statement.setString(1, dish.getName());
        statement.setString(2, dish.getDescription());
        statement.setString(3, String.valueOf(dish.getType()));
        statement.setInt(4, dish.getWeight());
        statement.setInt(5, dish.getPrice());
    }
}

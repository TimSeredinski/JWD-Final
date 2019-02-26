package by.training.epam.seredinski.dao.impl;

import by.training.epam.seredinski.dao.DishDAO;
import by.training.epam.seredinski.dao.util.ConnectionUtil;
import by.training.epam.seredinski.entity.Dish;
import by.training.epam.seredinski.enums.DishType;
import by.training.epam.seredinski.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLDishDAO implements DishDAO {

    private static final String CREATE_DISH = "INSERT INTO dishes (name, description, type, weight, price) VALUES (?,?,?,?,?)";
    private static final String GET_DISHES_BY_TYPE = "SELECT * FROM dishes WHERE type=?";


    @Override
    public void createDish(Dish dish) throws DaoException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(CREATE_DISH);
            statement.setString(1, dish.getName());
            statement.setString(2, dish.getDescription());
            statement.setString(3, dish.getType().toString());
            statement.setInt(4, dish.getWeight());
            statement.setInt(5, dish.getPrice());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            ConnectionUtil.rollBackConnection(connection);
            throw new DaoException(e);
        } finally {
            ConnectionUtil.closeConnection(resultSet, statement, connection);
        }
    }

    @Override
    public List<Dish> getByType(DishType type) throws DaoException {
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
            ConnectionUtil.closeConnection(resultSet, statement, connection);
        }
    }

    private Dish getDishWithResultSet(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        int weight = resultSet.getInt("weight");
        int price = resultSet.getInt("price");
        DishType type = DishType.valueOf(resultSet.getString("type"));
        return new Dish(name, description, type, weight, price);
    }
}

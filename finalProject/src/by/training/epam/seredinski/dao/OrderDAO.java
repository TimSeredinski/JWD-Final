package by.training.epam.seredinski.dao;

import by.training.epam.seredinski.entity.Order;
import by.training.epam.seredinski.exception.DaoException;

import java.util.List;

public interface OrderDAO {

    void save(Order order) throws DaoException;

    List<Order> getOrdersByUserId(int userId) throws DaoException;

}

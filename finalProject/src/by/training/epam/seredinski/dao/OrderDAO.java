package by.training.epam.seredinski.dao;

import by.training.epam.seredinski.entity.Order;
import by.training.epam.seredinski.exception.DaoException;

public interface OrderDAO {

    void save(Order order) throws DaoException;

}

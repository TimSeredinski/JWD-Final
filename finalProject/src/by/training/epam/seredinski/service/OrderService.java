package by.training.epam.seredinski.service;

import by.training.epam.seredinski.entity.Dish;
import by.training.epam.seredinski.entity.Order;
import by.training.epam.seredinski.exception.ServiceException;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.List;

public interface OrderService {

    void saveOrder(String city, String street, int house, int flat, int userId,
                   Calendar dateTime, LinkedHashSet<Dish> dishes) throws ServiceException;

    List<Order> getOrdersByUserId(int userId) throws ServiceException;

}

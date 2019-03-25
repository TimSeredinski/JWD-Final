package by.training.epam.seredinski.service.impl;

import by.training.epam.seredinski.dao.DAOProvider;
import by.training.epam.seredinski.dao.DishDAO;
import by.training.epam.seredinski.dao.OrderDAO;
import by.training.epam.seredinski.entity.Dish;
import by.training.epam.seredinski.entity.Order;
import by.training.epam.seredinski.exception.DaoException;
import by.training.epam.seredinski.exception.ServiceException;
import by.training.epam.seredinski.service.AddressService;
import by.training.epam.seredinski.service.OrderService;
import by.training.epam.seredinski.service.ServiceProvider;

import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    @Override
    public void saveOrder(String city, String street, int house, int flat, int userId, Calendar dateTime, LinkedHashSet<Dish> dishes) throws ServiceException {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        AddressService addressService = serviceProvider.getAddressService();
        int addressId = addressService.saveAddress(city, street, house, flat, userId);
        DAOProvider provider = DAOProvider.getInstance();
        OrderDAO orderDAO = provider.getOrderDAO();
        try {
            orderDAO.save(new Order(dateTime, userId, addressId, dishes));
        } catch (DaoException e) {
            throw new ServiceException("Exception in OrderServiceImpl.saveOrder()", e);
        }
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        OrderDAO orderDAO = provider.getOrderDAO();
        DishDAO dishDAO = provider.getDishDAO();
        try {
            List<Order> orders = orderDAO.getOrdersByUserId(userId);
            for(Order order : orders){
                order.setDishes(dishDAO.getOrderDishes(order.getId()));
            }
            return orders;
        } catch (DaoException e) {
            throw new ServiceException("Exception in OrderServiceImpl.getOrdersByUserId()", e);
        }
    }
}

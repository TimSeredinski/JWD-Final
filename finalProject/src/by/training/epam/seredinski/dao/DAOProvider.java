package by.training.epam.seredinski.dao;

import by.training.epam.seredinski.dao.impl.SQLAddressDAO;
import by.training.epam.seredinski.dao.impl.SQLDishDAO;
import by.training.epam.seredinski.dao.impl.SQLOrderDAO;
import by.training.epam.seredinski.dao.impl.SQLUserDAO;

public class DAOProvider {
    private static final DAOProvider instance = new DAOProvider();

    private final UserDAO userDAO = new SQLUserDAO();
    private final DishDAO dishDAO = new SQLDishDAO();
    private final OrderDAO orderDAO = new SQLOrderDAO();
    private final AddressDAO addressDAO = new SQLAddressDAO();

    private DAOProvider() {
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public DishDAO getDishDAO() {
        return dishDAO;
    }

    public OrderDAO getOrderDAO(){
        return orderDAO;
    }

    public AddressDAO getAddressDAO(){
        return addressDAO;
    }

    public static DAOProvider getInstance() {
        return instance;
    }

}

package by.training.epam.seredinski.dao;

import by.training.epam.seredinski.dao.impl.SQLDishDAO;
import by.training.epam.seredinski.dao.impl.SQLUserDAO;

public class DAOProvider {
    private static final DAOProvider instance = new DAOProvider();

    private final UserDAO userDAO = new SQLUserDAO();
    private final DishDAO dishDAO = new SQLDishDAO();

    private DAOProvider() {
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public DishDAO getDishDAO() {
        return dishDAO;
    }

    public static DAOProvider getInstance() {
        return instance;
    }

}

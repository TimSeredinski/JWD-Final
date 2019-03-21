package by.training.epam.seredinski.service.impl;

import by.training.epam.seredinski.dao.DAOProvider;
import by.training.epam.seredinski.dao.DishDAO;
import by.training.epam.seredinski.entity.Dish;
import by.training.epam.seredinski.exception.DaoException;
import by.training.epam.seredinski.exception.ServiceException;
import by.training.epam.seredinski.service.DishService;
import by.training.epam.seredinski.validation.ValidateUtil;

import java.util.List;

public class DishServiceImpl implements DishService {

    @Override
    public Dish createDish(String name, String description, Dish.DishType type, int weight, int price) throws ServiceException {
        Dish dish = new Dish(name, description, type, weight, price);

        if (!ValidateUtil.validateDish(dish)) {
            System.out.println("not valid");
            throw new ServiceException("Not valid dish");
        }

        DAOProvider daoProvider = DAOProvider.getInstance();
        DishDAO dishDAO = daoProvider.getDishDAO();

        try {
            System.out.println("DaoService create");
            dishDAO.createDish(dish);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return dish;
    }

    @Override
    public Dish updateDish(int id, String name, String description, Dish.DishType type, int weight, int price) throws ServiceException {
        Dish dish = new Dish(id, name, description, type, weight, price);

        if (!ValidateUtil.validateDish(dish)) {
            System.out.println("not valid");
            throw new ServiceException("Not valid dish");
        }

        DAOProvider daoProvider = DAOProvider.getInstance();
        DishDAO dishDAO = daoProvider.getDishDAO();

        try {
            System.out.println("DaoService update");
            dishDAO.updateDish(dish);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return dish;
    }

    @Override
    public void deleteDish(int dishId) throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        DishDAO dishDAO = daoProvider.getDishDAO();
        try {
            dishDAO.deleteDish(dishId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }


    @Override
    public List<Dish> getByType(Dish.DishType type) throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        DishDAO dishDAO = daoProvider.getDishDAO();
        try {
            return dishDAO.getByType(type);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Dish getById(int id) throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        DishDAO dishDAO = daoProvider.getDishDAO();
        try {
            return dishDAO.getById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}

package by.training.epam.seredinski.service.impl;

import by.training.epam.seredinski.dao.DAOProvider;
import by.training.epam.seredinski.dao.DishDAO;
import by.training.epam.seredinski.dao.impl.SQLDishDAO;
import by.training.epam.seredinski.entity.Dish;
import by.training.epam.seredinski.enums.DishType;
import by.training.epam.seredinski.exception.DaoException;
import by.training.epam.seredinski.exception.ServiceException;
import by.training.epam.seredinski.service.DishService;
import by.training.epam.seredinski.validation.ValidateUtil;

import java.util.ArrayList;
import java.util.List;

public class DishServiceImpl implements DishService {

    @Override
    public Dish createDish(String name, String description, DishType type, int weight, int price) throws ServiceException {
        Dish dish = new Dish(name, description, type, weight, price);

        if(!ValidateUtil.validateDish(dish)){
            System.out.println("not valid");
            throw new ServiceException("Not valid dish");
        }

        DAOProvider daoProvider = DAOProvider.getInstance();
        DishDAO dishDAO = daoProvider.getDishDAO();

        try {
            System.out.println("DaoService");
            dishDAO.createDish(dish);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return dish;
    }

    @Override
    public List<Dish> getByType(DishType type) throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        DishDAO dishDAO = daoProvider.getDishDAO();
        try {
            return dishDAO.getByType(type);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}

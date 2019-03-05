package by.training.epam.seredinski.dao;

import by.training.epam.seredinski.entity.Dish;
import by.training.epam.seredinski.exception.DaoException;

import java.util.List;

public interface DishDAO {

    void createDish(Dish dish) throws DaoException;
    List<Dish> getByType(Dish.DishType type) throws DaoException;
    Dish getById(int id) throws DaoException;
    void updateDish(Dish dish) throws DaoException;

}

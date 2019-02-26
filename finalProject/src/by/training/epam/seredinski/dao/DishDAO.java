package by.training.epam.seredinski.dao;

import by.training.epam.seredinski.entity.Dish;
import by.training.epam.seredinski.enums.DishType;
import by.training.epam.seredinski.exception.DaoException;

import java.util.List;

public interface DishDAO {

    void createDish(Dish dish) throws DaoException;

    List<Dish> getByType(DishType type) throws DaoException;
}

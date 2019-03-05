package by.training.epam.seredinski.service;

import by.training.epam.seredinski.entity.Dish;
import by.training.epam.seredinski.exception.ServiceException;

import java.util.List;

public interface DishService {

    Dish createDish(String name, String description, Dish.DishType type, int weight, int price) throws ServiceException;
    List<Dish> getByType(Dish.DishType type) throws ServiceException;
    Dish getById(int id) throws ServiceException;
    Dish updateDish(int id, String name, String description, Dish.DishType type, int weight, int price) throws ServiceException;

}

package by.training.epam.seredinski.service;

import by.training.epam.seredinski.entity.Dish;
import by.training.epam.seredinski.enums.DishType;
import by.training.epam.seredinski.exception.ServiceException;

import java.util.List;

public interface DishService {

    Dish createDish(String name, String description, DishType type, int weight, int price) throws ServiceException;
    List<Dish> getByType(DishType type) throws ServiceException;

}

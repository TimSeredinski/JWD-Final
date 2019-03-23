package by.training.epam.seredinski.controller.command.impl;

import by.training.epam.seredinski.constant.Constants;
import by.training.epam.seredinski.controller.command.Command;
import by.training.epam.seredinski.controller.command.util.CreatorFullURL;
import by.training.epam.seredinski.entity.Dish;
import by.training.epam.seredinski.exception.ServiceException;
import by.training.epam.seredinski.service.DishService;
import by.training.epam.seredinski.service.ServiceProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DishesRedirectCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url = CreatorFullURL.create(request);
        request.getSession(true).setAttribute(Constants.PREV_REQUEST, url);

        ServiceProvider provider = ServiceProvider.getInstance();
        DishService dishService = provider.getDishService();
        Dish.DishType type = Dish.DishType.valueOf(request.getParameter(Constants.PARAMETER_DISH_TYPE).toUpperCase());
        List<Dish> dishes;
        try {
            dishes = dishService.getByType(type);
            request.setAttribute(Constants.DISHES, dishes);
        } catch (ServiceException e) {
            request.setAttribute("error", "Can't display dishes");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.DISHES_PAGE);
        dispatcher.forward(request, response);
    }
}

package by.training.epam.seredinski.controller.command.impl;

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
        request.getSession(true).setAttribute("prev_request", url);

        ServiceProvider provider = ServiceProvider.getInstance();
        DishService dishService = provider.getDishService();
        List<Dish> dishes;
        try {
            dishes = dishService.getByType(Dish.DishType.PIZZA);
            dishes.addAll(dishService.getByType(Dish.DishType.BURGER));
            dishes.addAll(dishService.getByType(Dish.DishType.DESSERT));
            request.setAttribute("dishes", dishes);
        } catch (ServiceException e) {
            request.setAttribute("error", "Can't display dishes");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/dishes.jsp");
        dispatcher.forward(request, response);
    }
}

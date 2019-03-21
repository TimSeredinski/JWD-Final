package by.training.epam.seredinski.controller.command.impl;

import by.training.epam.seredinski.constant.Constants;
import by.training.epam.seredinski.controller.command.Command;
import by.training.epam.seredinski.entity.Dish;
import by.training.epam.seredinski.exception.ServiceException;
import by.training.epam.seredinski.service.DishService;
import by.training.epam.seredinski.service.ServiceProvider;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DeleteDishCommand implements Command {

    private final static Logger logger = Logger.getLogger(DeleteDishCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int dishId = Integer.parseInt(request.getParameter("deletedDishId"));
        ServiceProvider provider = ServiceProvider.getInstance();
        DishService service = provider.getDishService();
        Dish dish;
        List<Dish> dishes;
        try {
            dish = service.getById(dishId);
            service.deleteDish(dishId);
            if (dish != null) {
                dishes = service.getByType(dish.getType());
            } else {
                dishes = service.getByType(Dish.DishType.PIZZA);
            }
            request.setAttribute("dishes", dishes);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Exception in DeleteDishCommand");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.DISHES_PAGE);
        dispatcher.forward(request, response);
    }
}

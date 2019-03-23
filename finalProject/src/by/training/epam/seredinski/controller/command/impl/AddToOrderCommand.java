package by.training.epam.seredinski.controller.command.impl;

import by.training.epam.seredinski.constant.Constants;
import by.training.epam.seredinski.controller.command.Command;
import by.training.epam.seredinski.entity.Dish;
import by.training.epam.seredinski.exception.ServiceException;
import by.training.epam.seredinski.service.DishService;
import by.training.epam.seredinski.service.ServiceProvider;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;

public class AddToOrderCommand implements Command {

    private final static Logger logger = Logger.getLogger(AddToOrderCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int dishId = Integer.parseInt(request.getParameter(Constants.ORDER_DISH_ID));
        ServiceProvider provider = ServiceProvider.getInstance();
        DishService service = provider.getDishService();
        Dish dish = null;
        try {
            dish = service.getById(dishId);
            LinkedHashSet<Dish> userOrder;
            if (request.getSession().getAttribute(Constants.USER_ORDER) == null) {
                userOrder = new LinkedHashSet<>();
            } else {
                userOrder = (LinkedHashSet<Dish>) request.getSession().getAttribute(Constants.USER_ORDER);
            }
            if (userOrder.contains(dish)) {
                for (Dish dishInOrder : userOrder) {
                    if (dishInOrder.equals(dish)) {
                        dishInOrder.incAmount();
                    }
                }
            } else {
                userOrder.add(dish);
            }
            int orderPrice = 0;
            for (Dish dishFromOrder : userOrder) {
                orderPrice += dishFromOrder.getAmount() * dishFromOrder.getPrice();
            }
            request.getSession().setAttribute(Constants.ORDER_PRICE, orderPrice);
            request.getSession().setAttribute(Constants.USER_ORDER, userOrder);
        } catch (ServiceException e) {
            logger.error("Exception in AddToOrderCommand", e);
        }

        List<Dish> dishes;
        try {
            if (dish != null) {
                dishes = service.getByType(dish.getType());
            } else {
                dishes = service.getByType(Dish.DishType.PIZZA);
            }
            request.setAttribute(Constants.DISHES, dishes);
        } catch (ServiceException e) {
            request.setAttribute("error", "Can't display dishes");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.DISHES_PAGE);
        dispatcher.forward(request, response);

    }
}

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

public class DeleteDishFromOrder implements Command {

    private final static Logger logger = Logger.getLogger(DeleteDishFromOrder.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int dishId = Integer.parseInt(request.getParameter("deletedDishId"));
        LinkedHashSet<Dish> dishes = (LinkedHashSet<Dish>) request.getSession().getAttribute("userOrder");
        boolean flag = false;
        for (Dish dish : dishes) {
            if (dishId == dish.getId()) {
                if (dish.getAmount() > 1) {
                    dish.decAmount();
                } else {
                    flag = true;
                }
            }
        }
        if (flag) {
            ServiceProvider provider = ServiceProvider.getInstance();
            DishService service = provider.getDishService();
            try {
                Dish dishToRemove = service.getById(dishId);
                dishes.remove(dishToRemove);
            } catch (ServiceException e) {
                logger.error("Error in DeleteDishFromOrder", e);
            }
        }
        if (dishes.size() == 0) {
            dishes = null;
            request.getSession().setAttribute("orderPrice", null);
        }else {
            int orderPrice = 0;
            for (Dish dishFromOrder : dishes) {
                orderPrice += dishFromOrder.getAmount() * dishFromOrder.getPrice();
            }
            request.getSession().setAttribute("orderPrice", orderPrice);
        }
        request.getSession().setAttribute("userOrder", dishes);
        RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.ORDER_PAGE);
        dispatcher.forward(request, response);
    }
}

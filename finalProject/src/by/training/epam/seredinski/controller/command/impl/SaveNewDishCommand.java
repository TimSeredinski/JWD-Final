package by.training.epam.seredinski.controller.command.impl;

import by.training.epam.seredinski.constant.Constants;
import by.training.epam.seredinski.controller.command.Command;
import by.training.epam.seredinski.entity.Dish;
import by.training.epam.seredinski.exception.ServiceException;
import by.training.epam.seredinski.service.DishService;
import by.training.epam.seredinski.service.ServiceProvider;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SaveNewDishCommand implements Command {

    private final static Logger logger = Logger.getLogger(SaveNewDishCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter(Constants.PARAMETER_EDITED_DISH_ID);
        String page;
        String name = request.getParameter(Constants.PARAMETER_NAME);
        String description = request.getParameter(Constants.PARAMETER_DESCRIPTION);
        String weight = request.getParameter(Constants.PARAMETER_WEIGHT);
        String price = request.getParameter(Constants.PARAMETER_PRICE);
        request.setAttribute(Constants.PARAMETER_DISH_TYPE, Dish.DishType.values());
        Dish.DishType type = Dish.DishType.valueOf(request.getParameter(Constants.PARAMETER_TYPE).toUpperCase());

        request.getSession(true).removeAttribute(Constants.PARAMETER_EDITED_DISH);
        ServiceProvider provider = ServiceProvider.getInstance();
        DishService service = provider.getDishService();
        Dish dish;
        try {
            if (id.equals("")) {
                dish = service.createDish(name, description, type, Integer.parseInt(weight), Integer.parseInt(price));
            } else {
                dish = service.updateDish(Integer.parseInt(id), name, description, type, Integer.parseInt(weight), Integer.parseInt(price));
            }
            request.getSession(true).setAttribute(Constants.DISH, dish);
            page = Constants.REDIRECT_DISH_PAGE;
        } catch (ServiceException | NumberFormatException e) {
            logger.error("Exception in SaveNewDishCommand", e);
            page = Constants.REDIRECT_CREATE_DISH_PAGE;
        }
        response.sendRedirect(Constants.REDIRECT_COMMON + page);

    }

}

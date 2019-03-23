package by.training.epam.seredinski.controller.command.impl;

import by.training.epam.seredinski.constant.Constants;
import by.training.epam.seredinski.controller.command.Command;
import by.training.epam.seredinski.controller.command.util.CreatorFullURL;
import by.training.epam.seredinski.entity.Dish;
import by.training.epam.seredinski.exception.ServiceException;
import by.training.epam.seredinski.service.DishService;
import by.training.epam.seredinski.service.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateDishCommand implements Command {


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        String url = CreatorFullURL.create(request);
        request.getSession(true).setAttribute(Constants.PREV_REQUEST, url);
        ServiceProvider provider = ServiceProvider.getInstance();
        DishService service = provider.getDishService();
        try {
            Dish dish = service.getById(Integer.parseInt(request.getParameter(Constants.PARAMETER_EDITED_DISH_ID)));
            request.getSession(true).setAttribute(Constants.PARAMETER_EDITED_DISH_ID, dish);
            page = Constants.REDIRECT_CREATE_DISH_PAGE;

        } catch (ServiceException e) {
            request.getSession(true).setAttribute("error", "Can't find dish");
            page = Constants.REDIRECT_MAIN_PAGE;
        }
        response.sendRedirect(Constants.REDIRECT_COMMON + page);
    }
}

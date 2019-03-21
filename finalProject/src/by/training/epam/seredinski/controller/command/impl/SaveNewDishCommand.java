package by.training.epam.seredinski.controller.command.impl;

import by.training.epam.seredinski.constant.Constants;
import by.training.epam.seredinski.controller.command.Command;
import by.training.epam.seredinski.entity.Dish;
import by.training.epam.seredinski.exception.ServiceException;
import by.training.epam.seredinski.service.DishService;
import by.training.epam.seredinski.service.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SaveNewDishCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter(Constants.PARAMETER_EDITED_DISH_ID);
        String page;
        String name = request.getParameter(Constants.PARAMETER_NAME);
        String description = request.getParameter(Constants.PARAMETER_DESCRIPTION);
        String weight = request.getParameter(Constants.PARAMETER_WEIGHT);
        String price = request.getParameter(Constants.PARAMETER_PRICE);
        request.setAttribute("dishTypes", Dish.DishType.values());
        Dish.DishType type = Dish.DishType.valueOf(request.getParameter("type").toUpperCase());

        HttpSession session = request.getSession(false);

        ServiceProvider provider = ServiceProvider.getInstance();
        DishService service = provider.getDishService();
        Dish dish;
        try {
            if (id.equals("")) {
                dish = service.createDish(name, description, type, Integer.parseInt(weight), Integer.parseInt(price));
            } else {
                dish = service.updateDish(Integer.parseInt(id), name, description, type, Integer.parseInt(weight), Integer.parseInt(price));
                //!!!!!!!Даже после изменения блюда все еще храниться в сессии и вызывается при создании нового блюда!!!!!!!
            }
            request.getSession(true).setAttribute("dish", dish);
            page = Constants.REDIRECT_DISH_PAGE;
        } catch (ServiceException | NumberFormatException e) {
            session.setAttribute("error", "Something is wrong ;)");
            page = Constants.REDIRECT_CREATE_DISH_PAGE;
        }
        response.sendRedirect(Constants.REDIRECT_COMMON + page);

    }

}

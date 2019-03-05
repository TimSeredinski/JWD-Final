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

public class EditDishCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = CreatorFullURL.create(request);
        request.getSession(true).setAttribute("prev_request", url);
        request.setAttribute("dishTypes", Dish.DishType.values());
        ServiceProvider provider = ServiceProvider.getInstance();
        DishService service = provider.getDishService();
        try {
            Dish dish = service.getById(Integer.parseInt(request.getParameter("editedDishId")));
            request.setAttribute("editedDish", dish);
        } catch (ServiceException e) {
            request.setAttribute("error", "Can't find dish");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/newDishForm.jsp");
        dispatcher.forward(request, response);
    }
}

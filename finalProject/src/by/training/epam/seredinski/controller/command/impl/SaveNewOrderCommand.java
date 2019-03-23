package by.training.epam.seredinski.controller.command.impl;

import by.training.epam.seredinski.constant.Constants;
import by.training.epam.seredinski.controller.command.Command;
import by.training.epam.seredinski.entity.Dish;
import by.training.epam.seredinski.exception.ServiceException;
import by.training.epam.seredinski.service.OrderService;
import by.training.epam.seredinski.service.ServiceProvider;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class SaveNewOrderCommand implements Command {

    private static final Logger logger = Logger.getLogger(SaveNewOrderCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String city = request.getParameter(Constants.PARAMETER_CITY);
        String street = request.getParameter(Constants.PARAMETER_STREET);
        int house = Integer.parseInt(request.getParameter(Constants.PARAMETER_HOUSE));
        int flat = Integer.parseInt(request.getParameter(Constants.PARAMETER_FLAT));
        int userId = (int) request.getSession().getAttribute(Constants.PARAMETER_USER_ID);
        Calendar time = Calendar.getInstance();
        System.out.println(userId);
        ServiceProvider provider = ServiceProvider.getInstance();
        OrderService service = provider.getOrderService();
        try {
            LinkedHashSet<Dish> dishes = (LinkedHashSet<Dish>) request.getSession().getAttribute(Constants.USER_ORDER);
            service.saveOrder(city, street, house, flat, userId, time, dishes);
            request.getSession().setAttribute(Constants.USER_ORDER, null);
        } catch (ServiceException e) {
            logger.error("Exception in SaveNewOrderCommand", e);
        }
        response.sendRedirect(Constants.REDIRECT_COMMON + Constants.REDIRECT_MAIN_PAGE);
    }
}

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
        int userId = (int) request.getSession().getAttribute("userId");
        Calendar time = Calendar.getInstance();

        ServiceProvider provider = ServiceProvider.getInstance();
        OrderService service = provider.getOrderService();
        try {
            LinkedHashSet<Dish> dishes = (LinkedHashSet<Dish>) request.getSession().getAttribute("userOrder");
            service.saveOrder(city, street, house, flat, userId, time, dishes);
        } catch (ServiceException e) {
            logger.error("Exception in SaveNewOrderCommand", e);
        }
    }
}

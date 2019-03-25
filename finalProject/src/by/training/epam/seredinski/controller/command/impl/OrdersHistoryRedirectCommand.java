package by.training.epam.seredinski.controller.command.impl;

import by.training.epam.seredinski.constant.Constants;
import by.training.epam.seredinski.controller.command.Command;
import by.training.epam.seredinski.controller.command.util.CreatorFullURL;
import by.training.epam.seredinski.entity.Order;
import by.training.epam.seredinski.exception.ServiceException;
import by.training.epam.seredinski.service.OrderService;
import by.training.epam.seredinski.service.ServiceProvider;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrdersHistoryRedirectCommand implements Command {

    private final static Logger logger = Logger.getLogger(OrdersHistoryRedirectCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = CreatorFullURL.create(request);
        request.getSession(true).setAttribute(Constants.PREV_REQUEST, url);
        int userId = (int) request.getSession().getAttribute(Constants.PARAMETER_USER_ID);
        ServiceProvider provider = ServiceProvider.getInstance();
        OrderService service = provider.getOrderService();
        try {
            List<Order> orders = service.getOrdersByUserId(userId);
            request.setAttribute(Constants.USER_ORDERS, orders);
        } catch (ServiceException e) {
            logger.error("Exception in OrdersHistoryRedirectCommand", e);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.USER_ORDERS_PAGE);
        dispatcher.forward(request, response);

    }
}

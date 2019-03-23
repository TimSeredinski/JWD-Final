package by.training.epam.seredinski.controller.command.impl;

import by.training.epam.seredinski.constant.Constants;
import by.training.epam.seredinski.controller.command.Command;
import by.training.epam.seredinski.controller.command.util.CreatorFullURL;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToOrderPageCommand implements Command {

    private final static Logger logger = Logger.getLogger(GoToOrderPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url = CreatorFullURL.create(request);
        request.getSession(true).setAttribute(Constants.PREV_REQUEST, url);
        RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.ORDER_PAGE);
        dispatcher.forward(request, response);

    }
}

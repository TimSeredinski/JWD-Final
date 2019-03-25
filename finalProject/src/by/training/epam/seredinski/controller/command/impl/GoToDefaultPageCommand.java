package by.training.epam.seredinski.controller.command.impl;

import by.training.epam.seredinski.constant.Constants;
import by.training.epam.seredinski.controller.command.Command;
import by.training.epam.seredinski.controller.command.util.CreatorFullURL;
import by.training.epam.seredinski.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GoToDefaultPageCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = CreatorFullURL.create(request);
        HttpSession session = request.getSession();
        session.setAttribute(Constants.PREV_REQUEST, url);
        session.removeAttribute("errorRegistrationMessage");
        RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.DEFAULT_PAGE);
        dispatcher.forward(request, response);
        session.removeAttribute("errorLoginMessage");
    }

}

package by.training.epam.seredinski.controller.command.impl;

import by.training.epam.seredinski.constant.Constants;
import by.training.epam.seredinski.controller.command.Command;
import by.training.epam.seredinski.controller.command.util.CreatorFullURL;
import by.training.epam.seredinski.entity.User;
import by.training.epam.seredinski.exception.ServiceException;
import by.training.epam.seredinski.service.ClientService;
import by.training.epam.seredinski.service.ServiceProvider;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorizationCommand implements Command {

    private final static Logger logger = Logger.getLogger(AuthorizationCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter(Constants.PARAMETER_LOGIN);
        String password = request.getParameter(Constants.PARAMETER_PASSWORD);

        System.out.println(login + password);
        ServiceProvider provider = ServiceProvider.getInstance();
        ClientService service = provider.getClientService();

        String page;
        try {
            User user = service.authorization(login, password);

            if (user == null) {
                request.setAttribute("error", "login or password error");
            } else {
                request.setAttribute("user", user);
                request.getSession().setAttribute("userRole", user.getRole());
                request.getSession().setAttribute("userId", user.getId());
            }
        } catch (ServiceException e) {
            request.setAttribute("error", "Login or Password Error");
            logger.error("Exception in AuthorizationCommand", e);
        }
        response.sendRedirect(Constants.REDIRECT_COMMON + Constants.REDIRECT_MAIN_PAGE);

    }

}

package by.training.epam.seredinski.controller.command.impl;

import by.training.epam.seredinski.controller.command.Command;
import by.training.epam.seredinski.controller.command.util.CreatorFullURL;
import by.training.epam.seredinski.entity.User;
import by.training.epam.seredinski.service.ClientService;
import by.training.epam.seredinski.exception.ServiceException;
import by.training.epam.seredinski.service.ServiceProvider;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationCommand implements Command {

    private final static Logger logger = Logger.getLogger(RegistrationCommand.class);

    private static final String PARAMETER_EMAIL = "email";
    private static final String PARAMETER_PASSWORD = "password";
    private static final String PARAMETER_LOGIN = "login";
    private static final String PARAMETER_NAME = "name";
    private static final String PARAMETER_SURNAME = "surname";

    private static final String MAIN_PAGE = "/WEB-INF/jsp/main.jsp";
    private static final String REGISTRATION_PAGE = "/WEB-INF/jsp/registration.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        String email = request.getParameter(PARAMETER_EMAIL);
        String password = request.getParameter(PARAMETER_PASSWORD);
        String login = request.getParameter(PARAMETER_LOGIN);
        String name = request.getParameter(PARAMETER_NAME);
        String surname = request.getParameter(PARAMETER_SURNAME);

        ServiceProvider provider = ServiceProvider.getInstance();
        ClientService service = provider.getClientService();
        User user = null;

        try {
            user = service.registration(email, password, login, name, surname);
            if (user == null) {
                request.setAttribute("error", "Can't create user");
                page = REGISTRATION_PAGE;
            } else {
                request.setAttribute("user", user);
                page = MAIN_PAGE;
            }

        } catch (ServiceException e) {
            request.setAttribute("error", "Something wrong in RegistrationCommand");
            page = REGISTRATION_PAGE;
            logger.log(Level.ERROR, "Exception in RegistrationCommand");
        }

        String url = CreatorFullURL.create(request);
        request.getSession(true).setAttribute("prev_request", url);
        System.out.println("create new user");

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);

    }

}

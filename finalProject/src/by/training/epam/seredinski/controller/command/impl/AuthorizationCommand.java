package by.training.epam.seredinski.controller.command.impl;

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

    private static final String PARAMETER_LOGIN = "login";
    private static final String PARAMETER_PASSWORD = "password";

    private static final String MAIN_PAGE = "/WEB-INF/jsp/main.jsp";
    private static final String DEFAULT_PAGE = "/WEB-INF/jsp/default.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login;
        String password;

        login = request.getParameter(PARAMETER_LOGIN);
        password = request.getParameter(PARAMETER_PASSWORD);

        ServiceProvider provider = ServiceProvider.getInstance();
        ClientService service = provider.getClientService();

        User user = null;
        String page;
        try {
            user = service.authorization(login, password);

            if (user == null) {
                request.setAttribute("error", "login or password error");
                page = DEFAULT_PAGE;
            } else {
                request.setAttribute("user", user);
                page = MAIN_PAGE;
//                String role = "admin";
//                HttpSession session = request.getSession(true);
//                session.setAttribute("role", role);
            }
        } catch (ServiceException e) {
            request.setAttribute("error", "Login or Password Error");
            logger.error("Exception in AuthorizationCommand", e);
            page = DEFAULT_PAGE;
        }

//        String url = CreatorFullURL.create(request);
//        request.getSession(true).setAttribute("prev_request", url);

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);

    }

}

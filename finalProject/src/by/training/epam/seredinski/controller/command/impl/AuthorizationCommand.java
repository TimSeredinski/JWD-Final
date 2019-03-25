package by.training.epam.seredinski.controller.command.impl;

import by.training.epam.seredinski.constant.Constants;
import by.training.epam.seredinski.controller.command.Command;
import by.training.epam.seredinski.encryption.Encryption;
import by.training.epam.seredinski.entity.User;
import by.training.epam.seredinski.exception.ServiceException;
import by.training.epam.seredinski.service.ClientService;
import by.training.epam.seredinski.service.ServiceProvider;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationCommand implements Command {

    private final static Logger logger = Logger.getLogger(AuthorizationCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(Constants.PARAMETER_LOGIN);
        String password = Encryption.encryptPassword(request.getParameter(Constants.PARAMETER_PASSWORD));

        ServiceProvider provider = ServiceProvider.getInstance();
        ClientService service = provider.getClientService();
        try {
            User user = service.authorization(login, password);

            if (user == null) {
                request.getSession().setAttribute("errorLoginMessage", Constants.ERROR_LOGIN_MESSAGE);
            } else {
                request.getSession().setAttribute("userRole", user.getRole());
                request.getSession().setAttribute("userId", user.getId());
            }
        } catch (ServiceException e) {
            request.getSession().setAttribute("errorLoginMessage", Constants.ERROR_LOGIN_MESSAGE);
            logger.error("Exception in AuthorizationCommand", e);
        }
        response.sendRedirect(Constants.REDIRECT_COMMON + Constants.REDIRECT_MAIN_PAGE);

    }

}

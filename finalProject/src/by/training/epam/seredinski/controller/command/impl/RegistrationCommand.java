package by.training.epam.seredinski.controller.command.impl;

import by.training.epam.seredinski.constant.Constants;
import by.training.epam.seredinski.controller.command.Command;
import by.training.epam.seredinski.encryption.Encryption;
import by.training.epam.seredinski.entity.User;
import by.training.epam.seredinski.exception.ServiceException;
import by.training.epam.seredinski.service.ClientService;
import by.training.epam.seredinski.service.ServiceProvider;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationCommand implements Command {

    private final static Logger logger = Logger.getLogger(RegistrationCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        String email = request.getParameter(Constants.PARAMETER_EMAIL);
        String password = Encryption.encryptPassword(request.getParameter(Constants.PARAMETER_PASSWORD));
        String login = request.getParameter(Constants.PARAMETER_LOGIN);
        String name = request.getParameter(Constants.PARAMETER_NAME);
        String surname = request.getParameter(Constants.PARAMETER_SURNAME);
        System.out.println(surname);
        ServiceProvider provider = ServiceProvider.getInstance();
        ClientService service = provider.getClientService();
        User user;

        try {
            User.UserRole role = User.UserRole.CLIENT;
            user = service.registration(email, password, login, name, surname, role);
            if (user == null) {
                page = Constants.REGISTRATION_PAGE;
            } else {
                page = Constants.REDIRECT_MAIN_PAGE;
                System.out.println("create new user");
            }
        } catch (ServiceException e) {
            page = Constants.REDIRECT_REGISTRATION;
            logger.log(Level.ERROR, "Exception in RegistrationCommand");
        }

        response.sendRedirect(Constants.REDIRECT_COMMON + page);

    }

}

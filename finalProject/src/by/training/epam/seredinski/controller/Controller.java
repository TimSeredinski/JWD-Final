package by.training.epam.seredinski.controller;

import by.training.epam.seredinski.constant.Constants;
import by.training.epam.seredinski.controller.command.Command;
import by.training.epam.seredinski.controller.command.CommandProvider;
import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String PARAMETER_COMMAND = "command";
    private final CommandProvider provider = new CommandProvider();

    public void init() {
        String realConfiguration = getServletContext().getRealPath(Constants.CONFIG_PATH);
        new DOMConfigurator().doConfigure(realConfiguration, LogManager.getLoggerRepository());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String commandName = request.getParameter(PARAMETER_COMMAND);
        Command command = provider.getCommand(commandName);

        if (command != null) {
            command.execute(request, response);
        } else {
            request.getRequestDispatcher(Constants.ACCESS_DENIED_PAGE).forward(request, response);
        }

    }
}

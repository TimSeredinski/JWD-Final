package by.training.epam.seredinski.controller;

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


    public Controller() {
        super();
    }

    public void init() {
        String realConfiguration = getServletContext().getRealPath("WEB-INF/classes/resources/log4j2.xml");
        new DOMConfigurator().doConfigure(realConfiguration, LogManager.getLoggerRepository());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String commandName = request.getParameter(PARAMETER_COMMAND);
        Command command = provider.getCommand(commandName);
        command.execute(request, response);

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}

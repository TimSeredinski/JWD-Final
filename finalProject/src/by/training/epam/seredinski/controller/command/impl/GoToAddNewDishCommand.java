package by.training.epam.seredinski.controller.command.impl;

import by.training.epam.seredinski.controller.command.Command;
import by.training.epam.seredinski.controller.command.util.CreatorFullURL;
import by.training.epam.seredinski.enums.DishType;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToAddNewDishCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url = CreatorFullURL.create(request);
        request.getSession(true).setAttribute("prev_request", url);
        request.setAttribute("dishTypes", DishType.values());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/newDishForm.jsp");
        dispatcher.forward(request, response);

    }

}
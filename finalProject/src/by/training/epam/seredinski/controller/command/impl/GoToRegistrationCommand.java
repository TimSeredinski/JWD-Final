package by.training.epam.seredinski.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.training.epam.seredinski.constant.Constants;
import by.training.epam.seredinski.controller.command.Command;
import by.training.epam.seredinski.controller.command.util.CreatorFullURL;

public class GoToRegistrationCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = CreatorFullURL.create(request);
		request.getSession(true).setAttribute(Constants.PREV_REQUEST, url);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.REGISTRATION_PAGE);
		dispatcher.forward(request, response);
	}

}

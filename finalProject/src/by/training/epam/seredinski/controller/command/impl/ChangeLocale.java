package by.training.epam.seredinski.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.training.epam.seredinski.constant.Constants;
import by.training.epam.seredinski.controller.command.Command;

public class ChangeLocale implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newLocale;
		HttpSession session;
		
		newLocale = request.getParameter("locale");
		
		session = request.getSession(true);
		session.setAttribute("local", newLocale);
		
		String url = (String)request.getSession(false).getAttribute(Constants.PREV_REQUEST);
		response.sendRedirect(url);
	}

}

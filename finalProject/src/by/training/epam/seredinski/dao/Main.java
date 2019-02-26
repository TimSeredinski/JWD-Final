package by.training.epam.seredinski.dao;

import java.io.IOException;

import by.training.epam.seredinski.dao.impl.SQLUserDAO;
import by.training.epam.seredinski.entity.User;
import by.training.epam.seredinski.exception.DaoException;

public class Main {

	public static void main(String[] args) throws DaoException, IOException {
		
		SQLUserDAO dao = new SQLUserDAO();
		
		User user = dao.authentification("qqq", "www");
		
		System.out.println(user.getName());

	}

}

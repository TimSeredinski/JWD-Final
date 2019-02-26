package by.training.epam.seredinski.dao;

import by.training.epam.seredinski.entity.User;
import by.training.epam.seredinski.exception.DaoException;

public interface UserDAO {

    User authentification(String login, String password) throws DaoException;

    void registration(User userData) throws DaoException;

}

package by.training.epam.seredinski.service;

import by.training.epam.seredinski.entity.User;
import by.training.epam.seredinski.exception.ServiceException;

public interface ClientService {

    User authorization(String login, String password) throws ServiceException;

    User registration(String email, String password, String login, String name, String surname, User.UserRole role) throws ServiceException;

}

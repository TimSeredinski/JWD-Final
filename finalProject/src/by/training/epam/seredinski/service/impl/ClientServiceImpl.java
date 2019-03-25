package by.training.epam.seredinski.service.impl;

import by.training.epam.seredinski.dao.DAOProvider;
import by.training.epam.seredinski.dao.UserDAO;
import by.training.epam.seredinski.entity.User;
import by.training.epam.seredinski.exception.DaoException;
import by.training.epam.seredinski.exception.ServiceException;
import by.training.epam.seredinski.service.ClientService;
import by.training.epam.seredinski.service.validation.CredentionalValidator;
import by.training.epam.seredinski.validation.ValidateUtil;

public class ClientServiceImpl implements ClientService {

    @Override
    public User authorization(String login, String password) throws ServiceException {

        if (!CredentionalValidator.isCorrect(login, password)) {
            throw new ServiceException("Exception in UserServiceImpl.authorization(), cause the User object is invalid");
        }

        DAOProvider daoProvider = DAOProvider.getInstance();
        UserDAO userDAO = daoProvider.getUserDAO();
        User user = null;
        try {
            user = userDAO.authentification(login, password);
        } catch (DaoException e) {
            throw new ServiceException("Exception in ClientServiceImpl.authorization()", e);
        }

        return user;
    }

    @Override
    public User registration(String email, String password, String login, String name, String surname, User.UserRole role) throws ServiceException {
        User user = new User(name, surname, email, login, password, role);
        if (!ValidateUtil.validateUser(user)) {
            throw new ServiceException("Exception in UserServiceImpl.registration(), cause the User object is invalid");
        }
        DAOProvider daoProvider = DAOProvider.getInstance();
        UserDAO userDAO = daoProvider.getUserDAO();

        try {
            userDAO.registration(user);
        } catch (DaoException e) {
            throw new ServiceException("Exception in ClientServiceImpl.registration()", e);
        }

        return user;
    }
}

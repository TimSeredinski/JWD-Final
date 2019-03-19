package by.training.epam.seredinski.dao;

import by.training.epam.seredinski.entity.Address;
import by.training.epam.seredinski.exception.DaoException;

public interface AddressDAO {

    int save(Address address) throws DaoException;

    Address getAddress(int userId) throws DaoException;
}

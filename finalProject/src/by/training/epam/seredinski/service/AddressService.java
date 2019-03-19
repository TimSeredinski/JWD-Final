package by.training.epam.seredinski.service;

import by.training.epam.seredinski.entity.Address;
import by.training.epam.seredinski.exception.ServiceException;

public interface AddressService {

    int saveAddress (String city, String street, int house, int flat, int userId) throws ServiceException;

    Address getAddress(int userId) throws ServiceException;

}

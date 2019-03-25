package by.training.epam.seredinski.service.impl;

import by.training.epam.seredinski.dao.AddressDAO;
import by.training.epam.seredinski.dao.DAOProvider;
import by.training.epam.seredinski.entity.Address;
import by.training.epam.seredinski.exception.DaoException;
import by.training.epam.seredinski.exception.ServiceException;
import by.training.epam.seredinski.service.AddressService;
import by.training.epam.seredinski.validation.ValidateUtil;

public class AddressServiceImpl implements AddressService {
    @Override
    public int saveAddress(String city, String street, int house, int flat, int userId) throws ServiceException {
        Address address = new Address(city, street, house, flat, userId);
        if(!ValidateUtil.validateAddress(address)){
            throw new ServiceException("Exception in AddressServiceImpl.saveAddress(), cause the Address object is invalid");
        }
        DAOProvider provider = DAOProvider.getInstance();
        AddressDAO addressDAO = provider.getAddressDAO();
        try {
            return addressDAO.save(address);
        } catch (DaoException e) {
            throw new ServiceException("Exception in AddressServiceImpl.saveAddress()", e);
        }
    }

    @Override
    public Address getAddress(int userId) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        AddressDAO addressDAO = provider.getAddressDAO();
        Address address;
        try {
            address = addressDAO.getAddress(userId);
        } catch (DaoException e) {
            throw new ServiceException("Exception in AddressServiceImpl.getAddress()", e);
        }
        return address;
    }
}

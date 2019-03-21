package by.training.epam.seredinski.dao.impl;

import by.training.epam.seredinski.constant.Constants;
import by.training.epam.seredinski.dao.AddressDAO;
import by.training.epam.seredinski.entity.Address;
import by.training.epam.seredinski.exception.DaoException;
import org.apache.log4j.Logger;

import java.sql.*;

public class SQLAddressDAO implements AddressDAO {

    private static final Logger logger = Logger.getLogger(SQLAddressDAO.class);


    protected static final String CREATE_ADDRESS = "INSERT INTO address (city, street, house, flat, userId) VALUES (?,?,?,?,?)";
    protected static final String UPDATE_ADDRESS = "UPDATE address SET city=?, street=?, house=?, flat=? WHERE userId=?";
    protected static final String GET_ADDRESS_BY_USER_ID = "SELECT * FROM address WHERE userId=?";

    @Override
    public int save(Address address) throws DaoException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int addressId = -1;
        try {
            statement = connection.prepareStatement(GET_ADDRESS_BY_USER_ID, Statement.RETURN_GENERATED_KEYS);
            connection.setAutoCommit(false);
            statement.setInt(1, address.getUserId());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                addressId = resultSet.getInt(Constants.PARAMETER_ID);
            }
            if (addressId == -1) {
                statement = connection.prepareStatement(CREATE_ADDRESS, Statement.RETURN_GENERATED_KEYS);
            } else {
                statement = connection.prepareStatement(UPDATE_ADDRESS, Statement.RETURN_GENERATED_KEYS);
            }
            insertAddressProperties(statement, address);
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()){
                addressId = resultSet.getInt(1);
            }
            connection.commit();
            connection.setAutoCommit(true);
            return addressId;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DaoException("Exception in SQLAddressDAO.save()", e);
            }
            logger.error("Exception in SQLAddressDAO.save()", e);
            throw new DaoException("Exception in SQLAddressDAO.save()", e);
        } finally {
            ConnectionPool.getInstance().closeConnection(connection, statement);
        }
    }

    private void insertAddressProperties(PreparedStatement statement, Address address) throws SQLException {
        statement.setString(1, address.getCity());
        statement.setString(2, address.getStreet());
        statement.setInt(3, address.getHouse());
        statement.setInt(4, address.getFlat());
        statement.setInt(5, address.getUserId());
    }

    @Override
    public Address getAddress(int userId) throws DaoException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Address address = null;
        try {
            statement = connection.prepareStatement(GET_ADDRESS_BY_USER_ID);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                address = getAddressWithResultSet(resultSet);
            }
            return address;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getInstance().closeConnection(connection, statement, resultSet);
        }
    }

    private Address getAddressWithResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(Constants.PARAMETER_ADDRESS_ID);
        String city = resultSet.getString(Constants.PARAMETER_CITY);
        String street = resultSet.getString(Constants.PARAMETER_STREET);
        int house = resultSet.getInt(Constants.PARAMETER_HOUSE);
        int flat = resultSet.getInt(Constants.PARAMETER_FLAT);
        int userId = resultSet.getInt(Constants.PARAMETER_USER_ID);
        return new Address(id, city, street, house, flat, userId);
    }
}

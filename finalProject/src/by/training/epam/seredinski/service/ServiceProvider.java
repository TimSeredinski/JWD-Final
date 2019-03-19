package by.training.epam.seredinski.service;

import by.training.epam.seredinski.service.impl.AddressServiceImpl;
import by.training.epam.seredinski.service.impl.ClientServiceImpl;
import by.training.epam.seredinski.service.impl.DishServiceImpl;
import by.training.epam.seredinski.service.impl.OrderServiceImpl;

public class ServiceProvider {

    private static final ServiceProvider instance = new ServiceProvider();

    private ServiceProvider() {
    }

    private ClientService clientService = new ClientServiceImpl();
    private DishService dishService = new DishServiceImpl();
    private OrderService orderService = new OrderServiceImpl();
    private AddressService addressService = new AddressServiceImpl();

    public ClientService getClientService() {
        return clientService;
    }

    public DishService getDishService() {
        return dishService;
    }

    public OrderService getOrderService(){
        return orderService;
    }

    public AddressService getAddressService(){
        return addressService;
    }

    public static ServiceProvider getInstance() {
        return instance;
    }

}

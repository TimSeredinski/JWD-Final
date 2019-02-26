package by.training.epam.seredinski.service;

import by.training.epam.seredinski.service.impl.ClientServiceImpl;
import by.training.epam.seredinski.service.impl.DishServiceImpl;

public class ServiceProvider {

    private static final ServiceProvider instance = new ServiceProvider();

    private ServiceProvider() {
    }

    private ClientService clientService = new ClientServiceImpl();
    private DishService dishService = new DishServiceImpl();

    public ClientService getClientService() {
        return clientService;
    }

    public DishService getDishService() {
        return dishService;
    }

    public static ServiceProvider getInstance() {
        return instance;
    }

}

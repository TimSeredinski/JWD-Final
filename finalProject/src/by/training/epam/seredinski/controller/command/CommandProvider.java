package by.training.epam.seredinski.controller.command;

import by.training.epam.seredinski.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {

    private Map<String, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put("authorization", new AuthorizationCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("go_to_default", new GoToDefaultPageCommand());
        commands.put("change_locale", new ChangeLocale());
        commands.put("go_to_all_dishes", new DishesRedirectCommand());
        commands.put("dish_info", new GoToInfoDishCommand());

        commands.put("guest_go_to_registration_page", new GoToRegistrationCommand());
        commands.put("guest_registration", new RegistrationCommand());

        commands.put("admin_update_dish", new UpdateDishCommand());
        commands.put("admin_go_to_add_new_dish_page", new GoToAddNewDishCommand());
        commands.put("admin_save_new_dish", new SaveNewDishCommand());
        commands.put("admin_delete_dish", new DeleteDishCommand());

        commands.put("client_add_to_order", new AddToOrderCommand());
        commands.put("client_save_new_order", new SaveNewOrderCommand());
        commands.put("client_go_to_order", new GoToOrderPageCommand());
        commands.put("client_go_to_address", new GoToAddressPageCommand());
        commands.put("client_go_to_orders_history", new OrdersHistoryRedirectCommand());
        commands.put("client_delete_dish_from_order", new DeleteDishFromOrder());
    }

    public Command getCommand(String commandName) {
        return commands.getOrDefault(commandName, null);
    }

}

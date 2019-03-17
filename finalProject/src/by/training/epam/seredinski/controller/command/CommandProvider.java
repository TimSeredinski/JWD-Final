package by.training.epam.seredinski.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.training.epam.seredinski.controller.command.impl.*;

public class CommandProvider {
	
	private Map<String, Command> commands = new HashMap<>();
	
	public CommandProvider() {
		commands.put("authorization", new AuthorizationCommand());
		commands.put("logout", new LogoutCommand());
		commands.put("go_to_registration_page", new GoToRegistrationCommand());
		commands.put("registration", new RegistrationCommand());
		commands.put("go_to_default", new GoToDefaultPageCommand());
		commands.put("change_locale", new ChangeLocale());
		commands.put("go_to_add_new_dish_page", new GoToAddNewDishCommand());
		commands.put("go_to_all_dishes", new DishesRedirectCommand());
		commands.put("save_new_dish", new SaveNewDishCommand());
		commands.put("dish_info", new GoToInfoDishCommand());
		commands.put("update_dish", new UpdateDishCommand());
		commands.put("add_to_order", new AddToOrderCommand());
		commands.put("go_to_order", new GoToOrderPageCommand());
		commands.put("save_new_order", new SaveNewOrderCommand());
		commands.put("go_to_address", new GoToAddressPageCommand());
	}
	
	public Command getCommand(String commandName) {
		return commands.get(commandName);
	}

}

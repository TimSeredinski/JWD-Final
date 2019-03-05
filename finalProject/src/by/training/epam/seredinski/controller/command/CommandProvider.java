package by.training.epam.seredinski.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.training.epam.seredinski.controller.command.impl.*;

public class CommandProvider {
	
	private Map<String, Command> commands = new HashMap<>();
	
	public CommandProvider() {
		commands.put("authorization", new AuthorizationCommand());
		commands.put("goToRegistrationPage", new GoToRegistrationCommand());
		commands.put("registration", new RegistrationCommand());
		commands.put("go_to_default", new GoToDefaultPageCommand());
		commands.put("change_locale", new ChangeLocale());
		commands.put("go_to_add_new_dish_page", new GoToAddNewDishCommand());
		commands.put("goToAllDishes", new DishesRedirectCommand());
		commands.put("save_new_dish", new SaveNewDishCommand());
		commands.put("dish_info", new GoToInfoDishCommand());
		commands.put("edit_dish", new EditDishCommand());
	}
	
	public Command getCommand(String commandName) {
		return commands.get(commandName);
	}

}

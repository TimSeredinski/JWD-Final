package by.training.epam.seredinski.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.training.epam.seredinski.controller.command.impl.AuthorizationCommand;
import by.training.epam.seredinski.controller.command.impl.ChangeLocale;
import by.training.epam.seredinski.controller.command.impl.GoToAddNewDishCommand;
import by.training.epam.seredinski.controller.command.impl.GoToDefaultPageCommand;
import by.training.epam.seredinski.controller.command.impl.GoToRegistrationCommand;
import by.training.epam.seredinski.controller.command.impl.RegistrationCommand;
import by.training.epam.seredinski.controller.command.impl.SaveNewDishCommand;
import by.training.epam.seredinski.controller.command.impl.ShowInfoDishCommand;

public class CommandProvider {
	
	private Map<String, Command> commands = new HashMap<>();
	
	public CommandProvider() {
		commands.put("authorization", new AuthorizationCommand());
		commands.put("goToRegistrationPage", new GoToRegistrationCommand());
		commands.put("registration", new RegistrationCommand());
		commands.put("go_to_default", new GoToDefaultPageCommand());
		commands.put("change_locale", new ChangeLocale());
		commands.put("go_to_add_new_dish_page", new GoToAddNewDishCommand());
		commands.put("save_new_dish", new SaveNewDishCommand());
		commands.put("dish_info", new ShowInfoDishCommand());
	}
	
	public Command getCommand(String commandName) {
		return commands.get(commandName);
	}

}

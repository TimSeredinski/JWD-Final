package by.training.epam.seredinski.service.validation;

public class CredentionalValidator {
	
	public static boolean isCorrect(String login, String password) {
		return isLoginCorrect(login) && isPasswordCorrect(password);
	}
	
	private static boolean isLoginCorrect(String login) {
		return login.matches("^([A-Za-z0-9])+");
	}
	
	private static boolean isPasswordCorrect(String password) {
		return password.matches("^([A-Za-z0-9])+");
	}

}

package by.training.epam.seredinski.validation;

import by.training.epam.seredinski.entity.Dish;
import by.training.epam.seredinski.entity.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtil {

    private static final String MAIL = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
    private static final String STRING_FIELD = "^.{4,30}$";
    private static final String LOGIN = "^[a-zA-Z0-9_]{4,30}$";

    public static boolean validateUser(User user) {
        System.out.println(check(user.getLogin(), LOGIN));
        System.out.println(check(user.getName(), STRING_FIELD));
        System.out.println(check(user.getSurname(), STRING_FIELD));
        System.out.println(user.getMail());
        System.out.println(check(user.getMail(), MAIL));
        return (check(user.getLogin(), LOGIN) &&
                check(user.getName(), STRING_FIELD) &&
                check(user.getSurname(), STRING_FIELD) &&
                check(user.getMail(), MAIL));
    }

    public static boolean validateDish(Dish dish) {
        return (check(dish.getName(), STRING_FIELD) && dish.getWeight() > 0 && dish.getPrice() >= 0);
    }

    private static boolean check(String string, String regEx) {
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
}

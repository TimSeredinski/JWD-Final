package by.training.epam.seredinski.validation;

import by.training.epam.seredinski.entity.Address;
import by.training.epam.seredinski.entity.Dish;
import by.training.epam.seredinski.entity.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtil {

    private static final String MAIL = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
    private static final String STRING_FIELD = "^.{4,30}$";
    private static final String LOGIN = "^[a-zA-Z0-9_]{4,30}$";

    public static boolean validateUser(User user) {
        System.out.println(user.getLogin() + check(user.getLogin(), LOGIN));
        System.out.println(user.getName() + check(user.getName(), STRING_FIELD));
        System.out.println(user.getSurname() + check(user.getSurname(), STRING_FIELD));
        System.out.println(user.getMail() + check(user.getMail(), MAIL));
        return (check(user.getLogin(), LOGIN) &&
                check(user.getName(), STRING_FIELD) &&
                check(user.getSurname(), STRING_FIELD) &&
                check(user.getMail(), MAIL));
    }

    public static boolean validateDish(Dish dish) {
        return (check(dish.getName(), STRING_FIELD) && dish.getWeight() > 0 && dish.getPrice() >= 0);
    }

    public static boolean validateAddress (Address address) {
        return (check(address.getStreet(), STRING_FIELD) &&
                (address.getHouse() >= 1) &&
                (address.getFlat() >= 1));
    }

    private static boolean check(String string, String regEx) {
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
}

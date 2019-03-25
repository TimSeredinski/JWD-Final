package by.training.epam.seredinski.constant;

public class Constants {

    public static final String CONFIG_PATH = "WEB-INF/classes/resources/log4j2.xml";

    public static final String DISHES_PAGE = "/WEB-INF/jsp/dishes.jsp";
    public static final String DISH_INFO_PAGE = "/WEB-INF/jsp/dishInfo.jsp";
    public static final String DEFAULT_PAGE = "/WEB-INF/jsp/default.jsp";
    public static final String REGISTRATION_PAGE = "/WEB-INF/jsp/registration.jsp";
    public static final String NEW_DISH_FORM = "/WEB-INF/jsp/newDishForm.jsp";
    public static final String ORDER_PAGE = "/WEB-INF/jsp/order.jsp";
    public static final String ADDRESS_PAGE = "/WEB-INF/jsp/address.jsp";
    public static final String USER_ORDERS_PAGE = "/WEB-INF/jsp/userOrders.jsp";
    public static final String ACCESS_DENIED_PAGE = "/WEB-INF/jsp/accessDeniedPage.jsp";
    public static final String ERROR_PAGE = "/WEB-INF/jsp/errorPage.jsp";

    public static final String REDIRECT_COMMON = "/controller?command=";
    public static final String REDIRECT_DISH_PAGE = "dish_info";
    public static final String REDIRECT_CREATE_DISH_PAGE = "admin_go_to_add_new_dish_page";
    public static final String REDIRECT_MAIN_PAGE = "go_to_default";
    public static final String REDIRECT_DISHES_PAGE = "go_to_all_dishes";
    public static final String REDIRECT_REGISTRATION = "guest_go_to_registration_page";

    public static final String PREV_REQUEST = "prev_request";
    public static final String ORDER_PRICE = "orderPrice";
    public static final String USER_ORDER = "userOrder";
    public static final String USER_ORDERS = "userOrders";
    public static final String ORDER_DISH_ID = "orderDishId";
    public static final String DISHES = "dishes";
    public static final String DISH = "dish";

    public static final String PARAMETER_EMAIL = "email";
    public static final String PARAMETER_MAIL = "mail";
    public static final String PARAMETER_PASSWORD = "password";
    public static final String PARAMETER_LOGIN = "login";
    public static final String PARAMETER_NAME = "name";
    public static final String PARAMETER_SURNAME = "surname";
    public static final String PARAMETER_ROLE = "role";

    public static final String PARAMETER_ID = "id";
    public static final String PARAMETER_TYPE = "type";
    public static final String PARAMETER_DESCRIPTION = "description";
    public static final String PARAMETER_WEIGHT = "weight";
    public static final String PARAMETER_PRICE = "price";
    public static final String PARAMETER_DISH_TYPE = "dishType";
    public static final String PARAMETER_EDITED_DISH_ID = "editedDishId";
    public static final String PARAMETER_EDITED_DISH = "editedDish";
    public static final String PARAMETER_DELETED_DISH_ID = "deletedDishId";
    public static final String PARAMETER_USER_ID = "userId";
    public static final String PARAMETER_ADDRESS_ID = "addressId";
    public static final String PARAMETER_DATE_TIME = "date";
    public static final String PARAMETER_COUNT_OF_DISHES = "count_dishes";

    public static final String PARAMETER_CITY = "city";
    public static final String PARAMETER_STREET = "street";
    public static final String PARAMETER_HOUSE = "house";
    public static final String PARAMETER_FLAT = "flat";

    public static final String ERROR_REGISTRATION_MESSAGE = "You haven't been signed up";
    public static final String ERROR_LOGIN_MESSAGE = "Wrong login or password";

}

package by.training.epam.seredinski.controller.command.impl;

import by.training.epam.seredinski.controller.command.Command;
import by.training.epam.seredinski.entity.Dish;
import by.training.epam.seredinski.exception.ServiceException;
import by.training.epam.seredinski.service.DishService;
import by.training.epam.seredinski.service.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SaveNewDishCommand implements Command {

    private static final String PARAMETER_NAME = "name";
    private static final String PARAMETER_DESCRIPTION = "description";
    private static final String PARAMETER_WEIGHT = "weight";
    private static final String PARAMETER_PRICE = "price";
    private static final String PARAMETER_ID = "editedDishId";

    private static final String COMMON = "/controller?command=";
    private static final String DISHES_PAGE = "goToAllDishes";
    private static final String CREATE_DISH_PAGE = "go_to_add_new_dish_page";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter(PARAMETER_ID);
        String page;
        String name = request.getParameter(PARAMETER_NAME);
        String description = request.getParameter(PARAMETER_DESCRIPTION);
        String weight = request.getParameter(PARAMETER_WEIGHT);
        String price = request.getParameter(PARAMETER_PRICE);
        request.setAttribute("dishTypes", Dish.DishType.values());
        Dish.DishType type = Dish.DishType.valueOf(request.getParameter("type").toUpperCase());

//        HttpSession session = request.getSession(false);
//        if (session == null) {
//        }
//		String role = (String) session.getAttribute("role");
//		if (!role.equals("admin")) {
//		}
//         Service (LibraryService)  service.save(b);  mysql id

        ServiceProvider provider = ServiceProvider.getInstance();
        DishService service = provider.getDishService();
        Dish dish;
        try {
            if(id.equals("")) {
                dish = service.createDish(name, description, type, Integer.parseInt(weight), Integer.parseInt(price));
            } else {
                dish = service.updateDish(Integer.parseInt(id), name, description, type, Integer.parseInt(weight), Integer.parseInt(price));
            }
            request.setAttribute("dish", dish);
            page = DISHES_PAGE;
        } catch (ServiceException | NumberFormatException e) {
            request.setAttribute("error", "Something is wrong ;)");
            page = CREATE_DISH_PAGE;
        }
        response.sendRedirect(COMMON + page);

    }

}

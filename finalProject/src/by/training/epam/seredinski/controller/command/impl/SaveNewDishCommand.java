package by.training.epam.seredinski.controller.command.impl;

import by.training.epam.seredinski.controller.command.Command;
import by.training.epam.seredinski.entity.Dish;
import by.training.epam.seredinski.enums.DishType;
import by.training.epam.seredinski.exception.ServiceException;
import by.training.epam.seredinski.service.DishService;
import by.training.epam.seredinski.service.ServiceProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class SaveNewDishCommand implements Command {

    private static final String PARAMETER_NAME = "name";
    private static final String PARAMETER_DESCRIPTION= "description";
    private static final String PARAMETER_WEIGHT = "weight";
    private static final String PARAMETER_PRICE = "price";

    private static final String CREATE_DISH_PAGE = "/WEB-INF/jsp/newDishForm.jsp";
    private static final String INFO_DISH_PAGE = "/WEB-INF/jsp/DishInfo.jsp";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        String name = request.getParameter(PARAMETER_NAME);
        String description = request.getParameter(PARAMETER_DESCRIPTION);
        String weight = request.getParameter(PARAMETER_WEIGHT);
        String price = request.getParameter(PARAMETER_PRICE);
        DishType type = DishType.valueOf(request.getParameter("type").toUpperCase());
//        HttpSession session = request.getSession(false);
//        if (session == null) {
//        }
//		String role = (String) session.getAttribute("role");
//		if (!role.equals("admin")) {
//		}
        // Service (LibraryService)  service.save(b);  mysql id

        ServiceProvider provider = ServiceProvider.getInstance();
        DishService service = provider.getDishService();
        Dish dish = null;
        try {
            dish = service.createDish(name, description, type, Integer.parseInt(weight), Integer.parseInt(price));
            page = INFO_DISH_PAGE;
        } catch (ServiceException e) {
            request.setAttribute("error", "Something is wrong ;)");
            page = CREATE_DISH_PAGE;
        }

        request.setAttribute("dish", dish);

//        String url = "controller?command=book_info&id_book=" + b.getId() + "&new_book=All AOK!!!";
//        response.sendRedirect(url);

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);

    }

}

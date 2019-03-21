package by.training.epam.seredinski.filter;

import by.training.epam.seredinski.constant.Constants;
import by.training.epam.seredinski.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccessFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        if (session.getAttribute("userRole") == null) {
            session.setAttribute("userRole", User.UserRole.GUEST);
        }
        User.UserRole role = (User.UserRole) session.getAttribute("userRole");
        System.out.println(role);
        StringBuffer requestURL = req.getRequestURL();
        if (req.getQueryString() != null) {
            requestURL.append("?").append(req.getQueryString());
        }
        String completeURL = requestURL.toString();
        System.out.println(completeURL);
        User.UserRole[] rolesArray = {User.UserRole.ADMIN, User.UserRole.CLIENT, User.UserRole.GUEST};
        for (User.UserRole curRole : rolesArray) {
            Pattern p = Pattern.compile(".+?command=" + curRole.toString().toLowerCase() + "_.+");
            Matcher m = p.matcher(completeURL);
            if (m.matches() && role != curRole) {
                request.setAttribute("errorText", "You can not access the requested page.");
                request.getServletContext().getRequestDispatcher(Constants.ERROR_PAGE).forward(request, response);
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}

package by.training.epam.seredinski.filter;

import by.training.epam.seredinski.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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

        System.out.println(session.getAttribute("userRole"));
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}

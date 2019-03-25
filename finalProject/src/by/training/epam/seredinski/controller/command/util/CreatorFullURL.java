package by.training.epam.seredinski.controller.command.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

public class CreatorFullURL {

    public static String create(HttpServletRequest request) {
        String url = "";
        Enumeration<String> paramNames2 = request.getParameterNames();
        String paramName;
        String paramValue;
        while (paramNames2.hasMoreElements()) {
            paramName = paramNames2.nextElement();
            paramValue = request.getParameter(paramName);
            url = url + paramName + "=" + paramValue + "&";
        }
        url = request.getRequestURL() + "?" + url;
        System.out.println(url);
        return url;

    }

}

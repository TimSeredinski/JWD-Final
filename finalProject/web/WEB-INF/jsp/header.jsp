<%--
  Created by IntelliJ IDEA.
  User: Tim
  Date: 11.03.2019
  Time: 12:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="resources.locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.default.locale_button.ru" var="locale_button_ru"/>
<fmt:message bundle="${loc}" key="locale.default.locale_button.en" var="locale_button_en"/>
<fmt:message bundle="${loc}" key="locale.default.login_message" var="login"/>
<fmt:message bundle="${loc}" key="locale.default.password_message" var="password"/>
<fmt:message bundle="${loc}" key="locale.button.add_dish" var="add_dish"/>
<fmt:message bundle="${loc}" key="locale.button.submit" var="submit"/>
<fmt:message bundle="${loc}" key="locale.default.registration" var="registration"/>
<fmt:message bundle="${loc}" key="locale.default.menu" var="menu"/>
<fmt:message bundle="${loc}" key="locale.default.order" var="order"/>
<fmt:message bundle="${loc}" key="locale.default.history" var="history"/>
<fmt:message bundle="${loc}" key="locale.default.main_page" var="main_page"/>
<fmt:message bundle="${loc}" key="locale.default.logout" var="logout_button"/>
<fmt:message bundle="${loc}" key="locale.dish.type.pizza" var="pizza"/>
<fmt:message bundle="${loc}" key="locale.dish.type.burger" var="burger"/>
<fmt:message bundle="${loc}" key="locale.dish.type.dessert" var="dessert"/>
<html>
<head>
    <title>Title</title>
    <c:import url="/WEB-INF/jsp/links.jsp"/>
</head>
<body>
<style>
    .dropdown:hover > .dropdown-menu {
        display: block;
    }
</style>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="#">Brand</a>
    <div>
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="controller?command=go_to_default"> ${main_page}</a>
            </li>
            <li class="nav-item">
                <%--<a class="nav-link" href="controller?command=go_to_all_dishes">${menu}</a>--%>
                <div class="dropdown">
                    <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">${menu}</a>
                    <div class="dropdown-menu bg-dark">
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="go_to_all_dishes">
                            <input type="hidden" name="dishType" value="pizza">
                            <button class="btn btn-dark" type="submit">${pizza}</button>
                        </form>
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="go_to_all_dishes">
                            <input type="hidden" name="dishType" value="burger">
                            <button class="btn btn-dark" type="submit">${burger}</button>
                        </form>
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="go_to_all_dishes">
                            <input type="hidden" name="dishType" value="dessert">
                            <button class="btn btn-dark" type="submit">${dessert}</button>
                        </form>
                    </div>
                </div>
            </li>
            <c:choose>
            <c:when test="${userRole == 'CLIENT'}">
            <li class="nav-item">
                <a class="nav-link" href="controller?command=client_go_to_order">${order}</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="controller?command=client_go_to_orders_history">${history}</a>
            </li>
            </c:when>
            </c:choose>
            <li class="nav-item">
                <form class="form-inline" action="controller" method="post">
                    <input type="hidden" name="command" value="change_locale">
                    <input type="hidden" name="locale" value="ru">
                    <button class="btn btn-dark" type="submit">${locale_button_ru}</button>
                </form>
            </li>
            <li class="nav-item">
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="change_locale">
                    <input type="hidden" name="locale" value="en">
                    <button class="btn btn-dark" type="submit">${locale_button_en}</button>
                </form>
            </li>
            <c:choose>
            <c:when test="${userRole == 'GUEST'}">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true"
                   aria-expanded="false">Войти</a>
                <ul class="dropdown-menu">
                    <li>
                        <form class="px-4 py-3" action="controller" method="post">
                            <input type="hidden" name="command" value="authorization">
                            <div class="form-group">
                                <label>${login}</label>
                                <input type="text" class="form-control" name="login" placeholder="Login" value=""/>
                            </div>
                            <div class="form-group">
                                <label>${password}</label>
                                <input type="password" class="form-control" name="password" placeholder="Password"
                                       value=""/>
                            </div>
                            <input type="submit" class="btn btn-primary" value=${submit}>
                        </form>
                    </li>
                </ul>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="controller?command=guest_go_to_registration_page">${registration}</a>
            </li>
            </c:when>
            <c:when test="${userRole == 'CLIENT'|| userRole == 'ADMIN'}">
            <form action="controller" method="post">
                <input type="hidden" name="command" value="logout">
                <button class="btn btn-dark" type="submit">${logout_button}</button>
            </form>
            </c:when>
            </c:choose>
    </div>
</nav>
</body>
</html>

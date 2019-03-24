<%--
  Created by IntelliJ IDEA.
  User: Tim
  Date: 20.03.2019
  Time: 21:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Title</title>
    <c:import url="/WEB-INF/jsp/links.jsp"/>
</head>
<body>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="resources.locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.default.locale_button.ru" var="locale_button_ru"/>
<fmt:message bundle="${loc}" key="locale.default.locale_button.en" var="locale_button_en"/>
<fmt:message bundle="${loc}" key="locale.default.main_page" var="main_page"/>
<fmt:message bundle="${loc}" key="locale.default.menu" var="menu"/>
<fmt:message bundle="${loc}" key="locale.dish.make_order" var="make_order"/>
<fmt:message bundle="${loc}" key="locale.default.history" var="orders"/>
<c:import url="/WEB-INF/jsp/header.jsp"/>

<div class="main-div">
    <div class="container">
        <h2>${orders}</h2>
        <c:choose>
            <c:when test="${userOrders != null}">
                <div class="dishes-context">
                    <table class="table">
                        <thead class="thead-dark">
                        <tr>
                            <th scope="col"><fmt:message key="locale.order.date" bundle="${loc}"/></th>
                            <th scope="col"><fmt:message key="locale.order.dishes" bundle="${loc}"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${userOrders}" var="order">
                        <jsp:useBean id="dish" class="by.training.epam.seredinski.entity.Dish" type="java.lang.Object"
                                     scope="request"/>
                        <tr>
                            <td><c:out value="${order.getDateTime().getTime()}"/></td>
                            <td>
                                <div class="dropdown col-3">
                                    <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown"
                                            aria-haspopup="true" aria-expanded="true">
                                        <fmt:message key="locale.order.dishes" bundle="${loc}"/>
                                        <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu custom-dropdown" aria-labelledby="dropdownMenu1">
                                        <c:forEach items="${order.dishes}" var="dish">
                                            <li><c:out value="${dish.name} : ${dish.amount}"/></li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                        </c:forEach>
                    </table>
                </div>
            </c:when>
            <c:when test="${userOrders == null}">
                <div class="alert alert-warning" role="alert">
                    <h4 class="alert-heading"><fmt:message key="locale.order.empty" bundle="${loc}"/></h4>
                </div>
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="go_to_all_dishes">
                    <input type="hidden" name="dishType" value="pizza">
                    <button class="btn btn-primary" type="submit">${make_order}</button>
                </form>
            </c:when>
        </c:choose>
    </div>
</div>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: Tim
  Date: 05.03.2019
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Insert title here</title>
    <c:import url="/WEB-INF/jsp/links.jsp"/>
</head>
<body>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="resources.locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.default.locale_button.ru" var="locale_button_ru"/>
<fmt:message bundle="${loc}" key="locale.default.locale_button.en" var="locale_button_en"/>
<fmt:message bundle="${loc}" key="locale.button.add_dish" var="add_dish"/>
<fmt:message bundle="${loc}" key="locale.default.main_page" var="main_page"/>
<fmt:message bundle="${loc}" key="locale.default.menu" var="menu"/>
<fmt:message bundle="${loc}" key="locale.button.change" var="change_dish_button"/>
<fmt:message bundle="${loc}" key="locale.button.delete" var="delete_dish_button"/>
<fmt:message bundle="${loc}" key="locale.dish.add_to_order" var="add_dish_to_order_button"/>

<c:import url="/WEB-INF/jsp/header.jsp"/>

<header class="custom-header main-page-header">
</header>
<div class="dishes-context-container">
    <div class="container main-div">
        <h2>${menu}</h2>
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col"><fmt:message key="locale.dish.name" bundle="${loc}"/></th>
                <th scope="col"><fmt:message key="locale.dish.description" bundle="${loc}"/></th>
                <th scope="col"><fmt:message key="locale.dish.type" bundle="${loc}"/></th>
                <th scope="col"><fmt:message key="locale.dish.weight" bundle="${loc}"/></th>
                <th scope="col"><fmt:message key="locale.dish.price" bundle="${loc}"/></th>
                <th scope="col"></th>
                <c:choose>
                    <c:when test="${userRole == 'ADMIN'}">
                        <th scope="col"></th>
                    </c:when>
                </c:choose>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${dishes}" var="dish">
            <jsp:useBean id="dish" class="by.training.epam.seredinski.entity.Dish" type="java.lang.Object"
                         scope="request"/>
            <tr>
                <td><c:out value="${dish.name}"/></td>
                <td><c:out value="${dish.description}"/></td>
                <td><c:out value="${dish.type}"/></td>
                <td><c:out value="${dish.weight}"/></td>
                <td><c:out value="${dish.price}"/></td>
                <c:choose>
                    <c:when test="${userRole == 'ADMIN'}">
                        <td>
                            <form action="controller" method="post">
                                <input type="hidden" name="command" value="admin_update_dish">
                                <input type="hidden" name="editedDishId" value="${dish.id}">
                                <button class="btn btn-outline-secondary" type="submit">${change_dish_button}</button>
                            </form>
                        </td>
                        <td>
                            <form action="controller" method="post">
                                <input type="hidden" name="command" value="admin_delete_dish">
                                <input type="hidden" name="deletedDishId" value="${dish.id}">
                                <button class="btn btn-outline-secondary" type="submit">${delete_dish_button}</button>
                            </form>
                        </td>
                    </c:when>
                    <c:when test="${userRole == 'CLIENT'}">
                        <td>
                            <form action="controller" method="post">
                                <input type="hidden" name="command" value="client_add_to_order">
                                <input type="hidden" name="orderDishId" value="${dish.id}">
                                <button class="btn btn-outline-secondary" type="submit">
                                        ${add_dish_to_order_button}
                                </button>
                            </form>
                        </td>
                    </c:when>
                </c:choose>
            </tr>
            </tbody>
            </c:forEach>
        </table>
        <c:choose>
            <c:when test="${userRole == 'ADMIN'}">
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="admin_go_to_add_new_dish_page">
                    <button class="btn btn-primary" type="submit">${add_dish}</button>
                </form>
            </c:when>
        </c:choose>
    </div>
</div>
</body>
</html>

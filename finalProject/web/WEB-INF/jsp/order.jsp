<%--
  Created by IntelliJ IDEA.
  User: Tim
  Date: 16.03.2019
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
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
<fmt:message bundle="${loc}" key="locale.default.main_page" var="main_page"/>
<fmt:message bundle="${loc}" key="locale.dish.make_order" var="make_order"/>
<fmt:message bundle="${loc}" key="locale.button.delete" var="delete_dish_button"/>
<c:import url="/WEB-INF/jsp/header.jsp"/>

<header class="custom-header main-page-header">
</header>
<div class="dishes-context-container">
    <div class="container main-div">
        <c:choose>
            <c:when test="${userOrder != null}">
                <table class="table">
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col"><fmt:message key="locale.dish.name" bundle="${loc}"/></th>
                        <th scope="col"><fmt:message key="locale.dish.description" bundle="${loc}"/></th>
                        <th scope="col"><fmt:message key="locale.dish.type" bundle="${loc}"/></th>
                        <th scope="col"><fmt:message key="locale.dish.amount" bundle="${loc}"/></th>
                        <th scope="col"><fmt:message key="locale.dish.price" bundle="${loc}"/></th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${userOrder}" var="dish">
                    <jsp:useBean id="dish" class="by.training.epam.seredinski.entity.Dish" type="java.lang.Object"
                                 scope="request"/>
                    <tr>
                        <td><c:out value="${dish.name}"/></td>
                        <td><c:out value="${dish.description}"/></td>
                        <td><c:out value="${dish.type}"/></td>
                        <td><c:out value="${dish.amount}"/></td>
                        <td><c:out value="${dish.price}"/></td>
                        <td>
                            <form action="controller" method="post">
                                <input type="hidden" name="command" value="client_delete_dish_from_order">
                                <input type="hidden" name="deletedDishId" value="${dish.id}">
                                <button class="btn btn-outline-secondary" type="submit">${delete_dish_button}</button>
                            </form>
                        </td>
                    </tr>
                    </tbody>
                    </c:forEach>
                </table>
                <div class="alert alert-success" role="alert">
                    <h4 class="alert-heading">Итог: <c:out value="${orderPrice}"/></h4>
                </div>
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="client_go_to_address">
                    <button class="btn btn-outline-success" type="submit">${make_order}</button>
                </form>
            </c:when>
            <c:when test="${userOrder == null}">
                <div class="alert alert-warning" role="alert">
                    <h4 class="alert-heading"><fmt:message key="locale.order.empty" bundle="${loc}"/></h4>
                </div>
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="go_to_all_dishes">
                    <input type="hidden" name="dishType" value="pizza">
                    <button class="btn my-def-btn" type="submit">${make_order}</button>
                </form>
            </c:when>
        </c:choose>
    </div>
</div>
</body>
</html>

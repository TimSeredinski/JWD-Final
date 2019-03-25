<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Insert title here</title>
    <c:import url="/WEB-INF/jsp/links.jsp"/>
</head>
<body>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="resources.locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.dish.name" var="name"/>
<fmt:message bundle="${loc}" key="locale.dish.description" var="description"/>
<fmt:message bundle="${loc}" key="locale.dish.weight" var="weight"/>
<fmt:message bundle="${loc}" key="locale.dish.price" var="price"/>
<fmt:message bundle="${loc}" key="locale.dish.type" var="type"/>
<fmt:message bundle="${loc}" key="locale.default.locale_button.ru" var="locale_button_ru"/>
<fmt:message bundle="${loc}" key="locale.default.locale_button.en" var="locale_button_en"/>
<fmt:message bundle="${loc}" key="locale.default.main_page" var="main_page"/>
<fmt:message bundle="${loc}" key="locale.button.submit" var="submit"/>
<fmt:message bundle="${loc}" key="locale.default.add-dish" var="add"/>
<fmt:message bundle="${loc}" key="locale.default.edit-dish" var="edit"/>

<c:import url="header.jsp"/>

<header class="custom-header main-page-header">
</header>
<div class="dishes-context-container">
    <div class="container main-div">
        <c:choose>
        <c:when test="${editedDish != null}">
            <h2>${edit}</h2>
        </c:when>
        <c:otherwise>
            <h2>${add}</h2>
        </c:otherwise>
        </c:choose>

        <form class="input-form" action="controller" method="post">
            <input type="hidden" name="command" value="admin_save_new_dish">
            <input type="hidden" name="editedDishId" value="${editedDish.id}">
            <input type="text" name="name" pattern="^.{3,30}$" value="${editedDish.name}"
                   class="form-control form-group"
                   placeholder=" ${name}" required>
            <input type="text" name="description" value="${editedDish.description}" class="form-control form-group"
                   placeholder="${description}">
            ${type}
            <select size="1" name="type" class="form-control form-group">
                <c:forEach items="${dishType}" var="type">
                    <option
                            <c:if test="${editedDish.type == type}">
                                selected="selected"
                            </c:if>
                            value="${type.toString()}">
                        <fmt:message key="locale.dish.type.${type.toString().toLowerCase()}" bundle="${loc}"/>
                    </option>
                </c:forEach>
            </select>
            <input type="number" min="1" name="weight" value="${editedDish.weight}" class="form-control form-group"
                   placeholder="${weight}" required>
            <input type="number" min="0" name="price" value="${editedDish.price}" class="form-control form-group"
                   placeholder="${price}" required>

            <input type="submit" class="btn btn-primary" value=${submit}>
        </form>
</body>
</html>
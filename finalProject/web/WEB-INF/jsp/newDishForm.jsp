<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Insert title here</title>
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

<div align="right">
    <form action="controller" method="post">
        <input type="hidden" name="command" value="change_locale">
        <input type="hidden" name="locale" value="ru">
        <input type="submit" name="${locale_button_ru}" value="${locale_button_ru}"/>
    </form>
    <form action="controller" method="post">
        <input type="hidden" name="command" value="change_locale">
        <input type="hidden" name="locale" value="en">
        <input type="submit" name="${locale_button_en}" value="${locale_button_en}"/>
    </form>
</div>

<form action="controller" method="post">
    <input type="hidden" name="command" value="save_new_dish">
    <input type="hidden" name="editedDishId" value="${editedDish.id}">
${name}:
    <input type="text" name="name" value="${editedDish.name}"/>
    <br/>
    ${description}:
    <input type="text" name="description" value="${editedDish.description}"/>
    <br/>
    ${type}
    <select size="1" name="type">
        <c:forEach items="${dishTypes}" var="type">
            <option value="${type.toString()}">
                <fmt:message key="locale.dish.type.${type.toString().toLowerCase()}" bundle="${loc}"/>
            </option>
        </c:forEach>
    </select>
    <br/>
    ${weight}:
    <input type="number" name="weight" value="${editedDish.weight}"/>
    <br/>
    ${price}:
    <input type="number" name="price" value="${editedDish.price}"/>
    <br/>

    <input type="submit" name="submit" value=${submit}>
</form>

<h3>
    <c:out value="${requestScope.error}"/>
</h3>
<h2>
    <a href="controller?command=go_to_default">${main_page}</a>
</h2>
</body>
</html>
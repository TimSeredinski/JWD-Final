<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Insert title here</title>
</head>
<body>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="resources.locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.default.welcome_message" var="welcome_message"/>
<fmt:message bundle="${loc}" key="locale.default.locale_button.ru" var="locale_button_ru"/>
<fmt:message bundle="${loc}" key="locale.default.locale_button.en" var="locale_button_en"/>
<fmt:message bundle="${loc}" key="locale.default.login_message" var="login"/>
<fmt:message bundle="${loc}" key="locale.default.password_message" var="password"/>
<fmt:message bundle="${loc}" key="locale.button.add_dish" var="add_dish"/>
<fmt:message bundle="${loc}" key="locale.button.submit" var="submit"/>
<fmt:message bundle="${loc}" key="locale.default.registration" var="registration"/>

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


<h1>${welcome_message}</h1>

<form action="controller" method="post">
    <input type="hidden" name="command" value="authorization">
    ${login}
    <input type="text" name="login" value=""/>
    <br/>
    ${password}
    <input type="password" name="password" value=""/>
    <br/>
    <input type="submit" name="submit" value=${submit}>
</form>

<h3>
    <c:out value="${requestScope.error}"/>
</h3>

<a href="controller?command=goToRegistrationPage">${registration}</a>

<br/>
<br/>
<br/>

<table border="1" rules="all" cellpadding="10">
    <thead>
    <tr>
        <th><fmt:message key="locale.dish.name" bundle="${loc}"/></th>
        <th><fmt:message key="locale.dish.description" bundle="${loc}"/></th>
        <th><fmt:message key="locale.dish.type" bundle="${loc}"/></th>
        <th><fmt:message key="locale.dish.weight" bundle="${loc}"/></th>
        <th><fmt:message key="locale.dish.price" bundle="${loc}"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${dishes}" var="dish">
    <jsp:useBean id="dish" class="by.training.epam.seredinski.entity.Dish" type="java.lang.Object" scope="request"/>
    <tr>
        <td><c:out value="${dish.name}"/></td>
        <td><c:out value="${dish.description}"/></td>
        <td><c:out value="${dish.type}"/></td>
        <td><c:out value="${dish.weight}"/></td>
        <td><c:out value="${dish.price}"/></td>
    </tr>
    </tbody>
    </c:forEach>
</table>

<a href="controller?command=go_to_add_new_dish_page">${add_dish}</a>

</body>
</html>
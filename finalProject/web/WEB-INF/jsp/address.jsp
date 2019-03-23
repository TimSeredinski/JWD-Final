<%--
  Created by IntelliJ IDEA.
  User: Tim
  Date: 17.03.2019
  Time: 23:31
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
<fmt:message key="locale.flat" bundle="${loc}" var="flat"/>
<fmt:message key="locale.house" bundle="${loc}" var="house"/>
<fmt:message key="locale.street" bundle="${loc}" var="street"/>
<c:import url="/WEB-INF/jsp/header.jsp"/>

<div class="container">
    <form class="input-form" method="post" action="controller">
        <input type="hidden" name="command" value="client_save_new_order">
        <input type="text" class="form-control card-input" name="city" value="Minsk" readonly>
        <input type="text" pattern="^.{3,30}$" class="form-control card-input" name="street" value="${addr.street}"
               placeholder="${street}" required autofocus>
        <input type="number" min="1" class="form-control card-input" name="house" value="${addr.house}"
               placeholder="${house}" required>
        <input type="number" min="1" class="form-control card-input" name="flat" value="${addr.flat}"
               placeholder="${flat}" required>
        <button class="btn btn-outline-success" type="submit">${make_order}</button>
    </form>
</div>
</body>
</html>

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

<fmt:message bundle="${loc}" key="locale.email_message" var="email"/>
<fmt:message bundle="${loc}" key="locale.password_message" var="password"/>
<fmt:message bundle="${loc}" key="locale.login_message" var="login"/>
<fmt:message bundle="${loc}" key="locale.name_message" var="name"/>
<fmt:message bundle="${loc}" key="locale.surname_message" var="surname"/>
<fmt:message bundle="${loc}" key="locale.default.locale_button.ru" var="locale_button_ru"/>
<fmt:message bundle="${loc}" key="locale.default.locale_button.en" var="locale_button_en"/>
<fmt:message bundle="${loc}" key="locale.default.main_page" var="main_page"/>
<fmt:message bundle="${loc}" key="locale.button.submit" var="submit"/>

<c:import url="/WEB-INF/jsp/header.jsp"/>

<header class="custom-header main-page-header">
</header>
<div class="dishes-context-container">
    <div class="container main-div">
        <form class="input-form" action="controller" method="post">
            <input type="hidden" name="command" value="guest_registration">
            <input type="email" pattern="^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$"
                   name="email" class="form-control form-group" placeholder="${email}" required>
            <input type="password" pattern="^.{4,30}$" id="txtPassword" name="password" class="form-control form-group"
                   placeholder="${password}" required>
            <input type="text" pattern="^[a-zA-Z0-9_]{4,30}$" name="login" class="form-control form-group"
                   placeholder="${login}" required>
            <input type="text" pattern="^.{3,30}$" name="name" class="form-control form-group"
                   placeholder="${name}" required>
            <input type="text" pattern="^.{3,30}$" name="surname" class="form-control form-group"
                   placeholder="${surname}" required>
            <c:choose>
                <c:when test="${errorRegistrationMessage != null}">
                    <div class="alert alert-danger" role="alert">
                            ${errorRegistrationMessage}
                    </div>
                </c:when>
            </c:choose>
            <input type="submit" class="btn btn-primary" value=${submit}>
        </form>
    </div>
</div>
</body>
</html>
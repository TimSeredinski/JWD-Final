<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
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

<form action="controller" method="post">
    <input type="hidden" name="command" value="guest_registration">
    ${email}:
    <input type="text" name="email" value=""/>
    <br/>
    ${password}:
    <input type="password" name="password" value=""/>
    <br/>
    ${login}:
    <input type="text" name="login" value=""/>
    <br/>
    ${name}:
    <input type="text" name="name" value=""/>
    <br/>
    ${surname}:
    <input type="text" name="surname" value=""/>
    <br/>

    <input type="submit" value=${submit}>
</form>

<h2>
    <a href="controller?command=go_to_default">${main_page}</a>
</h2>
</body>
</html>
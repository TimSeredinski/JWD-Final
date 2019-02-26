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
<fmt:message bundle="${loc}" key="locale.email_message" var="email"/>
<fmt:message bundle="${loc}" key="locale.password_message" var="password"/>
<fmt:message bundle="${loc}" key="locale.login_message" var="login"/>
<fmt:message bundle="${loc}" key="locale.name_message" var="name"/>
<fmt:message bundle="${loc}" key="locale.surname_message" var="surname"/>
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
    <input type="hidden" name="command" value="registration">
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
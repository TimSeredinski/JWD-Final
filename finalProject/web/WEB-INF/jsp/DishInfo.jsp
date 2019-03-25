<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
<fmt:message bundle="${loc}" key="locale.default.main_page" var="main_page"/>
<fmt:message bundle="${loc}" key="locale.default.dish" var="titleDish"/>
<fmt:message bundle="${loc}" key="locale.dish.description" var="description"/>
<fmt:message bundle="${loc}" key="locale.dish.weight" var="weight"/>
<fmt:message bundle="${loc}" key="locale.dish.price" var="price"/>
<fmt:message bundle="${loc}" key="locale.dish.type" var="type"/>

<c:import url="/WEB-INF/jsp/header.jsp"/>
<header class="custom-header main-page-header">
</header>
<div class="dishes-context-container">
    <div class="container main-div">
        <h2>${titleDish}</h2>
        <h4><fmt:message key="locale.dish.name" bundle="${loc}"/>: <c:out value="${dish.name}"/></h4>
        <h4>${description}: <c:out value="${dish.description}"/></h4>
        <h4>${type}: <c:out value="${dish.type}"/></h4>
        <h4>${weight}: <c:out value="${dish.weight}"/> </h4>
        <h4>${price}: <c:out value="${dish.price}"/></h4>
    </div>
</div>
</body>
</html>
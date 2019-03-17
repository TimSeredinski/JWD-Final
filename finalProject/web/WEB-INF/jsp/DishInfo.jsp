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
<fmt:message bundle="${loc}" key="locale.default.main_page" var="main_page"/>

<c:import url="/WEB-INF/jsp/header.jsp"/>
<h1>Dish</h1>
<h4><c:out value="${sessionScope.dish.name}"/></h4>
<h4><c:out value="${sessionScope.dish.description}"/></h4>
<h4><c:out value="${sessionScope.dish.type}"/></h4>
<h4><c:out value="${sessionScope.dish.weight}"/> gr</h4>
<h4><c:out value="${sessionScope.dish.price}"/> $</h4>
<br/>
<h2>
    <a href="controller?command=go_to_default">${main_page}</a>
</h2>
</body>
</html>
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

<c:import url="/WEB-INF/jsp/header.jsp"/>

<header class="custom-header main-page-header">
</header>
<c:choose>
    <c:when test="${errorLoginMessage != null}">
        <div class="dishes-context-container">
            <div class="container main-div">
                <div class="alert alert-danger" role="alert">
                        ${errorLoginMessage}
                </div>
            </div>
        </div>
    </c:when>
</c:choose>
</body>
</html>
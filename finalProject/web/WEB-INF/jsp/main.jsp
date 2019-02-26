<%@ page language="java" import="by.htp.ellib.entity.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
<fmt:message bundle="${loc}" key="locale.default.main_page" var="main_page"/>

<h1>HELLO,</h1>
 <h2>
     <c:out value="${requestScope.user.mail}" />
     <c:out value="${requestScope.user.password}" />
     <c:out value="${requestScope.user.login}" />
     <c:out value="${requestScope.user.name}" />
     <c:out value="${requestScope.user.surname}" />
 </h2>

 <h2>
     <a href="controller?command=go_to_default">${main_page}</a>
 </h2>
</body>
</html>
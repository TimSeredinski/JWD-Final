<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Insert title here</title>
</head>
<body>

<h1>Dish</h1>
<h4><c:out value="${requestScope.dish.name}"/></h4>
<h4><c:out value="${requestScope.dish.description}"/></h4>
<h4><c:out value="${requestScope.dish.type}"/></h4>
<h4><c:out value="${requestScope.dish.weight}"/> gr</h4>
<h4><c:out value="${requestScope.dish.price}"/> $</h4>
<br/>
<h2>
    <a href="controller?command=go_to_default">Main page(добавить лок-цию на страницу)</a>
</h2>
</body>
</html>
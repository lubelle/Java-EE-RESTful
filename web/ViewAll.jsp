<%-- 
    Document   : ViewAll
    Created on : Nov 22, 2016, 10:39:10 AM
    
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Products</title>
    </head>
    <body>
        <h2>List of Products</h2>
        <c:forEach items="${list}" var="item">
            ${item}<br>
        </c:forEach>
        <hr>
        ${result}
    </body>
</html>

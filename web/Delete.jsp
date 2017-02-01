<%-- 
    Document   : Delete
    Created on : Nov 22, 2016, 10:45:38 AM
    
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Product</title>
    </head>
    <body>
        <form action="WebServiceServlet" method="post">
            <h2>To delete product: </h2>                
            Enter Product ID:            
            <input type="text" name="pid" width="25" style="text-align: right"><br>               
            <input type="Submit" name="action" value="Delete">
        </form>
        <hr>
        ${result}
    </body>
</html>
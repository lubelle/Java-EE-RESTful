<%-- 
    Document   : Create
    Created on : Nov 22, 2016, 10:45:24 AM
   
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Product</title>
    </head>
    <body>
        <form action="WebServiceServlet" method="post">
            <h2>To add product: </h2>
            Enter Product ID:            
            <input type="text" name="pid" width="25" style="text-align: right"><br>
            Enter Product Name: 
            <input type="text" name="pname" width="25" style="text-align: right"><br>
            Enter Product Quantity: 
            <input type="text" name="pqty" width="25" style="text-align: right"><br>
            Enter Product Price: 
            <input type="text" name="pprice" width="25" style="text-align: right"><br>
                
            <input type="Submit" name="action" value="Add">
        </form>
        <hr>
        ${result}
    </body>
</html>

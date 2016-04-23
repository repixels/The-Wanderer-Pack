<%-- 
    Document   : ShowAllCategories
    Created on : Apr 13, 2016, 4:05:58 AM
    Author     : Mohammed
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>JSP Page</title>
    </head>
    <body>
        
        <table>
            <tr>
                <th>id </th>
                <th>name </th>
                <th>description </th>
                <th>option </th>
            </tr>
        <c:forEach  var="cat"   items="${requestScope.allCategories}" >
            
            <tr>
                <td>
        
            <c:out value="${cat.categoryId}"/>
            </td>
            <td>
            <c:out value="${cat.categorName}"/>
            </td>
            <td>
            <c:out value="${cat.categoryDescription}"/>
            </td>
            <td>
                <a href="Category?categoryMode=edit&id=${cat.categoryId}"> edit</a>
                <a href="Category?categoryMode=delete&id=${cat.categoryId}"> delete</a>
                
                
            </td>
            
            </tr>
            
        </c:forEach> 
        </table>
          
    </body>
</html>

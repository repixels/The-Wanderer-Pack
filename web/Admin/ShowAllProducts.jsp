<%-- 
    Document   : ShowAllProducts
    Created on : Apr 19, 2016, 11:15:25 PM
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
                <th>price </th>
                <th>category </th>
                <th>option </th>
            </tr>
            
            
                <c:forEach  var="product"   items="${requestScope.allProducts}" >
            
            <tr>
                <td>
        
            <c:out value="${product.productId}"/>
            </td>
            <td>
            <c:out value="${product.productName}"/>
            </td>
            <td>
            <c:out value="${product.productDescription}"/>
            </td>
            <td>
            <c:out value="${product.productPrice}"/>
            </td>
            <td>
                <c:if test="${product.categoryList.size()>0}">
                    <c:out value="${product.categoryList.get(0).categorName}"/>
                </c:if>
            </td>
            <td>
                <a href="Product?productMode=edit&id=${product.productId}"> edit</a>
                <a href="Product?productMode=delete&id=${product.productId}"> delete</a>
                
                
            </td>
            
            </tr>
            
        </c:forEach> 
        </table>
          
    </body>
</html>

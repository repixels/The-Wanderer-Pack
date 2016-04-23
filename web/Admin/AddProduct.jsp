<%-- 
    Document   : AddProduct
    Created on : Apr 19, 2016, 12:32:27 AM
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
         
    <c:set var="isEdit" value="${requestScope.productMode}"/>

    
    <c:if test="${requestScope.product!=null}">not null
        
        <c:set var="isEdit" value="update"/>
        <jsp:useBean id="product" class="pojo.Product" scope="request"/>
        <jsp:setProperty name="product" property="productName" value="${requestScope.product.productName}"/>
        <jsp:setProperty name="product" property="productDescription" value="${requestScope.product.productDescription}"/>
        <jsp:setProperty name="product" property="productImage" value="${requestScope.product.productImage}"/>
        <jsp:setProperty name="product" property="productPrice" value="${requestScope.product.productPrice}"/>
        <jsp:setProperty name="product" property="productId" value="${requestScope.product.productId}"/>
    </c:if>
    <c:if test="${requestScope.product==null}">
        <c:set var="isEdit" value="insert"/>
    </c:if>
    
  ${pageScope.isEdit}  
    
        <form action="Product"  method="get">
            
            <input type="text" name="productId" hidden="true" value="${product.productId}" />
            <br/>
            <input name="productMode" hidden="true"value="${pageScope.isEdit}"/>
<!--            <input name="isEdit" hidden="true"value="${pageScope.isEdit}"/>-->
            <label >product Name : </label>
            <input type="text" name="productName" value="${product.productName}"/>
            <br/> 
            
            <label >product Description </label>
            <input type="text" name="productDescription" value="${product.productDescription}"/>
            <br/>
            
            <label >product Price </label>
            <input type="number" name="productPrice" value="${product.productPrice}"/>
            <br/>
            <label >Image </label>
            <input type="file" accept=".jpg"  name="productImage"  > 
            
            <br/>
            <label >category </label>
            
            <select name="categories">
                   
                <c:forEach  items="${requestScope.allCategories}" var="category" varStatus="i">
                <option   value=${category.categorName}>${category.categorName}</option>    
                </c:forEach>
            </select>
            
            <br/>
            
            <br/>
            
            <input type="submit" value="${pageScope.isEdit}"/>
               <input type="reset" value="Cancel"/>
        </form>
    </body>
</html>

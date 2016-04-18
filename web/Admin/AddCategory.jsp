<%-- 
    Document   : AddCategory
    Created on : Apr 12, 2016, 10:57:30 PM
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
        <h1>Hello World!</h1>
        
    <c:set var="isEdit" value="${requestScope.categoryMode}"/>

    
    <c:if test="${requestScope.category!=null}">not null
        
        <c:set var="isEdit" value="update"/>
        <jsp:useBean id="category" class="pojo.Category" scope="request"/>
        <jsp:setProperty name="category" property="categorName" value="${requestScope.category.categorName}"/>
        <jsp:setProperty name="category" property="categoryDescription" value="${requestScope.category.categoryDescription}"/>
        <jsp:setProperty name="category" property="categoryImage" value="${requestScope.category.categoryImage}"/>
        <jsp:setProperty name="category" property="categoryId" value="${requestScope.category.categoryId}"/>
    </c:if>
    <c:if test="${requestScope.category==null}">
        <c:set var="isEdit" value="insert"/>
    </c:if>
    
  ${pageScope.isEdit}  
    
        <form action="Category" enctype="MULTIPART/FORM-DATA" method="Post">
            
            <input type="text" name="categoryId" hidden="true" value="${category.categoryId}" />
            <br/>
            <input name="categoryMode" hidden="true"value="${pageScope.isEdit}"/>
<!--            <input name="isEdit" hidden="true"value="${pageScope.isEdit}"/>-->
            <label >category Name : </label>
            <input type="text" name="categorName"value="${category.categorName}"/>
            <br/> 
            
            <label >category Description </label>
            <input type="text" name="categoryDescription" value="${category.categoryDescription}"/>
            <br/>
            
            
            <label >Image </label>
            <input type="file" accept=".jpg"  name="categoryImage"  > 
            
            <br/>
            <label >product </label>
            <select name="products">
                <option value="0">choose product</option>
                <option value="1">product1</option>
                <option value="2">product2</option>
                <option value="3">product3</option>
            </select>
            
            <br/>
            
            <label >sub categories </label>
            <input type="text" name="categoryList"/>
            <br/>
            
            <input type="submit" value="${pageScope.isEdit}"/>
               <input type="reset" value="Cancel"/>
        </form>
        
    </body>
</html>

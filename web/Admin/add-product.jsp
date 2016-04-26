<%-- 
    Document   : add-product
    Created on : Apr 23, 2016, 6:57:35 PM
    Author     : Ehab
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="header.jsp" %>
    <body>
        
        <c:set var="isEdit" value="${requestScope.productMode}"/>
        <c:if test="${requestScope.product!=null}">
        
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
        
        
        <!-- Start Page Container -->
        <div class="page-container">
            <%@include file="right-menu.jsp" %>

            <!-- PAGE CONTENT -->
            <div class="page-content">

                <!-- START X-NAVIGATION VERTICAL -->
                <ul class="x-navigation x-navigation-horizontal x-navigation-panel">
                    <!-- TOGGLE NAVIGATION -->
                    <li class="xn-icon-button">
                        <a href="#" class="x-navigation-minimize"><span class="fa fa-dedent"></span></a>
                    </li>
                    <!-- END TOGGLE NAVIGATION -->
                    <!-- SEARCH -->
                    <li class="xn-search">
                        <form role="form">
                            <input type="text" name="search" placeholder="Search..."/>
                        </form>
                    </li>   
                    <!-- END SEARCH -->
                    <!-- SIGN OUT -->
                    <li class="xn-icon-button pull-right">
                        <a href="#" class="mb-control" data-box="#mb-signout"><span class="fa fa-sign-out"></span></a>                        
                    </li> 
                    <!-- END SIGN OUT -->
                </ul>
                <!-- END X-NAVIGATION VERTICAL -->                    

                <!-- START BREADCRUMB -->
                <ul class="breadcrumb push-down-0">
                    <li><a href="#">Home</a></li>
                    <li class="active">Products</li>
                </ul>
                <!-- END BREADCRUMB -->                

                <!-- PAGE CONTENT WRAPPER -->
                <div class="page-content-wrap">
                    <div class="row">
                        <div class="col-md-12">
                                
                            <form class="form-horizontal" action="Product?productMode=insert" method="GET">
                                
                                <input type="text" name="productId" hidden value="${product.productId}" />
                                <input name="productMode" hidden value="${pageScope.isEdit}"/>
                                
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h3 class="panel-title"><strong>${pageScope.isEdit}</strong> Product</h3>
                                        <ul class="panel-controls">
                                            <li><a href="products.html"><span class="fa fa-times"></span></a></li>
                                        </ul>
                                    </div>

                                    <div class="panel-body form-group-separated">

                                        <div class="form-group">
                                            <label class="col-md-3 col-xs-12 control-label">Product Name</label>
                                            <div class="col-md-6 col-xs-12">                                            
                                                <div class="input-group">
                                                    <span class="input-group-addon"><span class="fa fa-pencil"></span></span>
                                                    <input type="text" class="form-control" name="productName" value="${product.productName}"/>
                                                </div>                                            
                                                <span class="help-block">This is sample of text field</span>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-md-3 col-xs-12 control-label">Product Price</label>
                                            <div class="col-md-6 col-xs-12">                                            
                                                <div class="input-group">
                                                    <span class="input-group-addon"><span class="fa fa-pencil"></span></span>
                                                    <input type="number" class="form-control" name="productPrice" value="${product.productPrice}"/>
                                                </div>                                            
                                                <span class="help-block">This is sample of text field</span>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-md-3 col-xs-12 control-label">Product Quantity</label>
                                            <div class="col-md-6 col-xs-12">                                            
                                                <div class="input-group">
                                                    <span class="input-group-addon"><span class="fa fa-pencil"></span></span>
                                                    <input type="text" class="form-control" name="productQuantity"/>
                                                </div>                                            
                                                <span class="help-block">This is sample of text field</span>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-md-3 col-xs-12 control-label">Choose a Category</label>
                                            <div class="col-md-6 col-xs-12">                                                                                            
                                                <select class="form-control select" name="categories">
                                                    <c:forEach  items="${requestScope.allCategories}" var="category" varStatus="i">
                                                        <option value="${category.categoryId}">${category.categorName}</option>    
                                                    </c:forEach>
                                                </select>
                                                <span class="help-block">Select box example</span>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-md-3 col-xs-12 control-label">Upload Product Image</label>
                                            <div class="col-md-6 col-xs-12">                                                                                                                                        
                                                <input type="file" class="fileinput btn-primary" name="filename" id="filename" title="Browse file" accept=".jpg"/>
                                                <span class="help-block">Input type file</span>
                                            </div>
                                        </div>


                                    </div>
                                    <div class="panel-footer">
                                        <button class="btn btn-default">Clear Form</button>                                    
                                        <button class="btn btn-primary pull-right" type="submit">Submit</button>
                                    </div>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
                <!-- END PAGE CONTENT WRAPPER -->
            </div>            
            <!-- END PAGE CONTENT -->
        </div>
        <!-- End Page Container -->

        <%@include file="scripts.jsp" %>
        <!-- THIS PAGE PLUGINS -->
        <script type='text/javascript' src='js/plugins/icheck/icheck.min.js'></script>
        <script type="text/javascript" src="js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js"></script>
        
        <script type="text/javascript" src="js/plugins/bootstrap/bootstrap-datepicker.js"></script>                
        <script type="text/javascript" src="js/plugins/bootstrap/bootstrap-file-input.js"></script>
        <script type="text/javascript" src="js/plugins/bootstrap/bootstrap-select.js"></script>
        <script type="text/javascript" src="js/plugins/tagsinput/jquery.tagsinput.min.js"></script>
        <!-- END PAGE PLUGINS -->
        
    </body>
</html>

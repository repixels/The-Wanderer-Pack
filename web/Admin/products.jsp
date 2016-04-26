<%-- 
    Document   : products
    Created on : Apr 23, 2016, 8:42:52 PM
    Author     : Ehab
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="header.jsp" %>
    
    <body>
        
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
                    <%@include file="search.jsp" %>
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
                    <!-- START RESPONSIVE TABLES -->
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel panel-default">

                                <div class="panel-heading">
                                    <h3 class="panel-title">Products</h3>
                                    <ul class="panel-controls">
                                        <li><a href="Product?productMode=add"><span class="fa fa-plus"></span></a></li>
                                    </ul>
                                </div>

                                <div class="panel-body panel-body-table">

                                    <div class="table-responsive">
                                        <table class="table table-bordered table-striped table-actions">
                                            <thead>
                                                <tr>
                                                    <th width="50">id</th>
                                                    <th>name</th>
                                                    <th width="100">category</th>
                                                    <th width="100">description</th>
                                                    <th width="100">price</th>
                                                    <th width="100">actions</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach  var="product"   items="${requestScope.allProducts}" >
                                                <tr id="trow_${product.productId}">
                                                    <td class="text-center"><c:out value="${product.productId}"/></td>
                                                    <td><strong><c:out value="${product.productName}"/></strong></td>
                                                    <td>
                                                        <span class="label label-success">
                                                            <c:if test="${product.categoryList.size()>0}">
                                                                <c:out value="${product.categoryList.get(0).categorName}"/>
                                                            </c:if>
                                                        </span>
                                                    </td>
                                                    <td><c:out value="${product.productDescription}"/></td>
                                                    <td><c:out value="${product.productPrice}"/> EGP</td>
                                                    <td>
                                                        <button class="btn btn-default btn-rounded btn-sm"><a href="Product?productMode=edit&id=${product.productId}"><span class="fa fa-pencil"></span></a></button>
                                                        <button class="btn btn-danger btn-rounded btn-sm" onClick="delete_row('trow_${product.productId}','product',${product.productId}+'');"><span class="fa fa-times"></span></button>
                                                    </td>
                                                </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>                                

                                </div>
                            </div>                                                

                        </div>
                    </div>
                    <!-- END RESPONSIVE TABLES -->
                </div>
                <!-- END PAGE CONTENT WRAPPER -->
            </div>            
            <!-- END PAGE CONTENT -->
          
        </div>
            
               <!-- MESSAGE BOX-->
        <div class="message-box animated fadeIn" data-sound="alert" id="mb-remove-row">
            <div class="mb-container">
                <div class="mb-middle">
                    <div class="mb-title"><span class="fa fa-times"></span> Remove <strong>Data</strong> ?</div>
                    <div class="mb-content">
                        <p>Are you sure you want to remove this row?</p>                    
                        <p>Press Yes if you sure.</p>
                    </div>
                    <div class="mb-footer">
                        <div class="pull-right">
                            <button class="btn btn-success btn-lg mb-control-yes">Yes</button>
                            <button class="btn btn-default btn-lg mb-control-close">No</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- END MESSAGE BOX--> 
        
        <%@include file="scripts.jsp" %>
        <!-- THIS PAGE PLUGINS -->
        <script type='text/javascript' src='js/plugins/icheck/icheck.min.js'></script>
        <script type="text/javascript" src="js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js"></script>
        
        <script type="text/javascript" src="js/demo_tables.js"></script>
        <!-- END PAGE PLUGINS --> 
        
    </body>
</html>

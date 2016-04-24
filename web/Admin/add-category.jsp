<%-- 
    Document   : add-category
    Created on : Apr 24, 2016, 10:28:46 AM
    Author     : Ehab
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

    <%@include file="header.jsp" %>

    <body>
        
        <c:set var="isEdit" value="${requestScope.categoryMode}"/>


        <c:if test="${requestScope.category!=null}">

            <c:set var="isEdit" value="update"/>
            <jsp:useBean id="category" class="pojo.Category" scope="request"/>
            <jsp:setProperty name="category" property="categorName" value="${requestScope.category.categorName}"/>
            <jsp:setProperty name="category" property="categoryDescription" value="${requestScope.category.categoryDescription}"/>
            <jsp:setProperty name="category" property="categoryId" value="${requestScope.category.categoryId}"/>
        </c:if>
        <c:if test="${requestScope.category==null}">
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
                    <li><a href="Category?showAll">Categories</a></li>
                    <li class="active">Insert Category</li>
                </ul>
                <!-- END BREADCRUMB -->                

                <!-- PAGE CONTENT WRAPPER -->
                <div class="page-content-wrap">
                    <div class="row">
                        <div class="col-md-12">
                                
                            <form class="form-horizontal" action="Category" method="POST">
                                
                                <input type="text" name="categoryId" hidden value="${category.categoryId}" />
                                <input name="categoryMode" hidden value="${pageScope.isEdit}"/>
                                
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h3 class="panel-title"><strong>${pageScope.isEdit}</strong> Category</h3>
                                        <ul class="panel-controls">
                                            <li><a href="Category?categoryMode=showAll"><span class="fa fa-times"></span></a></li>
                                        </ul>
                                    </div>

                                    <div class="panel-body form-group-separated">

                                        <div class="form-group">
                                            <label class="col-md-3 col-xs-12 control-label">Category Name</label>
                                            <div class="col-md-6 col-xs-12">                                            
                                                <div class="input-group">
                                                    <span class="input-group-addon"><span class="fa fa-pencil"></span></span>
                                                    <input type="text" class="form-control" name="categorName" value="${category.categorName}"/>
                                                </div>                                            
                                                <span class="help-block">This is sample of text field</span>
                                            </div>
                                        </div>
                                                
                                        <div class="form-group">
                                            <label class="col-md-3 col-xs-12 control-label">Category Description</label>
                                            <div class="col-md-6 col-xs-12">                                            
                                                <div class="input-group">
                                                    <span class="input-group-addon"><span class="fa fa-pencil"></span></span>
                                                    <input type="text" class="form-control" name="categoryDescription" value="${category.categoryDescription}"/>
                                                </div>                                            
                                                <span class="help-block">This is sample of text field</span>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-md-3 col-xs-12 control-label">Upload Category Image</label>
                                            <div class="col-md-6 col-xs-12">                                                                                                                                        
                                                <input type="file" class="fileinput btn-primary" name="filename" id="filename" title="Browse file" accept=".jpg"/>
                                                <span class="help-block">Input type file</span>
                                            </div>
                                        </div>


                                    </div>
                                    <div class="panel-footer">
                                        <button class="btn btn-default">Clear Form</button>                                    
                                        <button class="btn btn-primary pull-right" type="submit" value="${pageScope.isEdit}">Submit</button>
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

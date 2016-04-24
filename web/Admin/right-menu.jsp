<%-- 
    Document   : right-menu
    Created on : Apr 23, 2016, 6:55:20 PM
    Author     : Ehab
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- START PAGE SIDEBAR -->
<div class="page-sidebar">
    <!-- START X-NAVIGATION -->
    <ul class="x-navigation">
        <li class="xn-logo">
            <a href="index.html">Joli Admin</a>
            <a href="#" class="x-navigation-control"></a>
        </li>
        <li class="xn-profile">
            <a href="#" class="profile-mini">
                <img src="assets/images/users/avatar.jpg" alt="John Doe"/>
            </a>
            <div class="profile">
                <div class="profile-image">
                    <img src="assets/images/users/avatar.jpg" alt="John Doe"/>
                </div>
                <div class="profile-data">
                    <div class="profile-data-name">John Doe</div>
                    <div class="profile-data-title">Web Developer/Designer</div>
                </div>
            </div>                                                                        
        </li>
        <li class="xn-title">Navigation</li>
        <li>
            <a href="index.jsp"><span class="fa fa-desktop"></span> <span class="xn-text">Dashboard</span></a>
        </li>
        <li class="active">
            <a href="Product?productMode=showAll"><span class="fa fa-desktop"></span> <span class="xn-text">Products</span></a>
        </li>
        <li>
            <a href="Category?categoryMode=showAll"><span class="fa fa-desktop"></span> <span class="xn-text">Category</span></a>
        </li>
    </ul>
    <!-- END X-NAVIGATION -->
</div>
<!-- END PAGE SIDEBAR -->

<%-- 
    Document   : header
    Created on : Apr 11, 2016, 6:39:39 AM
    Author     : OMIMA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

    <head>
        <title>Shopin A Ecommerce Category Flat Bootstrap Responsive Website Template | Home :: w3layouts</title>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
        <!-- Custom Theme files -->
        <!--theme-style-->
        <link href="css/style.css" rel="stylesheet" type="text/css" media="all" />	
        <!--//theme-style-->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="keywords" content="Shopin Responsive web template, Bootstrap Web Templates, Flat Web Templates, AndroId Compatible web template, 
              Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
        <!--theme-style-->
        <link href="css/style4.css" rel="stylesheet" type="text/css" media="all" />	
        <!--//theme-style-->
        <script src="js/jquery.min.js"></script>
        <!--- start-rate---->
        <script src="js/jstarbox.js"></script>
        <link rel="stylesheet" href="css/jstarbox.css" type="text/css" media="screen" charset="utf-8" />



        <script type="text/javascript">

            jQuery(function () {
                jQuery('.starbox').each(function () {
                    var starbox = jQuery(this);
                    starbox.starbox({
                        average: starbox.attr('data-start-value'),
                        changeable: starbox.hasClass('unchangeable') ? false : starbox.hasClass('clickonce') ? 'once' : true,
                        ghosting: starbox.hasClass('ghosting'),
                        autoUpdateAverage: starbox.hasClass('autoupdate'),
                        buttons: starbox.hasClass('smooth') ? false : starbox.attr('data-button-count') || 5,
                        stars: starbox.attr('data-star-count') || 5
                    }).bind('starbox-value-changed', function (event, value) {
                        if (starbox.hasClass('random')) {
                            var val = Math.random();
                            starbox.next().text(' ' + val);
                            return val;
                        }
                    })
                });

            });
        </script>
        <!---//End-rate---->

    </head>

    <body>
        
       
    <div class="container">
        <div class="head">
            <div class=" logo">
                <a href="index.jsp"><img src="images/logo.png" alt=""></a>	
            </div>
        </div>
    </div>
    <div class="header-top">
        <div class="container">


            <c:if test="${empty sessionScope.userName}">
                
                <div class="col-sm-5 col-md-offset-2  header-login">
                    <ul >
                        <li><a href="login.jsp">Login</a></li>
                        <li><a href="register.jsp">Register</a></li>
                        <li><a href="checkout.jsp">Checkout</a></li>
                    </ul>
                </div>
            </c:if>

            <c:if test="${not empty sessionScope.userName}">

                <div class="col-sm-5 col-md-offset-2  header-login">
                    <ul >
                        <li> <a href="">Welcome: ${sessionScope.userName}</a></li>
                        <li><a href=""></a></li>
                        <li><a href=""></a></li>
                        <li><a href=""></a></li>
                        <li><a href="LoginServlet?emailLogin=no&pwdLogin=no&flagLogin=0">LoginOut</a></li>
                    </ul>
                </div>
            </c:if>




            <div class="col-sm-5 header-social">		
                <ul >
                    <li><a href="#"><i></i></a></li>
                    <li><a href="#"><i class="ic1"></i></a></li>
                    <li><a href="#"><i class="ic2"></i></a></li>
                    <li><a href="#"><i class="ic3"></i></a></li>
                    <li><a href="#"><i class="ic4"></i></a></li>
                </ul>

            </div>
            <div class="clearfix"> </div>
        </div>
    </div>

    <div class="container">

        <div class="head-top">

            <div class="col-sm-8 col-md-offset-2 h_menu4">
                <nav class="navbar nav_bottom" role="navigation">

                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header nav_2">
                        <button type="button" class="navbar-toggle collapsed navbar-toggle1" data-toggle="collapse" data-target="#bs-megadropdown-tabs">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>

                    </div> 
                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="bs-megadropdown-tabs">
                        <ul class="nav navbar-nav nav_1">
                            <li><a class="color" href="index.jsp">Home</a></li>
                            <c:forEach  var="cat"   items="${applicationScope.categories}" >
                                <c:if test="${cat.categoryParentId == null}">
                                    <li class="dropdown mega-dropdown active">
                                        <a class="color1" href="#" class="dropdown-toggle" data-toggle="dropdown">${cat.categorName}
                                            <c:if test="${cat.categoryList.size() != 0}">
                                                <span class="caret"></span>
                                            </c:if>
                                        </a>
                                        <c:if test="${cat.categoryList.size() != 0}">
                                            <div class="dropdown-menu ">
                                                <div class="menu-top">
                                                    <div class="col1">
                                                        <div class="h_nav">
                                                            <h4>${cat.categorName}</h4>
                                                            <ul>
                                                                <c:forEach var="subCategory" items="${cat.categoryList}">
                                                                    <li>
                                                                        <a href="/The_Wanderer_Pack/UserProduct">${subCategory.categorName}</a>
                                                                    </li>
                                                                </c:forEach>
                                                            </ul>	
                                                        </div>							
                                                    </div>
                                                    <div class="clearfix"></div>
                                                </div>                  
                                            </div>
                                        </c:if>
                                    </li>
                                </c:if>
                            </c:forEach>
                            <li ><a class="color6" href="contact.jsp">Contact</a></li>
                        </ul>
                    </div><!-- /.navbar-collapse -->

                </nav>
            </div>
            <div class="col-sm-2 search-right">
                <ul class="heart">

                    <li><a class="play-icon popup-with-zoom-anim" href="#small-dialog"><i class="glyphicon glyphicon-search"> </i></a></li>
                </ul>
                <div class="cart box_1">
                    <a href="checkout.jsp">
                        <h3> <div class="total">
                                <span class="simpleCart_total"></span></div>
                            <img src="images/cart.png" alt=""/></h3>
                    </a>
                    <p><a href="javascript:;" class="simpleCart_empty">Empty Cart</a></p>

                </div>
                <div class="clearfix"> </div>

                <!----->

                <!---pop-up-box---->					  
                <link href="css/popuo-box.css" rel="stylesheet" type="text/css" media="all"/>
                <script src="js/jquery.magnific-popup.js" type="text/javascript"></script>
                <!---//pop-up-box---->
                <div id="small-dialog" class="mfp-hide">
                    <div class="search-top">
                        <div class="login-search">
                            <input type="submit" value="">
                            <input type="text" value="Search.." onfocus="this.value = '';" onblur="if (this.value == '') {
                                        this.value = 'Search..';
                                    }">		
                        </div>
                        <p>Shopin</p>
                    </div>				
                </div>
                <script>
                    $(document).ready(function () {
                        $('.popup-with-zoom-anim').magnificPopup({
                            type: 'inline',
                            fixedContentPos: false,
                            fixedBgPos: true,
                            overflowY: 'auto',
                            closeBtnInside: true,
                            preloader: false,
                            midClick: true,
                            removalDelay: 300,
                            mainClass: 'my-mfp-zoom-in'
                        });

                    });
                </script>		

            </div>
            <div class="clearfix"></div>
        </div>	
    </div>




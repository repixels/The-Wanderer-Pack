<%-- 
    Document   : index
    Created on : Apr 8, 2016, 1:49:22 PM
    Author     : OMIMA
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

    <jsp:include page="header.jsp"/>
    <!--header-->

    <!--banner-->

    <div class="banner">
        <div class="container">
        </div>
    </div>
    <!--content-->
    <div class="content">
        <div class="container">
            <div class="content-top">
                <div class="col-md-6 col-md">
                    <div class="col-1">
                        <a href="single.jsp" class="b-link-stroke b-animate-go  thickbox">
                            <img src="" class="img-responsive" alt=""/><div class="b-wrapper1 long-img"><p class="b-animate b-from-right    b-delay03 ">Lorem ipsum</p>
                                <label class="b-animate b-from-right    b-delay03 ">

                                </label><h3 class="b-animate b-from-left    b-delay03 ">Trendy</h3></div></a>

                        <!---<a href="single.html"><img src="images/pi.jpg" class="img-responsive" alt=""></a>-->
                    </div>
                    <div class="col-2">
                        <span>Hot Deal</span>
                        <h2><a href="single.jsp">Luxurious &amp; Trendy</a></h2>
                        <p>description  </p>
                        <a href="single.jsp" class="buy-now">Buy Now</a>
                    </div>
                </div>
                <div class="col-md-6 col-md1">
                    <div class="col-3">
                        <a href="single.jsp"><img src="images/pi3.jpg" class="img-responsive" alt="">
                            <div class="col-pic">
                                <p>Lorem Ipsum</p>
                                <label></label>
                                <h5>For Women</h5>
                            </div></a>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
            <!--products---------------------------------------------------------------------------------------------------------------------------------------------------------------->
            <div class="content-mid">
                <h3>Trending Items</h3>
                <label class="line"></label>



                <c:forEach var="item" items="${requestScope.productsREQ}">
                      


                    <div class="mid-popular">
                        <div class="col-md-3 item-grid simpleCart_shelfItem">
                            <div class=" mid-pop">
                                <div class="pro-img">
                                    <img src="images/pc.jpg" class="img-responsive" alt="">
                                    <div class="zoom-icon ">
                                        <a class="picture" href="images/pc.jpg" rel="title" class="b-link-stripe b-animate-go  thickbox"><i class="glyphicon glyphicon-search icon "></i></a>
                                        <a href="single.jsp"><i class="glyphicon glyphicon-menu-right icon"></i></a>
                                    </div>
                                </div>
                                <div class="mid-1">
                                    <div class="women">
                                        <div class="women-top">                          

                                            <span><c:out value="${item.getProductName()}"/></span>
                                            <span> Category</span> 
                                            <h6><a href="single.jsp">description</a></h6>
                                        </div>
                                        <div class="img item_add">
                                            <a href="#"><img src="images/ca.png" alt=""></a>
                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="mid-2">
                                        <p ><label>old price</label><em class="item_price">price</em></p>
                                        <div class="block">
                                            <div class="starbox small ghosting"> </div>
                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                    </div>


                </c:forEach>


            </div>
        </div>
    </div>
    <div class="clearfix"></div>
</div>
</div>
<!--//products-->
<!--brand-->
<div class="brand">
    <div class="col-md-3 brand-grid">
        <img src="images/ic.png" class="img-responsive" alt="">
    </div>
    <div class="col-md-3 brand-grid">
        <img src="images/ic1.png" class="img-responsive" alt="">
    </div>
    <div class="col-md-3 brand-grid">
        <img src="images/ic2.png" class="img-responsive" alt="">
    </div>
    <div class="col-md-3 brand-grid">
        <img src="images/ic3.png" class="img-responsive" alt="">
    </div>
    <div class="clearfix"></div>
</div>
<!--//brand-->
</div>
</div>
<!--//content-->
<!--//footer-->
<jsp:include page="footer.jsp"/>
<!--//footer-->
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/simpleCart.min.js"></script>
<!-- slide -->
<script src="js/bootstrap.min.js"></script>
<!--light-box-files -->
<script src="js/jquery.chocolat.js"></script>
<link rel="stylesheet" href="css/chocolat.css" type="text/css" media="screen" charset="utf-8">
<!--light-box-files -->
<script type="text/javascript" charset="utf-8">
    $(function () {
        $('a.picture').Chocolat();
    });
</script>


</body>
</html>
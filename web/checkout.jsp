<%-- 
    Document   : checkout
    Created on : Apr 8, 2016, 5:02:20 PM
    Author     : OMIMA
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>
    <!--header-->

    <!--banner-->
    <div class="banner-top">
        <div class="container">
            <h1>Checkout</h1>
            <em></em>
            <h2><a href="index.jsp">Home</a><label>/</label>Checkout</a></h2>
        </div>
    </div>
    <!--login-->
    <script>$(document).ready(function (c) {
            $('.close1').on('click', function (c) {
                $('.cart-header').fadeOut('slow', function (c) {
                    $('.cart-header').remove();
                });
            });
        });
    </script>
    <script>$(document).ready(function (c) {
            $('.close2').on('click', function (c) {
                $('.cart-header1').fadeOut('slow', function (c) {
                    $('.cart-header1').remove();
                });
            });
        });
    </script>
    <script>$(document).ready(function (c) {
            $('.close3').on('click', function (c) {
                $('.cart-header2').fadeOut('slow', function (c) {
                    $('.cart-header2').remove();
                });
            });
        });
    </script>
    <div class="container">
        <div class="check-out">
            <div class="bs-example4" data-example-id="simple-responsive-table">
                <div class="table-responsive">
                    <table class="table-heading simpleCart_shelfItem">
                        <tr>
                            <th class="table-grid">Item</th>

                            <th>Prices</th>
                            <th >Delivery </th>
                            <th>Subtotal</th>
                        </tr>
                        <tr class="cart-header">

                            <td class="ring-in"><a href="single.jsp" class="at-in"><img src="images/ch.jpg" class="img-responsive" alt=""></a>
                                <div class="sed">
                                    <h5><a href="single.jsp">Sed ut perspiciatis unde</a></h5>
                                    <p>(At vero eos et accusamus et iusto odio dignissimos ducimus ) </p>

                                </div>
                                <div class="clearfix"> </div>
                                <div class="close1"> </div></td>
                            <td>$100.00</td>
                            <td>FREE SHIPPING</td>
                            <td class="item_price">$100.00</td>
                            <td class="add-check"><a class="item_add hvr-skew-backward" href="#">Add To Cart</a></td>
                        </tr>
                        <tr class="cart-header1">
                            <td class="ring-in"><a href="single.jsp" class="at-in"><img src="images/ch2.jpg" class="img-responsive" alt=""></a>
                                <div class="sed">
                                    <h5><a href="single.jsp">Sed ut perspiciatis unde</a></h5>
                                    <p>(At vero eos et accusamus et iusto odio dignissimos ducimus ) </p>
                                </div>
                                <div class="clearfix"> </div>
                                <div class="close2"> </div></td></td>
                            <td>$100.00</td>
                            <td>FREE SHIPPING</td>
                            <td class="item_price">$100.00</td>
                            <td class="add-check"><a class="item_add hvr-skew-backward" href="#">Add To Cart</a></td>
                        </tr>
                        <tr class="cart-header2">
                            <td class="ring-in"><a href="single.jsp" class="at-in"><img src="images/ch1.jpg" class="img-responsive" alt=""></a>
                                <div class="sed">
                                    <h5><a href="single.jsp">Sed ut perspiciatis unde</a></h5>
                                    <p>(At vero eos et accusamus et iusto odio dignissimos ducimus ) </p>
                                </div>
                                <div class="clearfix"> </div>
                                <div class="close3"> </div></td></td>
                            <td>$100.00</td>
                            <td>FREE SHIPPING</td>
                            <td class="item_price">$100.00</td>
                            <td class="add-check"><a class="item_add hvr-skew-backward" href="#">Add To Cart</a></td>
                        </tr>

                    </table>
                </div>
            </div>
            <div class="produced">
                <a href="single.jsp" class="hvr-skew-backward">Produced To Buy</a>
            </div>
        </div>
    </div>

    <!--//login-->
    <!--brand-->
    <div class="container">
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
    </div>
    <!--//brand-->
</div>

</div>
<jsp:include page="footer.jsp"/>
<!--//content-->
<script src="js/simpleCart.min.js"></script>
<!-- slide -->
<script src="js/bootstrap.min.js"></script>

</body>
</html>

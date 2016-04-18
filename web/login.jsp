<%-- 
    Document   : login
    Created on : Apr 8, 2016, 1:50:44 PM
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
            <h1>Login</h1>
            <em></em>
            <h2><a href="index.jsp">Home</a><label>/</label>Login</a></h2>
        </div>
    </div>
    <!--login-->
    <div class="container">
        <div class="login">
            <form>
                <div class="col-md-6 login-do">
                    <div class="login-mail">
                        <input type="text" placeholder="Email" required="">
                        <i  class="glyphicon glyphicon-envelope"></i>
                    </div>
                    <div class="login-mail">
                        <input type="password" placeholder="Password" required="">
                        <i class="glyphicon glyphicon-lock"></i>
                    </div>
                    <a class="news-letter " href="#">
                        <label class="checkbox1"><input type="checkbox" name="checkbox" ><i> </i>Forget Password</label>
                    </a>
                    <label class="hvr-skew-backward">
                        <input type="submit" value="login">
                    </label>
                </div>
                <div class="col-md-6 login-right">
                    <h3>Completely Free Account</h3>
                    <p></p>
                    <a href="register.jsp" class=" hvr-skew-backward">Register</a>
                </div>
                <div class="clearfix"> </div>
            </form>
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
<!--//content-->
<!--//footer-->
<jsp:include page="footer.jsp"/>
<!--//footer-->

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->

<script src="js/simpleCart.min.js"></script>
<!-- slide -->
<script src="js/bootstrap.min.js"></script>

</body>
</html>

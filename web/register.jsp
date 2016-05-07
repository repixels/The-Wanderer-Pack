Skip to content
This repository
Search
Pull requests
Issues
Gist
 @omima
 Unwatch 5
  Star 0
  Fork 0 repixels/The-Wanderer-Pack
 Code  Issues 0  Pull requests 0  Wiki  Pulse  Graphs
Branch: login Find file Copy pathThe-Wanderer-Pack/web/register.jsp
3ccfcd3  20 days ago
@omima omima jsp file
1 contributor
RawBlameHistory     92 lines (85 sloc)  3.21 KB
<%-- 
    Document   : register
    Created on : Apr 8, 2016, 1:57:38 PM
    Author     : OMIMA
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <!--header-->
    <jsp:include page="header.jsp"/>
    <!--banner-->
    <div class="banner-top">
        <div class="container">
            <h1>Register</h1>
            <em></em>
            <h2><a href="index.jsp">Home</a><label>/</label>Register</a></h2>
        </div>
    </div>
    <!--login-->
    <div class="container">
        <div class="login">
            <form>
                <div class="col-md-6 login-do">
                    <div class="login-mail">
                        <input type="text" placeholder="Name" required="">
                        <i  class="glyphicon glyphicon-user"></i>
                    </div>
                    <div class="login-mail">
                        <input type="text" placeholder="Phone Number" required="">
                        <i  class="glyphicon glyphicon-phone"></i>
                    </div>
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
                        <input type="submit" value="Submit">
                    </label>

                </div>
                <div class="col-md-6 login-right">
                    <h3>Completely Free Account</h3>

                    <p>Pellentesque neque leo, dictum sit amet accumsan non, dignissim ac mauris. Mauris rhoncus, lectus tincidunt tempus aliquam, odio 
                        <a href="login.jsp" class="hvr-skew-backward">Login</a>

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
<jsp:include page="footer.jsp"/>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/simpleCart.min.js"></script>
<!-- slide -->
<script src="js/bootstrap.min.js"></script>
</body>
</html>
Status API Training Shop Blog About
© 2016 GitHub, Inc. Terms Privacy Security Contact Help
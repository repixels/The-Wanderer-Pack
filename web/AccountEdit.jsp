<%-- 
    Document   : AccountEdit
    Created on : Apr 8, 2016, 1:52:46 PM
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
            <h1>Welcome</h1>
            <em></em>
            <h2><a href="index.jsp">Home</a><label>/</label>MyAccount</a></h2>
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
                        <input type="text" placeholder="User Name" required="">
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
                    <div class="login-mail">
                        <Label>Choose your Interests</label>
                    </div>
                    <div class="login-mail">
                        <br/>   
                        <a class="news-letter " href="#">
                            <label class="checkbox1"><input type="checkbox" name="checkbox" ><i> </i>Option1</label>
                            <label class="checkbox1"><input type="checkbox" name="checkbox" ><i> </i>Option2</label>
                            <label class="checkbox1"><input type="checkbox" name="checkbox" ><i> </i>Option3</label>
                        </a>
                    </div>
                    <label class="hvr-skew-backward">
                        <input type="submit" value="Save">
                    </label>
                </div>
                <div class="col-md-6 login-right">
                    <div class="login-mail">
                        <input type="text" placeholder="City" required="">
                        <i  class="glyphicon glyphicon-home"></i>
                    </div>
                    <div class="login-mail">
                        <input type="text" placeholder="Country" required="">
                    </div>
                    <script>
                        populateCountries("country", "state"); // first parameter is id of country drop-down and second parameter is id of state drop-down
                        populateCountries("country2");
                        populateCountries("country2");
                    </script>
                    <div class="login-mail">
                        <input type="text" placeholder="State" required="">
                        <i  class="glyphicon glyphicon-home"></i>
                    </div>
                    <div class="login-mail">
                        <input type="text" placeholder="Address Details" required="">
                        <i  class="glyphicon glyphicon-home"></i>
                    </div>
                    <div class="login-mail">
                        <input type="text" placeholder="Land Mark" required="">
                        <i  class="glyphicon glyphicon-map-marker"></i>
                    </div>
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
<jsp:include page="footer.jsp"/>
<script src="js/simpleCart.min.js"></script>
<!-- slide -->
<script src="js/bootstrap.min.js"></script>

</body>
</html>
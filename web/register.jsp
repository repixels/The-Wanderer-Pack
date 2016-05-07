
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
                <form action = "RegisterServlet">
                    <div class="col-md-6 login-do">
                        <!--<label>Your name can contain only characters and space</label>-->
                        <div class="login-mail">
                            <input type="text" placeholder="Name" required="" name = "Name" pattern="^[A-Za-z\s]+$">
                            <i  class="glyphicon glyphicon-user"></i>
                         
                        </div>

                           
                        <div class="login-mail">
                            <input type="text" placeholder="User Name" required="" name = "userName">
                            <i  class="glyphicon glyphicon-user"></i>
                        </div>
                        <label id ="emailLabel" style="color: red"></label>
                        <div class="login-mail">
                            <input type ="email" placeholder="Email" required="" name = "email" id= "email" onblur="submitLoginForm()">
                            <i  class="glyphicon glyphicon-envelope"></i>
                        </div>
                        <div class="login-mail">
                            <input type="password" placeholder="Password" required="" name = "password">
                            <i class="glyphicon glyphicon-lock"></i>
                        </div>
                        <div class="login-mail">
                            <Label>Choose your Interests</label>
                        </div>
                        <div class="login-mail">
                            <br/>   
                            <a class="news-letter " href="#">
                                <label class="checkbox1"><input type="checkbox" name="interests" value="Sport"><i> </i>Sport</label>
                                <label class="checkbox1"><input type="checkbox" name="interests" value="Option2"><i> </i>Option2</label>
                                <label class="checkbox1"><input type="checkbox" name="interests" value="Option3"><i> </i>Option3</label>
                            </a>
                        </div>
                        <label class="hvr-skew-backward">
                            <input type="submit" value="Register">
                        </label>
                    </div>
                    <div class="col-md-6 login-right">
                         <!-- <label></label> -->
                        <div class="login-mail">
                            <input type="number" placeholder="Credit Number" required="" name = "creditNumber">
                            <i  class="glyphicon glyphicon-usd"></i>
                        </div>
                        <div class="login-mail">
                            <input type="text" placeholder="City" required="" name = "city">
                            <i  class="glyphicon glyphicon-home"></i>
                        </div>
                        <br>
                        <div class="login-mail">
                            <input type="text" placeholder="Country" required="" name = "country">
                             <i  class="glyphicon glyphicon-home"></i>
                        </div>
                        <div class="login-mail">
                            <input type="text" placeholder="State" required="" name = "state">
                            <i  class="glyphicon glyphicon-home"></i>
                        </div>

                        <div class="login-mail">
                            <input type="text" placeholder="Address Details" required="" name = "addressDetails">
                            <i  class="glyphicon glyphicon-home"></i>
                        </div>
                        <div class="login-mail">
                            <input type="text" placeholder="Land Mark" required="" name = "landMark">
                            <i  class="glyphicon glyphicon-map-marker"></i>
                        </div>

                        <div class="clearfix"> </div>
                </form>
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

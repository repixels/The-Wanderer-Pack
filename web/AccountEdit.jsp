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
            <form action ="EditProfileServlet">
                <div class="col-md-6 login-do">
                   
                    <div class="login-mail">
                        <input type="text" value="${name}" required="" name="userName">
                        <i  class="glyphicon glyphicon-user"></i>
                    </div>
                    
                    <div class="login-mail">
                        <input type="text" value= "${email}" required="" name="email">
                        <i  class="glyphicon glyphicon-envelope"></i>
                    </div>
                    <div class="login-mail">
                        <input type="password" value="${password}" required="" name="password">
                        <i class="glyphicon glyphicon-lock"></i>
                    </div>
                         <div class="login-mail">
                        <input type="number" value="${creditNumber}" required="" name="creditNumber">
                        <i  class="glyphicon glyphicon-home"></i>
                    </div>
                    <div class="login-mail">
                        <Label>Choose your Interests</label>
                    </div>
                    <div class="login-mail">
                        <br/>   
                        <a class="news-letter " href="#">
                            <label class="checkbox1"><input type="checkbox" name="interests" value="Sports" id = "Sports"><i> </i>Sports</label>
                            <label class="checkbox1"><input type="checkbox" name="interests" value="Reading" id = "Reading" ><i> </i>Reading</label>
                            <label class="checkbox1"><input type="checkbox" name="interests" value="Music" id = "Music"><i> </i>Music</label>
                        </a>
                    </div>
                    <label class="hvr-skew-backward">
                        <input type="submit" value="Save">
                    </label>
                </div>
                <div class="col-md-6 login-right">
 
                    <div class="login-mail">
                        <input type="text" value="${city}" required="" name="city">
                        <i  class="glyphicon glyphicon-home"></i>
                    </div>
                    <div class="login-mail">
                        <input type="text" value="${country}" required="" name="country">
                    </div>
                  
                    <div class="login-mail">
                        <input type="text" value="${state}" required=""name="state">
                        <i  class="glyphicon glyphicon-home"></i>
                    </div>
                    <div class="login-mail">
                        <input type="text" value="${addressDetails}"required="" name="addressDetails">
                        <i  class="glyphicon glyphicon-home"></i>
                    </div>
                    <div class="login-mail">
                        <input type="text" value="${landMark}" required="" name="landMark">
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
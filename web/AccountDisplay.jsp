<%-- 
    Document   : AccountDisplay
    Created on : Apr 8, 2016, 1:51:50 PM
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
            <h1>Welcome</h1>
            <em></em>
            <h2><a href="index.jsp">Home</a><label>/</label>MyAccount</a></h2>
        </div>
    </div>
    <!--login-->
    <div class="container">
        <div class="login">
            <div class="col-md-6 login-right">
                <h3>Your Contact Information</h3>
            </div>
        </div>
    <div class="login">
		
			<form action="EditProfileServlet">
			<div class="col-md-6 login-do">
			<div class="login-mail">
					<!--<input type="text" placeholder="Name" required="">-->
					<i  class="glyphicon glyphicon-user"></i>
					<label>Name/ </label>
					
					
				</div>
				<div class="login-mail">
					<!--input type="text" placeholder="Phone Number" required="">-->
					<i  class="glyphicon glyphicon-phone"></i>
					<label>Phone Number/ </label>
				</div>
				<div class="login-mail">
					<!--<input type="text" placeholder="Email" required="">-->
					<i  class="glyphicon glyphicon-envelope"></i>
					<label>Email/ </label>
				</div>
				<div class="login-mail">
					<!--<input type="password" placeholder="Password" required="">-->
					<i class="glyphicon glyphicon-lock"></i>
					<label>Password/ </label>
				</div>
				  				
                           <label class="hvr-skew-backward">
                            <a href="AccountEdit.jsp" class="hvr-skew-backward">Edit Info</a>
                        </label>
			</div>
			
			<div class="clearfix"> </div>
			
			<!-- Order!//////////////////////////////////////////////////////////////////////////////////////////////////////////////-->
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
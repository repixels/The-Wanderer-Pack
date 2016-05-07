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
            <form >
                <div class="col-md-6 login-do">
                    <div class="login-mail">
                        <input type="text" placeholder="Email" required="" name="emailLogin" id="emailLogin">
                        <i  class="glyphicon glyphicon-envelope"></i>
                    </div>
                    <div class="login-mail">
                        <input type="password" placeholder="Password" required="" name="pwdLogin" id="pwdLogin" >
                        <i class="glyphicon glyphicon-lock"></i>
                    </div>
                    <!--<a class="news-letter " href="#">
                        <label class="checkbox1"><input type="checkbox" name="checkbox" ><i> </i>Forget Password</label>
                    </a>-->
                    <div> 
                        <label id="wrongMailLBL_id" style="color: red"/>
                    </div>
                    <label class="hvr-skew-backward">
                        <input class=" hvr-skew-backward" type="button" value="login" id="login_id" onclick="nameValidAJAX();
                                return false">
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
<script type="text/javascript">
    //---------------------------2ND part----------------------------------------------------
    var reqSND = null;
    function nameValidAJAX()
    {
        var email = document.getElementById('emailLogin').value;
        var pwd = document.getElementById('pwdLogin').value;
        if (window.XMLHttpRequest)
        {
            reqSND = new XMLHttpRequest();
        }
        else if (window.ActiveXObject)
        {
            reqSND = new ActiveXObject(Microsoft.XMLHTTP)
        }
        reqSND.onreadystatechange = handleReqSND;
        reqSND.open("GET", "LoginServlet?emailLogin=" + email + "&pwdLogin=" + pwd + "&flagLogin=1", true)
        reqSND.send(null);
        //  document.getElementById('textOut_id').innerHTML = "Must Enter Name";
        // alert("called");
    }
    ;
    function handleReqSND(responseTxt, statusTxt, xhr)
    {
        if (reqSND.readyState == 4)
        {
            if (reqSND.status == 200)
            {

                var sth = reqSND.responseText;
                //  alert(sth)

                if (sth == "Sorry")
                {
                    document.getElementById('wrongMailLBL_id').innerHTML = " Sorry, password is wrong";
                }

                if (sth == "okay")
                {
                    window.location = "index.jsp";

                }
                else
                {
                    document.getElementById('wrongMailLBL_id').innerHTML = " Sorry, That's a wrong Email";
                }
            }
            else
            {
                document.getElementById('wrongMailLBL_id').innerHTML = " Error: " + reqSND.status;
            }
        }
    }
    ;

</script>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->

<script src="js/simpleCart.min.js"></script>
<!-- slide -->
<script src="js/bootstrap.min.js"></script>

</body>
</html>

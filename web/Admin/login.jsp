<%-- 
    Document   : login
    Created on : Apr 24, 2016, 9:06:33 PM
    Author     : Ehab
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en" class="body-full-height">
    <head>        
        <!-- META SECTION -->
        <title>The Wanderer Pack - Admin | Welcome Back</title>            
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        
        <link rel="icon" href="favicon.ico" type="image/x-icon" />
        <!-- END META SECTION -->
        
        <!-- CSS INCLUDE -->        
        <link rel="stylesheet" type="text/css" id="theme" href="css/theme-default.css"/>
        <!-- EOF CSS INCLUDE -->                                     
    </head>
    <body>
        
        <div class="login-container lightmode">
        
            <div class="login-box animated fadeInDown">
                <div class="login-logo"></div>
                <div class="login-body">
                    <div class="login-title"><strong>Log In</strong> to your account</div>
                    <form action="Admin?action=login" class="form-horizontal" method="post" id="loginForm">
                    <div class="form-group">
                        <div class="col-md-12">
                            <input type="email" class="form-control" placeholder="E-mail" name="email" id="email" required="required" />
                            <label class="error push-up-10" id="emailError" style="text-align: right;padding-right: 5px;"></label> 
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-12">
                            <input type="password" class="form-control" placeholder="Password" name="password" id="password" required="required" />
                            <label class="error push-up-10" id="passwordError" style="text-align: right;padding-right: 5px;"></label> 
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-6">
                            <a href="#" class="btn btn-link btn-block">Forgot your password?</a>
                        </div>
                        <div class="col-md-6">
                            <button class="btn btn-info btn-block" type="button" onclick="validateLogin()">Log In</button>
                        </div>
                    </div>
                        <input hidden="hidden" name="action" value="login" />
                    </form>
                </div>
                <div class="login-footer">
                    <div class="pull-left">
                        &copy; 2016 The Wanderer Pack
                    </div>
                </div>
            </div>
            
        </div>
        <script src="js/plugins/jquery/jquery.min.js" type="text/javascript"></script>
        <script>
            function validateLogin()
            {
                var uName = $('#email').val();
                var uPassword = $('#password').val();
                
                $('#emailError').text(''); 
                $('#passwordError').text('');
                
                var emailRegex = /^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
                

                var passwordError = $('#passwordError');
                
                if(uName.length === 0 || uName.trim() === '')
                {                    
                    $('#emailError').text("This field is required!"); 
                }
                else
                {
                    if(uPassword.length === 0 || uPassword.trim() === '')
                    {
                        $('#passwordError').text("This field is required!");
                    }
                    else if(emailRegex.test(uName) && uName === "admin@thewandererpack.com")
                    {
                        $('#loginForm').submit();
                    }
                    else
                    {
                        $('#emailError').text("Wrong Email!");
                    }
                        
                }
                
            }
        </script>
    </body>
</html>
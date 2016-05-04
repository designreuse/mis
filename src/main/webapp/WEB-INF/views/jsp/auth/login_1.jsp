<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Login</title>
        <meta name='description' content='A simple page'>
    </head>
    <body>
        <div class="login-box">
            <div class="login-logo">
                <%-- <img src="<c:url value='/resources/theme/dist/img/logo.png' />" /> --%>
                <h2>MIS Admin Panel</h2>
            </div><!-- /.login-logo -->
            <form action="/login" method="post">                
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="login-box-body">
                    <div class="form-group has-feedback">
                        <input type="text" id="username" name="username" class="form-control" placeholder="Username"/>
                        <span class="glyphicon glyphicon-user form-control-feedback"></span>
                    </div>
                    <div class="form-group has-feedback">
                        <input type="password" id="password" name="password" class="form-control" placeholder="Password"/>
                        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                    </div>
                    <div class="row">
                        <div class="col-xs-8">    
                            <div>
                                <label>
                                    <input type="checkbox"> Remember Me
                                </label>
                            </div>                        
                        </div><!-- /.col -->
                        <div class="col-xs-4">
                            <button type="submit" id="submit" class="btn btn-primary btn-block btn-flat">Sign In</button>
                        </div><!-- /.col -->
                    </div>
                    <c:if test="${param.error != null}">    
                        <div class="row">
                            <div class="col-xs-12">  
                                <div id="errorMsg">
                                    Invalid username and password.
                                </div>
                            </div>
                        </div>
                    </c:if>
                </div><!-- /.login-box-body -->
            </form>
        </div><!-- /.login-box -->
        <!--
        <script>
            $("#submit").click(function () {
                var error = false;
                if ($("#username").val() == null || $("#password").val() == '') {
                    $("#errorMsg").html("<p>Please fill in the username field.</p>");
                    error = true;
        
                }
                if ($("#password").val() == null || $("#password").val() == '') {
                    $("#errorMsg").html("<p>Please fill in the password field.</p>");
                    error = true;
        
                }
                if (error == false) {
                    $.ajax({
                        url: '/travelAgency/login/check',
                        data: {
                            username : $("#username").val(),
                            password : $("#password").val()
                        },
                        success: function (data) {
                            if(data=="forbidden")
                                $("#errorMsg").html("<p>The credentials you provided are not correct.</p>");
                            else
                                window.location.replace("/travelAgency/");
                        }
                    });
                }
            });
        </script>
        -->
    </body>
</html>

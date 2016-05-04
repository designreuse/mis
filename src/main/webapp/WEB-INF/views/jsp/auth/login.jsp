<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<content tag="title">Login</content>

<content tag="content">

    <div class="login-box">
        <div class="login-logo">
            <%-- <img src="<c:url value='/resources/theme/dist/img/logo.png' />" /> --%>
            <h2>MIS Admin Panel</h2>
        </div><!-- /.login-logo -->
        <form action="<c:url value='/'/>login" modelattribute="user" method="post">                
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
     </content>
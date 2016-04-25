<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Create user</title>
    </head>
    <body>
        <section class="content-header">
            <h1>
                Create user
            </h1>
        </section>
        <section class="content">

            <div class="row">
                <div class="col-md-12">
                    <div class="box box-info">
                        <div class="box-header with-border">
                            <h3 class="box-title">Create user</h3>
                        </div><!-- /.box-header -->
                        <div class="box-body">
                            <form role="form" action="<c:url value="/users/store"/>" method="POST">
                                <div class="row">
                                    <div class="col-md-4">
                                        <div class="form-group">
                                            <label>First Name:</label>
                                            <input name="firstName" class="form-control">
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-group">
                                            <label>Last Name:</label>
                                            <input name="lastName" class="form-control">
                                        </div>
                                    </div>                                    
                                    <div class="col-md-4">
                                        <div class="form-group">
                                            <label>Username</label>
                                            <input name="username" class="form-control">
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-4">
                                        <div class="form-group">
                                            <label>Password</label>
                                            <input type="password" name="password" class="form-control">
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-group">
                                            <label>Role</label>
                                            <select name="roleId" class="form-control">
                                                <c:forEach var="role" items="${roles}">
                                                    <option value="${role.id}">${role.name}</option>
                                                </c:forEach>
                                            </select>                                       
                                        </div>
                                    </div>
                                 </div>                                
                                <button type="submit" class="btn btn-default">Save</button>
                            </form>
                        </div><!-- /.box-body -->
                    </div>
                </div>
            </div>
        </section>
    </body>

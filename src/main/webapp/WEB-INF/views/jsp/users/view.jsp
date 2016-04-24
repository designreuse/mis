<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View all users</title>
    </head>
    <body>
        <section class="content-header">
            <h1>
                View all users
            </h1>
        </section>
        <section class="content">

            <div class="row">
                <div class="col-md-12">
                    <div class="box box-info">
                        <div class="box-header with-border">
                            <h3 class="box-title">View all users</h3>
                        </div><!-- /.box-header -->
                        <div class="box-body">
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Name</th>
                                            <th>Username</th>
                                            <th>Password</th>
                                            <th>Role</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="user" items="${users}">
                                            <tr>
                                                <td><c:out value="${user.id}"/></td>
                                                <td><c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/></td>
                                                <td><c:out value="${user.username}"/></td>
                                                <td><c:out value="${user.password}"/></td>
                                                <c:forEach var="role" items="${user.roles}">
                                                <td><c:out value="${role.name}"/></td>
                                                </c:forEach>
                                                <td>
                                                    <a href="<c:url value="/users/edit/${user.id}"/>">
                                                        <button type="button" class="btn btn-info"><i class="fa fa-edit"></i></button>
                                                    </a>
                                                    <a href="<c:url value="/users/delete/${user.id}"/>">
                                                        <button typ e="button" class="btn btn-danger"><i class="fa fa-trash"></i></button>
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div><!-- /.box-body -->
                    </div>
                </div>
            </div>
        </section>
    </body>


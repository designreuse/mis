<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Scheduled Visits</title>
    </head>
    <body>
        <section class="content-header">
            <h1>
                View Scheduled Visits
            </h1>
        </section>
        <section class="content">

            <div class="row">
                <div class="col-md-12">
                    <div class="box box-info">
                        <div class="box-header with-border">
                            <h3 class="box-title">New Visits List</h3>
                        </div><!-- /.box-header -->
                        <div class="box-body">
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Visitor/s</th>
                                            <th>Cycle</th>
                                            <th>Doctor</th>
                                            <th>Status</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="visit" items="${newVisits}">
                                            <tr>
                                                <td><c:out value="${visit.id}"/></td>
                                                <td><c:out value="${visit.medicalVisitor.firstName}"/> <c:out value="${visit.medicalVisitor.lastName}"/></td>
                                                <td><c:out value="${visit.cycle.startDate}"/> - <c:out value="${visit.cycle.endDate}"/></td>
                                                <td><c:out value="${visit.doctor.firstName}"/> <c:out value="${visit.doctor.lastName}"/></td>                          
                                                <td><c:out value="${visit.status}"/></td>                                        
                                                <td>
                                                    <a href="<c:url value="/ScheduledVisits/edit/${visit.id}"/>">
                                                        <button type="button" class="btn btn-info"><i class="fa fa-edit"></i></button>
                                                    </a>
                                                    <a href="<c:url value="/ScheduledVisits/delete/${visit.id}"/>">
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


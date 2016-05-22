<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<content tag="title">Dashboard</content>

<content tag="content">
 
    <section class="content-header">
        <h1>
            Dashboard
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <sec:authorize access="hasRole('ROLE_ADMIN')"> 
        <!-- Info boxes -->
        <div class="row">
            <div class="col-md-3 col-sm-6 col-xs-12">
                <div class="info-box">
                    <span class="info-box-icon bg-aqua"><i class="fa fa-users"></i></span>
                    <div class="info-box-content">
                        <span class="info-box-text">Medical Visitors</span>
                        <span class="info-box-number">${medicalVisitors}</span>
                    </div><!-- /.info-box-content -->
                </div><!-- /.info-box -->
            </div><!-- /.col -->
            <div class="col-md-3 col-sm-6 col-xs-12">
                <div class="info-box">
                    <span class="info-box-icon bg-red"><i class="fa fa-user-md"></i></span>
                    <div class="info-box-content">
                        <span class="info-box-text">Doctors</span>
                        <span class="info-box-number">${doctors}</span>
                    </div><!-- /.info-box-content -->
                </div><!-- /.info-box -->
            </div><!-- /.col -->

            <!-- fix for small devices only -->
            <div class="clearfix visible-sm-block"></div>

            <div class="col-md-3 col-sm-6 col-xs-12">
                <div class="info-box">
                    <span class="info-box-icon bg-green"><i class="fa fa-clock-o"></i></span>
                    <div class="info-box-content">
                        <span class="info-box-text">Scheduled Visits</span>
                        <span class="info-box-number">${scheduledVisits}</span>
                    </div><!-- /.info-box-content -->
                </div><!-- /.info-box -->
            </div><!-- /.col -->
            <div class="col-md-3 col-sm-6 col-xs-12">
                <div class="info-box">
                    <span class="info-box-icon bg-yellow"><i class="fa fa-clock-o"></i></span>
                    <div class="info-box-content">
                        <span class="info-box-text">Paid Visits</span>
                        <span class="info-box-number">${paidVisits}</span>
                    </div><!-- /.info-box-content -->
                </div><!-- /.info-box -->
            </div><!-- /.col -->
        </div><!-- /.row -->
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_MEDICAL_VISITOR')">
        <!-- Main row -->
        <div class="row">
            <!-- Left col -->
            <div class="col-md-12">
                <!-- MAP & BOX PANE -->
                <div class="box box-success">
                    <div class="box-header with-border">
                        <h3 class="box-title">Individual Scheduled Visits</h3>
                    </div><!-- /.box-header -->
                    <div class="box-body no-padding">
                        <div class="row">
                            <div class="col-md-12 col-sm-11">
                                <div class="pad">
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
                                                        <c:forEach var="visitor" items="${visit.medicalVisitors}">
                                                            <td><c:out value="${visitor.firstName}"/> <c:out value="${visitor.lastName}"/></td>
                                                        </c:forEach>
                                                        <td><c:out value="${visit.cycle.startDate}"/> - <c:out value="${visit.cycle.endDate}"/></td>
                                                        <td><c:out value="${visit.doctor.firstName}"/> <c:out value="${visit.doctor.lastName}"/></td>                          
                                                        <td><c:out value="${visit.status}"/></td>                                            
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div><!-- /.col -->
                        </div><!-- /.row -->
                    </div><!-- /.box-body -->
                </div><!-- /.box -->
            </div>
        </div><!-- /.row -->
        
        <div class="row">
            <!-- Left col -->
            <div class="col-md-12">
                <!-- MAP & BOX PANE -->
                <div class="box box-success">
                    <div class="box-header with-border">
                        <h3 class="box-title">Group Scheduled Visits</h3>
                    </div><!-- /.box-header -->
                    <div class="box-body no-padding">
                        <div class="row">
                            <div class="col-md-12 col-sm-11">
                                <div class="pad">
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
                                                <c:forEach var="gvisit" items="${newGroupVisits}">
                                                    <tr>
                                                        <td><c:out value="${gvisit.id}"/></td>
                                                        <c:forEach var="group" items="${gvisit.groups}">
                                                            <td><c:out value="${group.name}"/></td>
                                                        </c:forEach>
                                                        <td><c:out value="${gvisit.cycle.startDate}"/> - <c:out value="${gvisit.cycle.endDate}"/></td>
                                                        <td><c:out value="${gvisit.doctor.firstName}"/> <c:out value="${gvisit.doctor.lastName}"/></td>                          
                                                        <td><c:out value="${gvisit.status}"/></td>                                        
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div><!-- /.col -->
                        </div><!-- /.row -->
                    </div><!-- /.box-body -->
                </div><!-- /.box -->
            </div>
        </div><!-- /.row -->  
       </sec:authorize>
    </section><!-- /.content -->

</content>

<content tag="footerScripts">
    <script src="<c:url value='/resources/theme/dist/js/pages/dashboard2.js'/>" type="text/javascript"></script>
</content>
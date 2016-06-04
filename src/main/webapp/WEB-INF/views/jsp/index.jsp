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

        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <div class="row">
                <div class="col-md-8">
                   <div class="box box-info">
                            <div class="box-header with-border">
                                <h3 class="box-title">Paid Visits Calendar</h3>
                            </div><!-- /.box-header -->
                            <div class="box-body no-padding">
                                <div id="calendar">
                                    </div>
                                </div><!-- /.row -->
                            </div><!-- /.box-body -->
                        </div><!-- /.box -->
                </div><!-- /.col -->
            </div>
        </sec:authorize>


        <sec:authorize access="hasRole('ROLE_MEDICAL_VISITOR')">
            <div class="row">
                <div class="col-md-3 col-sm-6 col-xs-12">
                    <div class="info-box">
                        <span class="info-box-icon bg-green"><i class="fa fa-clock-o"></i></span>
                        <div class="info-box-content">
                            <span class="info-box-text">Scheduled Visits</span>
                            <span class="info-box-number">${newVisits.size()}</span>
                        </div><!-- /.info-box-content -->
                    </div><!-- /.info-box -->
                </div><!-- /.col -->
                <div class="col-md-3 col-sm-6 col-xs-12">
                    <div class="info-box">
                        <span class="info-box-icon bg-yellow"><i class="fa fa-clock-o"></i></span>
                        <div class="info-box-content">
                            <span class="info-box-text">Paid Visits</span>
                            <span class="info-box-number">${paidVisitsList.size()}</span>
                        </div><!-- /.info-box-content -->
                    </div><!-- /.info-box -->
                </div><!-- /.col -->  

                <!-- fix for small devices only -->
                <div class="clearfix visible-sm-block"></div>

                <div class="col-md-3 col-sm-6 col-xs-12">
                    <div class="info-box">
                        <span class="info-box-icon bg-green"><i class="fa fa-clock-o"></i></span>
                        <div class="info-box-content">
                            <span class="info-box-text">Group Sch. Visits</span>
                            <span class="info-box-number">${newGroupVisits.size()}</span>
                        </div><!-- /.info-box-content -->
                    </div><!-- /.info-box -->
                </div><!-- /.col -->
                <div class="col-md-3 col-sm-6 col-xs-12">
                    <div class="info-box">
                        <span class="info-box-icon bg-yellow"><i class="fa fa-clock-o"></i></span>
                        <div class="info-box-content">
                            <span class="info-box-text">Group Paid Visits</span>
                            <span class="info-box-number">${groupVisits.size()}</span>
                        </div><!-- /.info-box-content -->
                    </div><!-- /.info-box -->
                </div><!-- /.col -->  


            </div>    
            <!-- Main row -->
            <div class="row">
                <!-- Left col -->
                <div class="col-md-12">
                    <!-- MAP & BOX PANE -->
                    <div class="box box-info">
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
                    <div class="box box-info">
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

            <div class="row">
                <!-- Left col -->
                <div class="col-md-12">
                    <!-- MAP & BOX PANE -->
                    <div class="box box-info">
                        <div class="box-header with-border">
                            <h3 class="box-title">Individual Paid Visits</h3>
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
                                                        <th>Visit ID</th>
                                                        <th>Visitor/s</th>
                                                        <th>Doctor</th>
                                                        <th>Cycle</th>
                                                        <th>Date</th>
                                                        <th>Hour</th>
                                                        <th>Week</th>
                                                        <th>Group</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="paidList" items="${paidVisitsList}">
                                                        <tr>
                                                            <td><c:out value="${paidList.id}"/></td>
                                                            <td><c:out value="${paidList.scheduledVisit.id}"/></td>
                                                            <c:forEach var="visitor" items="${paidList.scheduledVisit.medicalVisitors}">
                                                                <td><c:out value="${visitor.firstName}"/> <c:out value="${visitor.lastName}"/></td>
                                                            </c:forEach>                                                      
                                                            <td><c:out value="${paidList.scheduledVisit.doctor.firstName}"/> <c:out value="${paidList.scheduledVisit.doctor.lastName}"/></td>
                                                            <td><c:out value="${paidList.scheduledVisit.cycle.startDate}"/> - <c:out value="${paidList.scheduledVisit.cycle.endDate}"/></td>
                                                            <td><c:out value="${paidList.date}"/></td>
                                                            <td><c:out value="${paidList.hour}"/></td>
                                                            <td><c:out value="${paidList.week}"/></td>
                                                            <td><c:out value="${paidList.isGroup}"/></td>  
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
                    <div class="box box-info">
                        <div class="box-header with-border">
                            <h3 class="box-title">Group Paid Visits</h3>
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
                                                        <th>Visit ID</th>
                                                        <th>Visitor/s</th>
                                                        <th>Doctor</th>
                                                        <th>Cycle</th>
                                                        <th>Date</th>
                                                        <th>Hour</th>
                                                        <th>Week</th>
                                                        <th>Group</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="groupVisit" items="${groupVisits}">
                                                        <tr>
                                                            <td><c:out value="${groupVisit.id}"/></td>
                                                            <td><c:out value="${groupVisit.scheduledVisit.id}"/></td>
                                                            <c:forEach var="group" items="${groupVisit.scheduledVisit.groups}">
                                                                <td><c:out value="${group.name}"/></td>
                                                            </c:forEach>
                                                            <td><c:out value="${groupVisit.scheduledVisit.doctor.firstName}"/> <c:out value="${groupVisit.scheduledVisit.doctor.lastName}"/></td>
                                                            <td><c:out value="${groupVisit.scheduledVisit.cycle.startDate}"/> - <c:out value="${groupVisit.scheduledVisit.cycle.endDate}"/></td>
                                                            <td><c:out value="${groupVisit.date}"/></td>
                                                            <td><c:out value="${groupVisit.hour}"/></td>
                                                            <td><c:out value="${groupVisit.week}"/></td>
                                                            <td><c:out value="${groupVisit.isGroup}"/></td>  
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
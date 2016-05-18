<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<content tag="title">View paid visits</content>

<content tag="content">
    <section class="content-header">
        <h1>
            View Paid Visits
        </h1>
    </section>
    <section class="content">

        <div class="row">
            <div class="col-md-12">
                <div class="box box-info">
                    <div class="box-header with-border">
                        <h3 class="box-title">Paid Visits List</h3>
                    </div><!-- /.box-header -->
                    <div class="box-body">     
                        <ul class="nav nav-pills">  
                            <li class="active">
                                <a href="#individual-pills" data-toggle="tab" aria-expanded="true">Individual Visits</a>
                            </li>
                            <li class>
                                <a href="#group-pills" data-toggle="tab" aria-expanded="false">Group Visits</a>
                            </li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane fade active in" id="individual-pills">    
                                <div class="table-responsive">
                                    <c:if test="${not empty paidVisits}">
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
                                                <c:forEach var="paidVisit" items="${paidVisits}">
                                                    <tr>
                                                        <td><c:out value="${paidVisit.id}"/></td>
                                                        <td><c:out value="${paidVisit.scheduledVisit.id}"/></td>
                                                        <c:forEach var="visitor" items="${paidVisit.scheduledVisit.medicalVisitors}">
                                                            <td><c:out value="${visitor.firstName}"/> <c:out value="${visitor.lastName}"/></td>
                                                        </c:forEach>                                                      
                                                        <td><c:out value="${paidVisit.scheduledVisit.doctor.firstName}"/> <c:out value="${paidVisit.scheduledVisit.doctor.lastName}"/></td>
                                                        <td><c:out value="${paidVisit.scheduledVisit.cycle.startDate}"/> - <c:out value="${paidVisit.scheduledVisit.cycle.endDate}"/></td>
                                                        <td><c:out value="${paidVisit.date}"/></td>
                                                        <td><c:out value="${paidVisit.hour}"/></td>
                                                        <td><c:out value="${paidVisit.week}"/></td>
                                                        <td><c:out value="${paidVisit.isGroup}"/></td>  
                                                        <td>
                                                            <a href="<c:url value="/paidVisits/info/${paidVisit.id}"/>" class="btn btn-info">
                                                                <i class="fa fa-info"></i>
                                                            </a>
                                                            <sec:authorize access="hasRole('ROLE_ADMIN')">    
                                                            <button typ e="button" class="btn btn-danger deletePaidVisit" data-id="${paidVisit.id}"><i class="fa fa-trash"></i></button>
                                                            </sec:authorize>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </c:if> 
                                    <c:if test="${empty paidVisits}">  
                                        <div class="alert alert-warning">The are no available records to view!</div>
                                    </c:if>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="group-pills"> 
                                <div class="table-responsive">
                                    <c:if test="${not empty groupVisits}">
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
                                                        <td>
                                                            <a href="<c:url value="/paidVisits/info/${groupVisit.id}"/>" class="btn btn-info">
                                                                <i class="fa fa-info"></i>
                                                            </a>
                                                            <button typ e="button" class="btn btn-danger deletePaidVisit" data-id="${groupVisit.id}"><i class="fa fa-trash"></i></button>    
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </c:if> 
                                    <c:if test="${empty groupVisits}">  
                                        <div class="alert alert-warning">The are no available records to view!</div>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div><!-- /.box-body -->
                </div>
            </div>
        </div>
    </section>
</content>
<content tag="footerScripts">
    <script src="<c:url value='/resources/js/paidVisit.js'/>" type="text/javascript"></script>
</content>


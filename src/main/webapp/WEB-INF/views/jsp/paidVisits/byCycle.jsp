<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<content tag="title">View scheduled visits</content>

<content tag="content">
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
                        <form role="form" action="<c:url value="/paidVisits/byCycle"/>" method="POST">                          
                            <select name="cycleId" id="cycleId" class="form-control">
                                <option value="0">[-- select --]</option>
                                <c:forEach var="cycle" items="${cyclesList}">
                                    <option value="${cycle.id}">${cycle.startDate} - ${cycle.endDate}</option>
                                </c:forEach>
                            </select>                         
                            <button type="submit" class="btn btn-default">Select</button>
                        </form>
                        <div class="tab-content">
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
                                                    <c:forEach var="group" items="${visit.scheduledVisit.groups}">
                                                        <td><c:out value="${group.name}"/></td>
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
                                                        <button typ e="button" class="btn btn-danger deletePaidVisit" data-id="${paidVisit.id}"><i class="fa fa-trash"></i></button>    
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
                    </div><!-- /.box-body -->
                </div>
            </div>
        </div>
    </section>
</content>

<content tag="footerScripts">
    <script src="<c:url value='/resources/js/paidVisit.js'/>" type="text/javascript"></script>
</content>




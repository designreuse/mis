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
                        <form role="form" action="<c:url value="/scheduledVisits/byCycle"/>" method="POST">                          
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
                                <c:if test="${not empty newVisits}">                                                       
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
                                                        <a href="<c:url value="/paidVisits/create/${visit.id}"/>" class="btn btn-info">
                                                            <i class="fa fa-check-square-o"></i>
                                                        </a>  
                                                        <button type="button" class="btn btn-danger deleteVisit" data-id="${visit.id}"><i class="fa fa-trash"></i></button>                               
                                                    </td>      
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </c:if> 
                                <c:if test="${empty newVisits}">  
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
    <script src="<c:url value='/resources/js/scheduledVisit.js'/>" type="text/javascript"></script>
</content>



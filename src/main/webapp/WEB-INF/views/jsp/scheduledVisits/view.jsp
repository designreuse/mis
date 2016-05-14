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
                        <ul class="nav nav-tabs nav-pills" id="myTab">
                            <li class="dropdown"> <a href="#" class="dropdown-toggle" data-toggle="dropdown">Cycles <span class="caret"></span></a>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a href="#tab1" data-toggle="tab">Current Active</a>
                                    </li>
                                    <li><a href="#tab2" data-toggle="tab">All Visits</a>
                                    </li>                                
                                </ul>
                            </li>
                        </ul>
                        <div class="tab-content">
                        <div class="tab-pane active" id="tab1">
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
                        <div class="tab-pane" id="tab2">
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
                                        <c:forEach var="visit" items="${newAllVisits}">
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
                                                    <button typ e="button" class="btn btn-danger deleteVisit" data-id="${visit.id}"><i class="fa fa-trash"></i></button>                               
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


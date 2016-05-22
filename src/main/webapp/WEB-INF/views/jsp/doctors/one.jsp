<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<content tag="title">View doctor</content>

<content tag="content">
    <section class="content-header">
        <h1>
            View doctor
        </h1>
    </section>
    <section class="content">

        <div class="row">
            <div class="col-md-4">
                <div class="box box-info">
                    <div class="box-header with-border">
                        <h3 class="box-title">Info</h3>
                    </div><!-- /.box-header -->
                    <div class="box-body">
                        <h4>${doctor.firstName} ${doctor.lastName}
                            <small>${doctor.specialty.name}</small>
                        </h4>

                        <h5>${doctor.position} at ${doctor.institution.name}</h5>

                        <p><i class="fa fa-map-marker"></i> ${doctor.address}, ${doctor.city.name}, ${doctor.geolocationArea.name} </p>
                        <p><i class="fa fa-phone"></i> ${doctor.phone}</p>
                        <p><i class="fa fa-envelope"></i> <a href="mailto:${doctor.email}">${doctor.email}</a></p>

                        <div class="text-right">
                            <c:if test="${doctor.editable}">
                                <a href="<c:url value="/doctors/edit/${doctor.id}"/>">
                                    <button type="button" class="btn btn-success btn-40"><i class="fa fa-edit"></i></button>
                                </a>
                            </c:if>
                            <c:if test="${doctor.deletable}">
                                <button type="button" class="btn btn-danger btn-40 deleteDoctor" data-id="${doctor.id}"><i class="fa fa-trash"></i></button>
                                </c:if>
                        </div>
                    </div><!-- /.box-body -->
                </div>
            </div>
            <div class="col-md-8">
                <div class="box box-info">
                    <div class="box-header with-border">
                        <h3 class="box-title">Visits</h3>
                    </div><!-- /.box-header -->
                    <div class="box-body">
                        <ul class="nav nav-pills">  
                            <li class="active">
                                <a href="#scheduledGroup-pills" data-toggle="tab" aria-expanded="true">Scheduled Visits</a>
                            </li>
                            <li class>
                                <a href="#paidGroup-pills" data-toggle="tab" aria-expanded="false">Paid Visits</a>
                            </li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane fade active in" id="scheduledGroup-pills">
                                <table class="table">    
                                    <thead>
                                        <tr>
                                            <th>Group/Name</th>
                                            <th>Cycle</th>                            
                                            <th>Status</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="schvisits" items="${newVisitsList}">
                                            <tr>
                                                <c:forEach var="visitor" items="${schvisits.medicalVisitors}">
                                                    <td><c:out value="${visitor.firstName}"/> <c:out value="${visitor.lastName}"/></td>
                                                </c:forEach>
                                                <c:forEach var="gname" items="${schvisits.groups}">
                                                    <td><c:out value="${gname.name}"/></td>
                                                </c:forEach>                                             
                                                <td><c:out value="${schvisits.cycle.startDate}"/> - <c:out value="${schvisits.cycle.endDate}"/></td>
                                                <td><c:out value="${schvisits.status}"/> (<c:out value="${schvisits.paidVisits.size()}"/>)</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <div class="tab-pane fade" id="paidGroup-pills"> 
                                <table class="table">    
                                    <thead>
                                        <tr>
                                            <th>Group/Name</th>
                                            <th>Cycle</th>                            
                                            <th>Date</th>
                                            <th>Hour</th>
                                            <th>Week</th>
                                        </tr>
                                    </thead>
                                    <tbody> 
                                        <c:forEach var="pvisits" items="${paidVisits}">
                                            <tr>
                                                <c:forEach var="visitor" items="${pvisits.scheduledVisit.medicalVisitors}">
                                                    <td><c:out value="${visitor.firstName}"/> <c:out value="${visitor.lastName}"/></td>
                                                </c:forEach>
                                                <c:forEach var="group" items="${pvisits.scheduledVisit.groups}">
                                                    <td><c:out value="${group.name}"/></td>
                                                </c:forEach>    
                                                <td><c:out value="${pvisits.scheduledVisit.cycle.startDate}"/> - <c:out value="${pvisits.scheduledVisit.cycle.endDate}"/></td> 
                                                <td><c:out value="${pvisits.date}"/></td>
                                                <td><c:out value="${pvisits.hour}"/></td>
                                                <td><c:out value="${pvisits.week}"/></td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div><!-- /.box-body -->
                </div>
            </div>
        </div>
    </section>
</content>

<content tag="footerScripts">
    <script src="<c:url value='/resources/js/doctors.js'/>" type="text/javascript"></script>
</content>
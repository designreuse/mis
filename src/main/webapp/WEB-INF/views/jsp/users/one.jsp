<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
                        <h4>${user.firstName} ${user.lastName}
                        </h4> 
                        <h4><small>Username: ${user.username}</small></h4>

                        
                        <c:forEach var="role" items="${user.roles}">
                            <c:if test = "${role.name == 'ROLE_ADMIN'}">
                                <h5>Role: Administrator</h5>  
                            </c:if>
                            <c:if test = "${role.name == 'ROLE_MEDICAL_VISITOR'}">
                                <h5>Role: Medical Visitor</h5>  
                            </c:if>   
                        </c:forEach>

                        <p><i class="fa fa-envelope"></i> <a href="mailto:${user.email}">${user.email}</a></p>

                        <div class="text-right">
                        <a href="<c:url value="/users/edit/${user.id}"/>">
                            <button type="button" class="btn btn-success btn-40"><i class="fa fa-edit"></i></button>
                        </a>
                        <button type="button" class="btn btn-danger btn-40 deleteUser" data-id="${user.id}"><i class="fa fa-trash"></i></button>
                        </div>
                    </div><!-- /.box-body -->
                </div>
            </div>
            <div class="col-md-8">
                <div class="box box-info">
                    <div class="box-header with-border">
                        <h3 class="box-title">Group etc</h3>
                    </div><!-- /.box-header -->
                    <div class="box-body">
                        ...

                    </div><!-- /.box-body -->
                </div>
            </div>            
            <div class="col-md-8">
                <div class="box box-info">
                    <div class="box-header with-border">
                        <h3 class="box-title">Visits - Current Active Cycle</h3>
                    </div><!-- /.box-header -->
                    <div class="box-body">
                    <ul class="nav nav-pills">  
                        <li class="active">
                            <a href="#scheduled-pills" data-toggle="tab" aria-expanded="true">Scheduled Visits (${userVisits.size()})</a>
                        </li>
                        <li class>
                            <a href="#paid-pills" data-toggle="tab" aria-expanded="false">Paid Visits (${paidVisits.size()})</a>
                        </li>
                    </ul>
                    <div class="tab-content"> 
                    <div class="tab-pane fade active in" id="scheduled-pills">    
                     <table class="table">    
                        <thead>
                            <tr>
                                <th>Doctor</th>
                                <th>Cycle</th>                            
                                <th>Status</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="schvisits" items="${userVisits}">
                            <tr>
                            <td><c:out value="${schvisits.doctor.firstName}"/> <c:out value="${schvisits.doctor.lastName}"/></td>
                            <td><c:out value="${schvisits.cycle.startDate}"/> - <c:out value="${schvisits.cycle.endDate}"/></td>
                            <td><c:out value="${schvisits.status}"/> (<c:out value="${schvisits.paidVisits.size()}"/>)</td>
                            </tr>
                        </c:forEach>
                       </tbody>
                     </table>
                    </div> 
                    <div class="tab-pane fade" id="paid-pills"> 
                        <table class="table">    
                        <thead>
                            <tr>
                                <th>Doctor</th>
                                <th>Cycle</th>                            
                                <th>Date</th>
                                <th>Hour</th>
                                <th>Week</th>
                                <th>Group</th>
                            </tr>
                        </thead>
                        <tbody> 
                             <c:forEach var="pvisits" items="${paidVisits}">
                            <tr>
                            <td><c:out value="${pvisits.scheduledVisit.doctor.firstName}"/> <c:out value="${pvisits.scheduledVisit.doctor.lastName}"/></td>
                            <td><c:out value="${pvisits.scheduledVisit.cycle.startDate}"/> - <c:out value="${pvisits.scheduledVisit.cycle.endDate}"/></td> 
                            <td><c:out value="${pvisits.date}"/></td>
                            <td><c:out value="${pvisits.hour}"/></td>
                            <td><c:out value="${pvisits.week}"/></td>
                            <td><c:out value="${pvisits.isGroup}"/></td>  
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
    <script src="<c:url value='/resources/js/users.js'/>" type="text/javascript"></script>
</content>
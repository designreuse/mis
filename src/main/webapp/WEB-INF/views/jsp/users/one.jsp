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
                        <h4>${user.firstName} ${user.lastName}
                        </h4> 
                        <h4><small>Username: ${user.username}</small></h4>


                        <c:forEach var="role" items="${user.roles}">
                            <h5>Role: ${role.publicName}</h5>  
                        </c:forEach>

                        <p><i class="fa fa-envelope"></i> <a href="mailto:${user.email}">${user.email}</a></p>

                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <div class="text-right">
                                <a href="<c:url value="/users/edit/${user.id}"/>">
                                    <button type="button" class="btn btn-success btn-40"><i class="fa fa-edit"></i></button>
                                </a>
                                <button type="button" class="btn btn-danger btn-40 deleteUser" data-id="${user.id}"><i class="fa fa-trash"></i></button>
                            </div>
                        </sec:authorize>
                    </div><!-- /.box-body -->
                </div>
                <div class="box box-info">
                    <div class="box-header with-border">
                        <h3 class="box-title">Group Membership</h3>
                    </div><!-- /.box-header -->
                    <div class="box-body">
                        <div class="table-responsive">
                            <c:if test="${not empty userGroups}">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>Group name</th>
                                            <th>Leader</th>
                                            <th>Members</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="group" items="${userGroups}">
                                            <tr>
                                                <td><c:out value="${group.name}"/></td>
                                                <td><c:out value="${group.leader.firstName}"/> <c:out value="${group.leader.lastName}"/> </td>
                                                <td>
                                                    <c:forEach var="user" items="${group.members}" varStatus="loop">
                                                        <c:choose>
                                                            <c:when test="${loop.index==0}">
                                                                <c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/>
                                                            </c:when>
                                                            <c:otherwise>
                                                                , <c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                </td>                                               
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:if> 
                            <c:if test="${empty userGroups}">  
                                ...
                            </c:if>
                        </div>
                    </div><!-- /.box-body -->
                </div> 
            </div> 
            <div class="col-md-8">
                <div class="box box-info">
                    <div class="box-header with-border">
                        <h3 class="box-title">Group Visits - Current Active Cycle</h3>
                    </div><!-- /.box-header -->
                    <div class="box-body">
                        <ul class="nav nav-pills">  
                            <li class="active">
                                <a href="#scheduledGroup-pills" data-toggle="tab" aria-expanded="true">Scheduled Visits (${newGroupVisits.size()})</a>
                            </li>
                            <li class>
                                <a href="#paidGroup-pills" data-toggle="tab" aria-expanded="false">Paid Visits (${groupVisits.size()})</a>
                            </li>
                        </ul>
                        <div class="tab-content"> 
                            <div class="tab-pane fade active in" id="scheduledGroup-pills">    
                                <table class="table">    
                                    <thead>
                                        <tr>
                                            <th>Group</th>
                                            <th>Doctor</th>
                                            <th>Cycle</th>                            
                                            <th>Status</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="schvisits" items="${newGroupVisits}">
                                            <tr>                                          
                                                <c:forEach var="gname" items="${schvisits.groups}">
                                                    <td><c:out value="${gname.name}"/></td>
                                                </c:forEach>
                                                <td><c:out value="${schvisits.doctor.firstName}"/> <c:out value="${schvisits.doctor.lastName}"/></td>
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
                                            <th>Doctor</th>
                                            <th>Cycle</th>                            
                                            <th>Date</th>
                                            <th>Hour</th>
                                            <th>Week</th>
                                        </tr>
                                    </thead>
                                    <tbody> 
                                        <c:forEach var="pvisits" items="${groupVisits}">
                                            <tr>
                                                <td><c:out value="${pvisits.scheduledVisit.doctor.firstName}"/> <c:out value="${pvisits.scheduledVisit.doctor.lastName}"/></td>
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
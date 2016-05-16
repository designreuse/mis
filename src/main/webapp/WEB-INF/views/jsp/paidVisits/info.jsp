<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication var="user" property="principal" />

<content tag="title">Pay Visit Info</content>

<content tag="content">
    <section class="content-header">     
    </section>
    <section class="content">

        <div class="row">
            <div class="col-md-12">
                <div class="box box-info">
                    <div class="box-header with-border">
                        <c:forEach var="visitor" items="${paidVisit.scheduledVisit.medicalVisitors}">  
                            <h3 class="box-title">Visit Details: For <c:out value="${visitor.firstName}"/> <c:out value="${visitor.lastName}"/> </h3>
                        </c:forEach> 
                        <c:forEach var="visitor" items="${paidVisit.scheduledVisit.medicalVisitors}">  
                            <h3 class="box-title">Visit Details: For <c:out value="${group.name}"/> </h3>
                        </c:forEach>     
                    </div><!-- /.box-header -->
                    <div class="box-body">
                        <form role="form" action="<c:url value="/paidVisits/${user.id}" />" method="GET">
                            <div class="row">
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Medical Visitor Name:</label><br/>
                                        <c:forEach var="visitor" items="${paidVisit.scheduledVisit.medicalVisitors}">
                                            <p><c:out value="${visitor.firstName}"/> <c:out value="${visitor.lastName}"/></p>
                                        </c:forEach>
                                        <c:forEach var="group" items="${paidVisit.scheduledVisit.groups}">
                                            <p><c:out value="${group.name}"/></p>
                                        </c:forEach>       
                                    </div>
                                </div>                            
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Doctor Name:</label><br/>
                                        <p>${paidVisit.scheduledVisit.doctor.firstName} ${paidVisit.scheduledVisit.doctor.lastName}</p>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Cycle:</label><br/>
                                        <p>${paidVisit.scheduledVisit.cycle.startDate} - ${paidVisit.scheduledVisit.cycle.endDate}</p>
                                    </div>
                                </div>                                 
                            </div> 
                            <div class="row">      
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Actual Date: </label><br/>
                                        <p>${paidVisit.date}</p>
                                    </div>
                                </div> 
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Hour: </label><br/>
                                        <p>${paidVisit.hour}</p>
                                    </div>
                                </div> 
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Week: </label><br/>
                                        <p>${paidVisit.week}</p>
                                    </div>
                                </div> 
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Group: </label><br/>
                                        <p>${paidVisit.isGroup}</p>
                                    </div>
                                </div>       
                            </div>  
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label>Comment: </label><br/>
                                        <p>${paidVisit.comments}</p>
                                    </div>
                                </div> 
                            </div>  
                            <button type="submit" class="btn btn-default">Back</button>         
                        </form>           
                    </div><!-- /.box-body -->
                </div>
            </div>
        </div>
    </section>
</content>

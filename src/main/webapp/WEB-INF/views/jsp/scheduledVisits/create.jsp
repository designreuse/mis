<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<content tag="title">Create new visits</content>

<content tag="content">
    <section class="content-header">
        <h1>
            Create New Visits
        </h1>
    </section>
    <section class="content">

        <div class="row">
            <div class="col-md-12">
                <div class="box box-info">
                    <div class="box-header with-border">
                        <h3 class="box-title">Create New Visit</h3>
                    </div><!-- /.box-header -->
                    <div class="box-body">
                        <ul class="nav nav-pills">  
                            <li class="active">
                                <a href="#individual-pills" data-toggle="tab" aria-expanded="true">New Individual Visit</a>
                            </li>
                            <li class>
                                <a href="#group-pills" data-toggle="tab" aria-expanded="false">New Group Visit</a>
                            </li>
                        </ul>
                        <div class="tab-content">
                        <div class="tab-pane fade active in" id="individual-pills">    
                        <form role="form" action="<c:url value="/scheduledVisits/store"/>" method="POST">
                            <div class="row">
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Medical Visitor:</label>
                                        <select name="medicalVisitorId" class="form-control">
                                            <c:forEach var="medVisitor" items="${visitors}">                     
                                                <option value="${medVisitor.id}">${medVisitor.firstName} ${medVisitor.lastName}</option>                                                   
                                            </c:forEach>
                                        </select>                                       
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Cycle</label>
                                        <select name="cycleId" id="cycleId" class="form-control">
                                            <option value="0">[-- select --]</option>
                                            <c:forEach var="cycle" items="${cycles}">
                                                <option value="${cycle.id}">${cycle.startDate} - ${cycle.endDate}</option>
                                            </c:forEach>
                                        </select>     
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Doctors</label>
                                        <select name="doctorId" id="doctorId" class="form-control" disabled>                                      
                                        </select>     
                                    </div>
                                </div>
                            </div>
                            <button type="submit" class="btn btn-default">Save</button>
                        </form>
                        </div>  
                        <div class="tab-pane fade" id="group-pills">    
                        <form role="form" action="<c:url value="/scheduledVisits/storeGroup"/>" method="POST">
                            <div class="row">
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Group Name:</label>
                                        <select name="groupVisitorId" class="form-control">
                                            <c:forEach var="group" items="${groups}">                     
                                                <option value="${group.id}">${group.name}</option>                                                   
                                            </c:forEach>
                                        </select>                                       
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Cycle</label>
                                        <select name="cycleIdGroup" id="cycleIdGroup" class="form-control">
                                            <option value="0">[-- select --]</option>
                                            <c:forEach var="cycle" items="${cycles}">
                                                <option value="${cycle.id}">${cycle.startDate} - ${cycle.endDate}</option>
                                            </c:forEach>
                                        </select>     
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Doctors</label>
                                        <select name="doctorIdGroup" id="doctorIdGroup" class="form-control" disabled>                                      
                                        </select>     
                                    </div>
                                </div>
                            </div>
                            <button type="submit" class="btn btn-default">Save</button>
                         </form>
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

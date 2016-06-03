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
                        <h3 class="box-title">Scheduled Visits List</h3>
                    </div><!-- /.box-header -->
                    <div class="box-body">   
                        <form role="form" action="<c:url value="/reports/byCycle"/>" method="POST">                                          <div class="row" style="margin-bottom:20px;">
                                <div class="col-md-3">
                                    <select name="userId" id="userId" class="form-control">
                                        <option value="0">[-- Select Medical Visitor --]</option>
                                        <c:forEach var="user" items="${userList}">
                                            <option value="${user.id}">Name: ${user.firstName} ${user.lastName}</option>
                                        </c:forEach>
                                    </select>     
                                </div>
                                <div class="col-md-3">
                                    <select name="cycleId" id="cycleId" class="form-control">
                                        <option value="0">[-- Select Cycle --]</option>
                                        <c:forEach var="cycle" items="${cyclesList}">
                                            <option value="${cycle.id}">Cycle: ${cycle.startDate} - ${cycle.endDate}</option>
                                        </c:forEach>
                                    </select>     
                                </div>
                                <div class="col-md-3">                     
                                    <button type="submit" class="btn btn-default">Select</button>
                                </div>
                            </div>
                        </form>
                        <div class="tab-content">
                            <div class="table-responsive">                                                                                                                                 
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>Visitor Name</th>
                                            <th>Doctors</th>
                                            <th>Paid Visits</th>
                                            <th>Coverage(%)</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>${user.firstName} ${user.lastName}</td>
                                            <td>${newVisits.size()}</td>
                                            <td>
                                                <ul style="list-style-type: none;">
                                                    <li>1st visits:${FirstPaidVisits.size()}</li>
                                                    <li>2nd visits:${SecondPaidVisits.size()}</li>
                                                    <li>3rd visits:${ExtraPaidVisits.size()}</li>
                                                    <li><b>Total visits:${totalCount}</b></li>
                                                </ul>
                                            </td>                          
                                            <td>
                                                <ul style="list-style-type: none;">
                                                    <li>Doctor Coverage(Sum of all visits): ${percentage} %</li>
                                                    <li>Doctor Coverage(1st visits): ${percentageFirst} %</li>
                                                </ul>
                                            </td>                                                                                            
                                        </tr>
                                        <c:if test="${not empty userGroups}">
                                            <tr>
                                                <td>Group Total</td>
                                                <td>${newGroupVisits.size()}</td>
                                                <td>
                                                    <ul style="list-style-type: none;">
                                                        <li>1st visits:${firstGroupVisits.size()}</li>
                                                        <li>2nd visits:${secondGroupVisits.size()}</li>
                                                        <li>3rd visits:${extraGroupVisits.size()}</li>
                                                        <li><b>Total visits:${groupTotalCount}</b></li>
                                                    </ul>
                                                </td>                          
                                                <td>
                                                    <ul style="list-style-type: none;">
                                                        <li>Doctor Coverage(Sum of all visits): ${groupPercentage} %</li>
                                                        <li>Doctor Coverage(1st visits): ${groupPercentageFirst} %</li>
                                                    </ul>
                                                </td>       
                                            </tr>
                                        </c:if> 
                                        <c:if test="${empty userGroups}">  
                                    </c:if>
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
    <script src="<c:url value='/resources/js/scheduledVisit.js'/>" type="text/javascript"></script>
</content>




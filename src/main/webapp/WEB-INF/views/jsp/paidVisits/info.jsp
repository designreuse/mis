<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<content tag="title">Pay Visit Info</content>

<content tag="content">
    <section class="content-header">     
    </section>
    <section class="content">

        <div class="row">
            <div class="col-md-12">
                <div class="box box-info">
                    <div class="box-header with-border">
                        <h3 class="box-title">Visit Details: For ${paidVisit.scheduledVisit.medicalVisitor.firstName} ${paidVisit.scheduledVisit.medicalVisitor.lastName} </h3>
                    </div><!-- /.box-header -->
                    <div class="box-body">
                        <form role="form" action="<c:url value="/PaidVisits/${paidVisit.scheduledVisit.medicalVisitor.id}" />" method="GET">
                            <div class="row">
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Medical Visitor Name:</label><br/>
                                        <label>${paidVisit.scheduledVisit.medicalVisitor.firstName} ${paidVisit.scheduledVisit.medicalVisitor.lastName}</label>
                                    </div>
                                </div>                            
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Doctor Name:</label><br/>
                                        <label>${paidVisit.scheduledVisit.doctor.firstName} ${paidVisit.scheduledVisit.doctor.lastName}</label>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Cycle:</label><br/>
                                        <label>${paidVisit.scheduledVisit.cycle.startDate} - ${paidVisit.scheduledVisit.cycle.endDate}</label>
                                    </div>
                                </div>                                 
                            </div> 
                             <div class="row">      
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Status: </label><br/>
                                        <label>${paidVisit.date}</label>
                                    </div>
                                </div> 
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Status: </label><br/>
                                        <label>${paidVisit.hour}</label>
                                    </div>
                                </div> 
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Status: </label><br/>
                                        <label>${paidVisit.week}</label>
                                    </div>
                                </div> 
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Status: </label><br/>
                                        <label>${paidVisit.isGroup}</label>
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


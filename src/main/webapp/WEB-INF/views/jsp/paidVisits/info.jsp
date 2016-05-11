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
                            <div class="row">
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Medical Visitor Name:</label><br/>
                                        <p>${paidVisit.scheduledVisit.medicalVisitor.firstName} ${paidVisit.scheduledVisit.medicalVisitor.lastName}</p>
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
                                        <label>Status: </label><br/>
                                        <p>${paidVisit.date}</p>
                                    </div>
                                </div> 
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Status: </label><br/>
                                        <p>${paidVisit.hour}</p>
                                    </div>
                                </div> 
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Status: </label><br/>
                                        <p>${paidVisit.week}</p>
                                    </div>
                                </div> 
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Status: </label><br/>
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
                    </div><!-- /.box-body -->
                </div>
            </div>
        </div>
    </section>
</content>

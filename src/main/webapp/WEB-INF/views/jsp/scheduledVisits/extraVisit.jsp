<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication var="user" property="principal" />
<div class="modal fade" id="extraVisit" tabindex="-1" role="dialog" aria-labelledby="extraVisit">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Note extra visit</h4>
            </div>
            <form role="form" action="<c:url value='/extraVisits/store/${user.id}'/>" method="POST">
                <div class="modal-body">
                    <p>In case you couldn't reach the doctor, note down the visit's info.</p>
                    <p class="text-danger"><span class="extraVisitsNum"></span> unsuccessful visits.</p>
                    <input type="hidden" name="scheduledVisitId"/>
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>Visit Date</label>
                                <input name="date" type="text" class="form-control date">                                  
                            </div>
                            <div class="form-group">
                                <label>Hour</label>                                          
                                <select name="time" class="form-control">                                         
                                    <option value="AM">AM</option>
                                    <option value="PM">PM</option>                                            
                                </select>  
                            </div>
                        </div>
                        <div class="col-md-8">
                            <div class="form-group">
                                <label>Comments</label>
                                <textarea name="comments" type="text" class="form-control"> </textarea>                                        
                            </div>
                        </div>
                    </div> 
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Save</button>
                </div>
            </form>
        </div>
    </div>
</div>
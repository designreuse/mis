<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<content tag="title">Create new group</content>

<content tag="content">
    <section class="content-header">
        <h1>
            Create New Group
        </h1>
    </section>
    <section class="content">

        <div class="row">
            <div class="col-md-12">
                <div class="box box-info">
                    <div class="box-header with-border">
                        <h3 class="box-title">Create New Group</h3>
                    </div><!-- /.box-header -->
                    <div class="box-body">
                        <form role="form" action="<c:url value="/groups/store"/>" method="POST">
                            <div class="row">
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Group Name:</label>
                                        <input name="groupName" class="form-control" required data-fv-notempty-message="The group name is required">
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Leader:</label>
                                        <select name="leaderId" id="leaderId" class="form-control" required>
                                            <option value="0">[-- select --]</option>
                                            <c:forEach var="medVisitor" items="${medicalVisitors}">                     
                                                <option value="${medVisitor.id}">${medVisitor.firstName} ${medVisitor.lastName}</option>                                                   
                                            </c:forEach>
                                        </select>                                       
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Members</label>
                                        <select name="members[]" id="groupMembers" class="form-control" multiple="multiple">
                                            <c:forEach var="medVisitor" items="${medicalVisitors}">
                                                <option value="${medVisitor.id}">${medVisitor.firstName} ${medVisitor.lastName}</option>
                                            </c:forEach>
                                        </select>     
                                    </div>
                                </div>
                            </div>
                            <button type="submit" class="btn btn-default">Save</button>
                        </form>
                    </div><!-- /.box-body -->
                </div>
            </div>
        </div>
    </section>
</content>

<content tag="footerScripts">
    <script src="<c:url value='/resources/js/groups.js'/>" type="text/javascript"></script>
</content>

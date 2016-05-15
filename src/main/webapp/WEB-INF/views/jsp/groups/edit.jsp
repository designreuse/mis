<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<content tag="title">Edit group</content>

<content tag="content">
    <section class="content-header">
        <h1>
            Edit group
        </h1>
    </section>
    <section class="content">

        <div class="row">
            <div class="col-md-12">
                <div class="box box-info">
                    <div class="box-header with-border">
                        <h3 class="box-title">Edit Group</h3>
                    </div><!-- /.box-header -->
                    <div class="box-body">
                        <form role="form" action="<c:url value="/groups/update"/>" method="POST">
                            <input type="hidden" name="_method" value="put" />
                            <input type="hidden" name="id" value="${group.id}">
                            <div class="row">
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Group Name:</label>
                                        <input name="groupName" class="form-control" value="${group.name}"  required data-fv-notempty-message="The group name is required">
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Leader:</label>
                                        <select name="leaderId" id="leaderId" class="form-control" required>
                                            <option value="0">[-- select --]</option>
                                            <c:forEach var="medVisitor" items="${medicalVisitors}">                     
                                                <option value="${medVisitor.id}" ${medVisitor.id == group.leader.id ? 'selected="selected"' : ''}>${medVisitor.firstName} ${medVisitor.lastName}</option>                                                   
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
                                                <c:set var="found" scope="page" value="false"/>
                                                <c:forEach var="groupMember" items="${group.members}">
                                                    <c:if test="${groupMember.id==medVisitor.id}">
                                                        <c:set var="found" scope="page" value="true"/>
                                                    </c:if>                                                    
                                                </c:forEach>
                                                <option value="${medVisitor.id}" ${found==true ? 'selected="selected"' : ''}>${medVisitor.firstName} ${medVisitor.lastName}</option>
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

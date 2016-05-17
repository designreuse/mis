<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<content tag="title">View groups</content>

<content tag="content">
    <section class="content-header">
        <h1>
            View Groups
        </h1>
    </section>
    <section class="content">

        <div class="row">
            <div class="col-md-12">
                <div class="box box-info">
                    <div class="box-header with-border">
                        <h3 class="box-title">Groups</h3>
                    </div><!-- /.box-header -->
                    <div class="box-body">
                        <div class="table-responsive">
                            <c:if test="${not empty groups}">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Group name</th>
                                            <th>Leader</th>
                                            <th>Members</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="group" items="${groups}">
                                            <tr>
                                                <td><c:out value="${group.id}"/></td>
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
                                                <td>
                                                    <a href="<c:url value="/groups/edit/${group.id}"/>" class="btn btn-success">
                                                        <i class="fa fa-edit"></i>
                                                    </a>
                                                    <button type="button" class="btn btn-danger deleteGroup" data-id="${group.id}"><i class="fa fa-trash"></i></button>    
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:if> 
                            <c:if test="${empty groups}">  
                                <div class="alert alert-warning">The are no available records to view!</div>
                            </c:if>
                        </div>
                    </div><!-- /.box-body -->
                </div>
            </div>
        </div>
    </section>
</content>
<content tag="footerScripts">
    <script src="<c:url value='/resources/js/groups.js'/>" type="text/javascript"></script>
</content>


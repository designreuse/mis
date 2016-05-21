<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<content tag="title">Reports</content>

<content tag="content">
    <section class="content-header">
        <h1>
            Reports
        </h1>
    </section>
    <section class="content">

        <div class="row">
            <div class="col-md-4 byGeolocation ">
                <div class="box box-info">
                    <div class="box-header with-border">
                        <h3 class="box-title">Geographical Area by Medical Visitor</h3>
                    </div><!-- /.box-header -->
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-6 pull-right" style="margin-bottom:10px;">
                                <select name="medicalVisitorId" id="medicalVisitorId" class="form-control">
                                    <c:forEach var="medicalVisitor" items="${medicalVisitors}" varStatus="loop">
                                        <option value="${medicalVisitor.id}" ${loop.index==0 ? 'selected' :''}>${medicalVisitor.firstName} ${medicalVisitor.lastName}</option>
                                    </c:forEach>
                                </select> 
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <p class="noData" style="display:none;">There are not scheduled visits for the selected medical visitor</p>
                                <canvas id="byGeolocation"></canvas                   
                            </div>
                        </div>
                    </div><!-- /.box-body -->
                </div>
            </div>
        </div>
    </section>
</content>

<content tag="footerScripts">
    <script src="<c:url value='/resources/js/reports.js'/>" type="text/javascript"></script>
</content>                                   
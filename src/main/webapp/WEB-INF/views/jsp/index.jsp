<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<content tag="title">Dashboard</content>

<content tag="content">

    <section class="content-header">
        <h1>
            Dashboard
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Info boxes -->
        <div class="row">
            <div class="col-md-3 col-sm-6 col-xs-12">
                <div class="info-box">
                    <span class="info-box-icon bg-aqua"><i class="fa fa-users"></i></span>
                    <div class="info-box-content">
                        <span class="info-box-text">Medical Visitors</span>
                        <span class="info-box-number">${medicalVisitors}</span>
                    </div><!-- /.info-box-content -->
                </div><!-- /.info-box -->
            </div><!-- /.col -->
            <div class="col-md-3 col-sm-6 col-xs-12">
                <div class="info-box">
                    <span class="info-box-icon bg-red"><i class="fa fa-user-md"></i></span>
                    <div class="info-box-content">
                        <span class="info-box-text">Doctors</span>
                        <span class="info-box-number">${doctors}</span>
                    </div><!-- /.info-box-content -->
                </div><!-- /.info-box -->
            </div><!-- /.col -->

            <!-- fix for small devices only -->
            <div class="clearfix visible-sm-block"></div>

            <div class="col-md-3 col-sm-6 col-xs-12">
                <div class="info-box">
                    <span class="info-box-icon bg-green"><i class="fa fa-clock-o"></i></span>
                    <div class="info-box-content">
                        <span class="info-box-text">Scheduled Visits</span>
                        <span class="info-box-number">${scheduledVisits}</span>
                    </div><!-- /.info-box-content -->
                </div><!-- /.info-box -->
            </div><!-- /.col -->
            <div class="col-md-3 col-sm-6 col-xs-12">
                <div class="info-box">
                    <span class="info-box-icon bg-yellow"><i class="fa fa-clock-o"></i></span>
                    <div class="info-box-content">
                        <span class="info-box-text">Paid Visits</span>
                        <span class="info-box-number">${paidVisits}</span>
                    </div><!-- /.info-box-content -->
                </div><!-- /.info-box -->
            </div><!-- /.col -->
        </div><!-- /.row -->


    </section><!-- /.content -->

</content>

<content tag="footerScripts">
    <script src="<c:url value='/resources/theme/dist/js/pages/dashboard2.js'/>" type="text/javascript"></script>
</content>
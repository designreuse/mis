<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication var="user" property="principal" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title><sitemesh:write property='page.title'/> | Mediculus Admin Panel</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>

            <link href="<c:url value='/resources/theme/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css" />
            <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
            <link href="<c:url value='/resources/theme/plugins/jvectormap/jquery-jvectormap-1.2.2.css'/>" rel="stylesheet" type="text/css" />
            <link href="<c:url value='/resources/theme/dist/css/AdminLTE.min.css'/>" rel="stylesheet" type="text/css" />
            <link href="<c:url value='/resources/theme/dist/css/skins/_all-skins.min.css'/>" rel="stylesheet" type="text/css" />
            <link href="<c:url value='/resources/theme/plugins/datepicker/datepicker3.css'/>" rel="stylesheet" type="text/css" />
            <link href="<c:url value='/resources/theme/plugins/dropzone/dropzone.css'/>" rel="stylesheet" type="text/css" />
            <link href="<c:url value='/resources/theme/plugins/select2/select2.min.css'/>" rel="stylesheet" type="text/css" />
            <link href="<c:url value='/resources/css/custom.css'/>" rel="stylesheet" type="text/css" />

            <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
            <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
            <!--[if lt IE 9]>
                <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
                <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
            <![endif]-->

    </head>
    <body class="sidebar-mini skin-blue" data-url="<c:url value='/${user.id}'/>">
        <div class="wrapper">
            <!-- Left side column. contains the logo and sidebar -->
            <aside class="main-sidebar">
                <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar">
                    <!-- Sidebar user panel -->
                    <div class="user-panel">
                        <img src="<c:url value='/resources/img/logo.png' />" style="width:50%;"/>
                    </div>
                    <!-- sidebar menu: : style can be found in sidebar.less -->
                    <ul class="sidebar-menu">                      
                        <li class="header">WELCOME <span class="text-uppercase">${user.username}</span> <span class="pull-right"><a href="<c:url value='/'/>logout">LOGOUT</a></span></li>       
                        <li class="treeview">
                            <a href="<c:url value='/'/>">
                                <i class="fa fa-dashboard"></i> <span>Dashboard</span> 
                            </a>
                        </li>
                        <li class="treeview">
                            <a href="#">
                                <i class="fa fa-user-md"></i>
                                <span>Doctors</span>  <i class="fa fa-angle-right pull-right"></i>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href="<c:url value='/doctors/'/>"><i class="fa fa-circle-o"></i> View doctors</a></li>
                                <li><a href="<c:url value='/doctors/create'/>"><i class="fa fa-circle-o"></i> Create doctor</a></li>
                            </ul>
                        </li>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <li class="treeview">
                                <a href="#">
                                    <i class="fa fa-users"></i>
                                    <span>Medical visitors</span>  <i class="fa fa-angle-right pull-right"></i>
                                </a>
                                <ul class="treeview-menu">
                                    <li><a href="<c:url value='/users/'/>"><i class="fa fa-circle-o"></i> View medical visitors</a></li>
                                    <li><a href="<c:url value='/users/create'/>"><i class="fa fa-circle-o"></i> Create medical visitor</a></li>
                                    <li><a href="<c:url value='/groups/'/>"><i class="fa fa-circle-o"></i> View medical groups</a></li>
                                    <li><a href="<c:url value='/groups/create'/>"><i class="fa fa-circle-o"></i> Create medical group</a></li>
                                </ul>
                            </li>                                          
                            <li class="treeview">
                                <a href="#">
                                    <i class="fa fa-clock-o"></i>
                                    <span>Visits Management</span>  <i class="fa fa-angle-right pull-right"></i>
                                </a>
                                <ul class="treeview-menu">                             
                                    <li><a href="<c:url value='/scheduledVisits/allCycles'/>"><i class="fa fa-circle-o"></i> Assigned Visits</a></li>                               
                                    <li><a href="<c:url value='/paidVisits/allCycles'/>"><i class="fa fa-circle-o"></i> Paid Visits</a></li>  
                                    <li><a href="<c:url value='/scheduledVisits/create'/>"><i class="fa fa-circle-o"></i> New Visit</a></li>
                                </ul>
                            </li>                    
                            <li class="treeview">
                                <a href="#">
                                    <i class="fa fa-calendar"></i>
                                    <span>Cycles</span>  <i class="fa fa-angle-right pull-right"></i>
                                </a>
                                <ul class="treeview-menu">
                                    <li><a href="<c:url value='/cycles/'/>"><i class="fa fa-circle-o"></i> View All Cycles</a></li>
                                    <li><a href="<c:url value='/cycles/create'/>"><i class="fa fa-circle-o"></i> Create New Cycle</a></li>

                                </ul>
                            </li>
                        </sec:authorize>  
                        <sec:authorize access="hasRole('ROLE_MEDICAL_VISITOR')">
                            <li class="treeview">
                                <a href="#">
                                    <i class="fa fa-clock-o"></i>
                                    <span>My Scheduled Visits</span>  <i class="fa fa-angle-right pull-right"></i>
                                </a>
                                <ul class="treeview-menu">
                                    <li><a href="<c:url value='/scheduledVisits/${user.id}'/>"><i class="fa fa-circle-o"></i>View Assigned Visits</a></li>     
                                    <li><a href="<c:url value='/paidVisits/${user.id}'/>"><i class="fa fa-circle-o"></i>View Paid Visits</a></li>
                                </ul>
                            </li>
                        </sec:authorize>
                        <li class="treeview">
                            <a href="<c:url value='/reports/'/>">
                                <i class="fa fa-bar-chart"></i> <span>Reports</span> 
                            </a>
                        </li>
                    </ul>
                </section>
                <!-- /.sidebar -->
            </aside>
            <div class="content-wrapper">
                <sitemesh:write property='page.content'/>                
            </div>
        </div>

        <!-- Footer Scripts -->
        <script src="<c:url value='/resources/theme/plugins/jQuery/jQuery-2.1.4.min.js'/>"></script>
        <script src="<c:url value='/resources/theme/bootstrap/js/bootstrap.min.js'/>" type="text/javascript"></script>
        <script src="<c:url value='/resources/theme/dist/js/app.min.js'/>" type="text/javascript"></script>
        <script src="<c:url value='/resources/theme/plugins/sparkline/jquery.sparkline.min.js'/>" type="text/javascript"></script>
        <script src="<c:url value='/resources/theme/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js'/>" type="text/javascript"></script>
        <script src="<c:url value='/resources/theme/plugins/jvectormap/jquery-jvectormap-world-mill-en.js'/>" type="text/javascript"></script>
        <script src="<c:url value='/resources/theme/plugins/slimScroll/jquery.slimscroll.min.js'/>" type="text/javascript"></script>
        <script src="<c:url value='/resources/theme/plugins/chartjs/Chart.min.js'/>" type="text/javascript"></script>
        <script src="<c:url value='/resources/theme/plugins/datepicker/bootstrap-datepicker.js'/>" type="text/javascript"></script>
        <script src="<c:url value='/resources/theme/plugins/dropzone/dropzone.js'/>" type="text/javascript"></script>
        <script src="<c:url value='/resources/theme/plugins/select2/select2.min.js'/>" type="text/javascript"></script>
        <script src="<c:url value='/resources/js/custom.js'/>" type="text/javascript"></script>

        <sitemesh:write property='page.footerScripts'/>

    </body>
</html>
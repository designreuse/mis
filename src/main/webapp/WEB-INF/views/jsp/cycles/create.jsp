<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="now" value="<%=new java.util.Date()%>" />

<content tag="title">Create Cycle</content>

<content tag="content">
    <section class="content-header">
        <h1>
            Create Cycle
        </h1>
    </section>
    <section class="content">

        <div class="row">
            <div class="col-md-12">
                <div class="box box-info">
                    <div class="box-header with-border">
                        <h3 class="box-title">Create Cycle</h3>
                    </div><!-- /.box-header -->
                    <div class="box-body">
                        <form role="form" action="<c:url value="/cycles/store" />" method="POST">
                            <div class="row">
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>From Date</label>
                                        <input name="fromDate" type="text" class="form-control date">                                           
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>To Date</label>
                                        <input name="toDate" type="text" class="form-control date">                                           
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
    <script type="text/javascript">
        $('.date').datepicker({
            format: "yyyy-mm-dd",
            startDate: "<fmt:formatDate pattern="yyyy-MM-dd" value="${now}"/>"
        });
    </script>
</content>                                   
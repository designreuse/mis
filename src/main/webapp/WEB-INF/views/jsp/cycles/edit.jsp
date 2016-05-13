<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<content tag="title">Edit cycle</content>

<content tag="content">
    <section class="content-header">
        <h1>
            Edit cycle
        </h1>
    </section>
    <section class="content">

        <div class="row">
            <div class="col-md-12">
                <div class="box box-info">
                    <div class="box-header with-border">
                        <h3 class="box-title">Edit user</h3>
                    </div><!-- /.box-header -->
                    <div class="box-body">
                        <form role="form" action="<c:url value="/cycles/update" />" method="POST">
                            <input type="hidden" name="_method" value="put" />
                            <div class="row">
                                <input type="hidden" name="id" value="${cycle.id}">
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>From Date:</label>
                                        <input name="fromDate" value="${cycle.startDate}" class="form-control">
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>To Date:</label>
                                        <input name="toDate" value="${cycle.endDate}" class="form-control">
                                    </div>
                                </div>                                    
                            </div>
                    </div>                                
                    <button type="submit" class="btn btn-default">Update</button>
                    </form>
                </div><!-- /.box-body -->
            </div>
        </div>
        </div>
    </section>
</content>

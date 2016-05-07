<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<content tag="title">Create doctor</content>

<content tag="content">
    <section class="content-header">
        <h1>
            Create doctor
        </h1>
    </section>
    <section class="content">

        <div class="row">
            <div class="col-md-12">
                <div class="box box-info">
                    <div class="box-header with-border">
                        <h3 class="box-title">Create doctor</h3>
                    </div><!-- /.box-header -->
                    <div class="box-body">
                        <form role="form" action="<c:url value="/doctors/store"/>" method="POST">
                            <div class="row">
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>First Name:</label>
                                        <input name="firstName" class="form-control">
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Last Name:</label>
                                        <input name="lastName" class="form-control">
                                    </div>
                                </div>                                    
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Email:</label>
                                        <input name="email" class="form-control">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Address:</label>
                                        <input name="address" class="form-control">
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Geographical Area:</label>
                                        <select name="geolocationAreaId" id="geolocationAreaId" class="form-control">
                                            <option value="0">[-- select --]</option>
                                            <c:forEach var="area" items="${geolocationAreas}">
                                                <option value="${area.id}">${area.name}</option>
                                            </c:forEach>
                                        </select>                                       
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>City</label>
                                        <select name="cityId" id="cityId" class="form-control" disabled>
                                        </select>     
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Phone:</label>
                                        <input name="phone" class="form-control">
                                    </div>
                                </div>

                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Position:</label>
                                        <input name="position" class="form-control">
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Institution</label>
                                        <select name="institutionId" id="institutionId" class="form-control" disabled>
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
    <script src="<c:url value='/resources/js/doctors.js'/>" type="text/javascript"></script>
</content>
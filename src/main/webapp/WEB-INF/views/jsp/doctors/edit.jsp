<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<content tag="title">Edit doctor</content>

<content tag="content">
    <section class="content-header">
        <h1>
            Edit doctor
        </h1>
    </section>
    <section class="content">

        <div class="row">
            <div class="col-md-12">
                <div class="box box-info">
                    <div class="box-header with-border">
                        <h3 class="box-title">Edit doctor</h3>
                    </div><!-- /.box-header -->
                    <div class="box-body">
                        <form role="form" action="<c:url value="/doctors/update"/>" method="POST">
                            <input type="hidden" name="_method" value="put" />
                            <input type="hidden" name="id" value="${doctor.id}">
                            <div class="row">
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label>First Name:</label>
                                        <input name="firstName" class="form-control" value="${doctor.firstName}">
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label>Last Name:</label>
                                        <input name="lastName" class="form-control" value="${doctor.lastName}">
                                    </div>
                                </div>                                    
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label>Email:</label>
                                        <input name="email" class="form-control" value="${doctor.email}">
                                    </div>
                                </div>
                                    
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label>Phone:</label>
                                        <input name="phone" class="form-control" value="${doctor.phone}">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label>Address:</label>
                                        <input name="address" class="form-control" value="${doctor.address}">
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label>Geographical Area:</label>
                                        <select name="geolocationAreaId" id="geolocationAreaId" class="form-control" >
                                            <option value="0">[-- select --]</option>
                                            <c:forEach var="area" items="${geolocationAreas}">
                                                <option value="${area.id}" ${area.id == doctor.geolocationArea.id ? 'selected="selected"' : ''}>${area.name}</option>
                                            </c:forEach>
                                        </select>                                       
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label>City</label>
                                        <select name="cityId" id="cityId" class="form-control">
                                            <c:forEach var="city" items="${doctor.geolocationArea.cities}">
                                                <option value="${city.id}" ${area.id == doctor.city.id ? 'selected="selected"' : ''}>${city.name}</option>
                                            </c:forEach>
                                        </select>    
                                    </div>
                                </div>
                            </div>

                            <div class="row">                                
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label>Specialty</label>
                                        <select name="specialtyId" id="specialtyId" class="form-control">
                                            <c:forEach var="specialty" items="${doctorSpecialties}">
                                                <option value="${specialty.id}" ${specialty.id == doctor.specialty.id ? 'selected="selected"' : ''}>${specialty.name}</option>
                                            </c:forEach>
                                        </select>    
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label>Position:</label>
                                        <input name="position" class="form-control" value="${doctor.position}">
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label>Institution</label>
                                        <select name="institutionId" id="institutionId" class="form-control">
                                            <c:forEach var="institution" items="${doctor.geolocationArea.institutions}">
                                                <option value="${institution.id}" ${institution.id == doctor.institution.id ? 'selected="selected"' : ''}>${institution.name}</option>
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
    <script src="<c:url value='/resources/js/doctors.js'/>" type="text/javascript"></script>
</content>
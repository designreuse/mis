<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<content tag="title">View all doctors</content>

<content tag="content">
    <section class="content-header">
        <h1>
            View all doctors
        </h1>
    </section>
    <section class="content">

        <div class="row">
            <div class="col-md-12">
                <div class="box box-info">
                    <div class="box-header with-border">
                        <h3 class="box-title">Search doctors</h3>                       
                    </div><!-- /.box-header -->
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-2">
                                <input name="firstName" id="firstName" class="form-control" placeholder="First name"/>
                            </div>
                            <div class="col-md-2">
                                <input name="lastName" id="lastName" class="form-control" placeholder="Last name"/>
                            </div>
                            <div class="col-md-2">
                                <input name="address" id="address" class="form-control" placeholder="Address"/>
                            </div>
                            <div class="col-md-2">
                                <input name="phone" id="phone" class="form-control" placeholder="Phone"/>
                            </div>
                            <div class="col-md-2">
                                <input name="email" id="email" class="form-control" placeholder="Email"/>
                            </div>
                            <div class="col-md-2">
                                <input name="position" id="position" class="form-control" placeholder="Position"/>
                            </div>
                        </div>
                        <div class="row" style="margin-top:8px;">
                            <div class="col-md-2">
                                <select name="geolocationAreaId" id="geolocationAreaId" class="form-control">
                                    <option value="0" selected="selected">Geolocation Area</option>
                                    <c:forEach var="area" items="${geolocationAreas}">
                                        <option value="${area.id}">${area.name}</option>
                                    </c:forEach>
                                </select>   
                            </div>                            
                            <div class="col-md-2">
                                <select name="cityId" id="cityId" class="form-control" disabled>
                                     <option value="0" selected="selected">City</option>
                                </select>  
                            </div>
                            <div class="col-md-2">
                                <select name="institutionId" id="institutionId" class="form-control" disabled>
                                     <option value="0" selected="selected">Institution</option>
                                </select>  
                            </div>
                            <div class="col-md-2">
                                <select name="specialtyId" id="specialtyId" class="form-control">
                                    <option value="0" selected="selected">Specialty</option>
                                    <c:forEach var="specialty" items="${doctorSpecialties}">
                                        <option value="${specialty.id}">${specialty.name}</option>
                                    </c:forEach>
                                </select>   
                            </div>
                            <div class="col-md-2">
                                <button class="btn btn-info" id="search"><i class="fa fa-search"></i></button>
                            </div>
                        </div>                        
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <div class="box box-info">
                    <div class="box-header with-border">
                        <h3 class="box-title">View all doctors</h3>                       
                    </div><!-- /.box-header -->
                    <div class="box-body">
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Name</th>
                                        <th>Email</th>
                                        <th>Position</th>
                                        <th>Institution</th>
                                        <th>Specialty</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="doctor" items="${doctors}">
                                        <tr>
                                            <td><c:out value="${doctor.id}"/></td>
                                            <td><c:out value="${doctor.firstName}"/> <c:out value="${doctor.lastName}"/></td>
                                            <td><c:out value="${doctor.email}"/></td>
                                            <td><c:out value="${doctor.position}"/></td>
                                            <td><c:out value="${doctor.institution.name}"/></td>
                                            <td><c:out value="${doctor.specialty.name}"/></td>
                                            <td>
                                                <a href="<c:url value="/doctors/one/${doctor.id}"/>" class="btn btn-info btn-sm btn-30">
                                                    <i class="fa fa-eye"></i>
                                                </a>
                                                <a href="<c:url value="/doctors/edit/${doctor.id}"/>">
                                                    <button type="button" class="btn btn-sm btn-success btn-30"><i class="fa fa-edit"></i></button>
                                                </a>
                                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                                    <button type="button" class="btn btn-sm btn-danger btn-30 deleteDoctor" data-id="${doctor.id}"><i class="fa fa-trash"></i></button>
                                                    </sec:authorize>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div><!-- /.box-body -->
                </div>
            </div>
        </div>
    </section>
</content>

<content tag="footerScripts">
    <script src="<c:url value='/resources/js/doctors.js'/>" type="text/javascript"></script>
</content>
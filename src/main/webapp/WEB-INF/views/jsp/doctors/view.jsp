<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
                        <h3 class="box-title">View all doctors</h3>
                    </div><!-- /.box-header -->
                    <div class="box-body">
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Name</th>
                                        <th>Address</th>
                                        <th>Phone</th>
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
                                            <td><c:out value="${doctor.address}"/>, 
                                                <c:out value="${doctor.city.name}"/>, 
                                                <c:out value="${doctor.geolocationArea.name}"/></td>
                                            <td><c:out value="${doctor.phone}"/></td>
                                            <td><c:out value="${doctor.email}"/></td>
                                            <td><c:out value="${doctor.position}"/></td>
                                            <td><c:out value="${doctor.institution.name}"/></td>
                                            <td><c:out value="${doctor.specialty.name}"/></td>
                                            <td>
                                                <a href="<c:url value="/doctors/edit/${doctor.id}"/>">
                                                    <button type="button" class="btn btn-info"><i class="fa fa-edit"></i></button>
                                                </a>
                                                <a href="<c:url value="/doctors/delete/${doctor.id}"/>">
                                                    <button typ e="button" class="btn btn-danger"><i class="fa fa-trash"></i></button>
                                                </a>
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

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
                                                 <button type="button" class="btn btn-sm btn-danger btn-30 deleteDoctor" data-id="${doctor.id}"><i class="fa fa-trash"></i></button>
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
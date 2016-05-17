<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<content tag="title">View doctor</content>

<content tag="content">
    <section class="content-header">
        <h1>
            View doctor
        </h1>
    </section>
    <section class="content">

        <div class="row">
            <div class="col-md-4">
                <div class="box box-info">
                    <div class="box-header with-border">
                        <h3 class="box-title">Info</h3>
                    </div><!-- /.box-header -->
                    <div class="box-body">
                        <h4>${doctor.firstName} ${doctor.lastName}
                            <small>${doctor.specialty.name}</small>
                        </h4>

                        <h5>${doctor.position} at ${doctor.institution.name}</h5>

                        <p><i class="fa fa-map-marker"></i> ${doctor.address}, ${doctor.city.name}, ${doctor.geolocationArea.name} </p>
                        <p><i class="fa fa-phone"></i> ${doctor.phone}</p>
                        <p><i class="fa fa-envelope"></i> <a href="mailto:${doctor.email}">${doctor.email}</a></p>

                        <div class="text-right">
                            <c:if test="${doctor.editable}">
                                <a href="<c:url value="/doctors/edit/${doctor.id}"/>">
                                    <button type="button" class="btn btn-success btn-40"><i class="fa fa-edit"></i></button>
                                </a>
                            </c:if>
                            <c:if test="${doctor.deletable}">
                                <button type="button" class="btn btn-danger btn-40 deleteDoctor" data-id="${doctor.id}"><i class="fa fa-trash"></i></button>
                                </c:if>
                        </div>
                    </div><!-- /.box-body -->
                </div>
            </div>
            <div class="col-md-8">
                <div class="box box-info">
                    <div class="box-header with-border">
                        <h3 class="box-title">Visits etc</h3>
                    </div><!-- /.box-header -->
                    <div class="box-body">
                        ...

                    </div><!-- /.box-body -->
                </div>
            </div>
        </div>
    </section>
</content>

<content tag="footerScripts">
    <script src="<c:url value='/resources/js/doctors.js'/>" type="text/javascript"></script>
</content>
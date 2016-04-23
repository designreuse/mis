<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>View all doctors</title>
    </head>
    <body>
        <section class="content-header">
            <h1>
                View all doctors
            </h1>
        </section>


        <c:forEach var="doctor" items="${doctors}">
            Doctor: <c:out value="${doctor.firstName}"/> <c:out value="${doctor.lastName}"/>
        </c:forEach>

    </body>

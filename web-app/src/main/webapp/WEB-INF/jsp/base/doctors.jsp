<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
    <%@ page isELIgnored="false" %>
    <link href="<c:url value="/resources/css/doctors.css" />" rel="stylesheet" type="text/css" />
    <jsp:include page="searchbar.jsp" />

</head>
<body class="d-flex flex-column h-100">
<div class="container marketing" id="doctors">
    <div class="row">
        <c:choose>
            <c:when test="${!empty doctorClinics}">
                <c:forEach var="doctorClinic" items="${doctorClinics}">
                    <div class="col-lg-4 single-doctor">
                        <svg class="bd-placeholder-img rounded-circle" width="140" height="140" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: 140x140">
                            <title>
                                ::before
                                "Placeholder"
                                ::after
                            </title>
                            <rect width="100%" height="100%" fill="#777"></rect>
                            <text x="50%" y="50%" fill="#777" dy=".3em">140x140</text>
                        </svg>
                        <h3>${doctorClinic.doctor.firstName} ${doctorClinic.doctor.lastName}</h3>
                        <p>${doctorClinic.doctor.specialty.specialtyName}</p>
                        <p>${doctorClinic.clinic.name}</p>
                        <p><a class="btn btn-secondary" href="/results/${doctorClinic.clinic.id}/${doctorClinic.doctor.license}" role="button">View Details</a></p>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <p><spring:message code="no.results.found"/></p>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>

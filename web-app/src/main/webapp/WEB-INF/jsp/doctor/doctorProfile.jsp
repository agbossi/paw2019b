<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="format" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <%@ page isELIgnored="false" %>
        <jsp:include page="../base/navbar.jsp" />
        <link href="<c:url value="/resources/css/doctorProfile.css" />" rel="stylesheet" type="text/css" />
    </head>
    <body class="d-flex flex-column h-100">
        <div class="doctor-index-container">
                <c:choose>
                    <c:when test="${empty doctorClinics}">
                        <div class="new-doctor-header">
                            <spring:message code="still.no.clinic.subscription"/>&nbsp;
                            <a href="<c:url value="/doctor/addDoctorClinic"/>"><spring:message code="here"/></a>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="doctor-clinics-container">
                            <h2 class="doctor-index-clinic-header"><spring:message code="your.clinics"/></h2>
                            <div class="doctor-index-clinic-container">
                                <c:forEach var="docClinic" items="${doctorClinics}">
                                    <div class="clinic-card">
                                        <a href="<c:url value="/doctor/clinics/${docClinic.clinic.id}/1"/>"><h5><c:out value="${docClinic.clinic.name}"/></h5></a>
                                        <p><c:out value="${docClinic.clinic.location.locationName}"/></p>
                                        <p>(<c:out value="${docClinic.clinic.address}"/>)</p>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
        </div>
    </body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
    <head>
        <%@ page isELIgnored="false" %>
        <jsp:include page="../base/navbar.jsp" />

    </head>
    <body class="d-flex flex-column h-100">
        <h2>Doctor Information</h2>
        <body class="d-flex flex-column h-100">
        <div class="container marketing" id="doctors">
            <div class="row">
                <c:choose>
                    <c:when test="${empty doctorClinics}">
                        <spring:message code="still.no.clinic.subscription"/>&nbsp;<a href="<c:out value="/doctor/addDoctorClinic"/>"><spring:message code="here"/></a>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="doctorClinic" items="${doctorClinics}">
                                <h3><c:out value="${doctorClinic.doctor.firstName}"/> <c:out value="${doctorClinic.doctor.lastName}"/></h3>
                                <p><c:out value="${doctorClinic.doctor.specialty.specialtyName}"/></p>
                                <p><c:out value="${doctorClinic.clinic.name}"/> (<c:out value="${doctorClinic.clinic.location.locationName}"/>)</p>
                                <p><a class="btn btn-secondary" href="<c:url value="/doctor/addSchedule/${doctorClinic.clinic.id}/${doctorClinic.doctor.license}"/>" role="button">
                                    <spring:message code="add.week.schedule"/>
                                </a>
                                </p>
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        </body>
        </div>
    </body>
</html>
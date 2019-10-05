<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="format" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <%@ page isELIgnored="false" %>
        <jsp:include page="../base/navbar.jsp" />
        <link href="<c:url value="/resources/css/appointments.css" />" rel="stylesheet" type="text/css" />
    </head>
        <div>
            <c:choose>
                <c:when test="${empty doctorClinic}">
                    <spring:message code="still.no.clinic.subscription"/>&nbsp;<a href="<c:url value="/doctor/addDoctorClinic"/>"><spring:message code="here"/></a>
                </c:when>
                <c:otherwise>
                    <c:if test="${empty schedule}">
                        <spring:message code="still.no.schedule"/>&nbsp;<a href="<c:url value="/doctor/addSchedule"/>"><spring:message code="here"/></a>
                    </c:if>
                </c:otherwise>
            </c:choose>
        </div>
        <br/>
        <div>
            <h4><b><spring:message code="appointments"/></b></h4>
            <c:choose>
                <c:when test="${empty appointments}">
                    <spring:message code="no.appointments" />
                </c:when>
                <c:otherwise>
                    <c:forEach var="appointment" items="${appointments}">
                        <div class="app-div col-sm">
                            <h6><format:formatDate value="${appointment.date.getTime()}" type="date" pattern="EEEE dd/MM/yyyy HH:mm"/></h6>
                            <h6><b><spring:message code="a.withpatient"/></b></h6>
                            <h6><b><spring:message code="a.patientname" /></b> <c:out value="${appointment.patient.lastName}"/>, <c:out value="${appointment.patient.firstName}"/></h6>
                            <h6><b><spring:message code="user.email"/></b> <c:out value="${appointment.patient.email}"/></h6>
                            <h6><spring:message code="a.at"/> <b>${appointment.doctorClinic.clinic.name} (${appointment.doctorClinic.clinic.location.locationName})</b></h6>
                            <a class="btn btn-outline-primary" href="<c:url value="/docCancelApp/${appointment.doctorClinic.clinic.id}/${appointment.patient.email}/${appointment.date.get(1)}-${appointment.date.get(2)}-${appointment.date.get(5)}-${appointment.date.get(11)}"/>"><spring:message code="a.cancel"/></a>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>

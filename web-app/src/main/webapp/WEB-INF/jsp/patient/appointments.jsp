<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="format" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
    <head>
        <%@ page isELIgnored="false" %>
        <jsp:include page="../base/navbar.jsp" />
        <link href="<c:url value="/resources/css/listAppointments.css" />" rel="stylesheet" type="text/css" />
    </head>
    <body class="list-items-body">
        <div class="list-container">
            <c:choose>
                <c:when test="${not empty patient.appointments}">
                    <div class="header-info">
                        <div class="header-block">
                            <h5><spring:message code="your.appointments.information"/></h5>
                        </div>
                    </div>
                    <div class="listed-items">
                        <c:forEach var="appointment" items="${patient.appointments}">
                            <div class="item-card">
                                <div class="appointment-information">
                                    <p><format:formatDate value="${appointment.appointmentKey.date.getTime()}" type="date" pattern="EEEE dd/MM/yyyy HH:mm"/>
                                    <div><b><spring:message code="a.withdr"/> <c:out value="${appointment.doctorClinic.doctor.lastName}"/>, <c:out value="${appointment.doctorClinic.doctor.firstName}"/></b></div>
                                    <div>
                                        <spring:message code="a.at"/> <c:out value="${appointment.doctorClinic.clinic.name}"/>
                                    </div>
                                    <div>
                                        <c:out value="${appointment.doctorClinic.clinic.location.locationName}"/> (<c:out value="${appointment.doctorClinic.clinic.address}"/>)
                                    </div>
                                    </p>
                                </div>
                                <div class="cancel-appointment-button">
                                    <a class="cancel-button btn btn-outline-primary" href="<c:url value="/cancelApp/${appointment.doctorClinic.clinic.id}/${appointment.doctorClinic.doctor.license}/${appointment.appointmentKey.date.get(1)}-${appointment.appointmentKey.date.get(2)}-${appointment.appointmentKey.date.get(5)}-${appointment.appointmentKey.date.get(11)}"/>"><spring:message code="a.cancel"/></a>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="header-info">
                        <spring:message code="no.appointments"/>
                    </div>
                    <div>
                        <a href=<c:url value="/"/>><spring:message code="go.back.home"/></a>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>

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
    <body>
        <div class="container mycont">
            <div class="row">
                <div class="col-6">
                    <h2><spring:message code="account.header"/></h2>
                    <div>
                        <h4><b><spring:message code="user.first.name"/></b></h4>
                        <h6><c:out value="${user.firstName}"/>  </h6>
                    </div>
                    <div>
                        <h4><b><spring:message code="user.last.name"/></b></h4>
                        <h6><c:out value="${user.lastName}"/></h6>
                    </div>
                    <div>
                        <h4><b><spring:message code="user.email"/></b></h4>
                        <h6><c:out value="${user.email}"/></h6>
                    </div>
                </div>
                <div class="col-6">
                    <h2><spring:message code="doctor.header"/></h2>
                    <div>
                        <h4><b><spring:message code="doctor.license"/></b></h4>
                        <h6><c:out value="${doctor.license}"/></h6>
                    </div>
                    <div>
                        <h4><b><spring:message code="doctor.specialty"/></b></h4>
                        <h6><c:out value="${doctor.specialty.specialtyName}"/></h6>
                    </div>
                    <div>
                        <h4><b><spring:message code="doctor.phone.number"/></b></h4>
                        <h6><c:out value="${doctor.phoneNumber}"/></h6>
                    </div>
                    <div>
                        <c:choose>
                            <c:when test="${not empty image}">
                                <img class="card-img-top" src="<c:url value="/images/${doctor.license}"/>" alt="Doctor Profile Image">
                            </c:when>
                            <c:otherwise>
                                You still haven't upload an profile picture.
                                To do so click <a href="/doctor/uploadPicture">here</a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>

        <div>
            <h4><b><spring:message code="appointments"/></b></h4>
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
        </div>
    </body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="format" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
    <head>
        <%@ page isELIgnored="false" %>
        <jsp:include page="../base/navbar.jsp" />
    </head>
    <body>
        <c:forEach var="appointment" items="${patient.appointments}">
            <p><format:formatDate value="${appointment.date.getTime()}" type="date" pattern="EEEE dd/MM/yyyy HH:mm"/>
            <div><b><spring:message code="a.withdr"/> <c:out value="${appointment.doctorClinic.doctor.lastName}"/>, <c:out value="${appointment.doctorClinic.doctor.firstName}"/></b></div>
            <div>
                <spring:message code="a.at"/>
            </div>
            <div>
                <div><c:out value="${appointment.docClinic.clinic.name}"/></div>
                <div>(<c:out value="${appointment.docClinic.clinic.location.locationName}"/>)</div>
                <div>(<c:out value="${appointment.docClinic.clinic.address}"/>)</div>
            </div>
                <a class="btn btn-outline-primary" href="<c:url value="/cancelApp/${appointment.doctorClinic.clinic.id}/${appointment.doctorClinic.doctor.license}/${appointment.date.get(1)}-${appointment.date.get(2)}-${appointment.date.get(5)}-${appointment.date.get(11)}"/>"><spring:message code="a.cancel"/></a>
            </p>
        </c:forEach>
    </body>
</html>
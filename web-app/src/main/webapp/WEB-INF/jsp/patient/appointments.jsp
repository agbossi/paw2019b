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
            <p><format:formatDate value="${appointment.date.getTime()}" type="date" pattern="dd/MM/yyyy HH:mm"/>
                <spring:message code="a.withdr"/> ${appointment.doctorClinic.doctor.lastName}, ${appointment.doctorClinic.doctor.firstName} <spring:message code="a.at"/> ${appointment.doctorClinic.clinic.name} (${appointment.doctorClinic.clinic.location.locationName})</p>
        </c:forEach>
    </body>
</html>
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
                <spring:message code="a.withdr"/> ${appointment.doctorClinic.doctor.lastName}, ${appointment.doctorClinic.doctor.firstName} <spring:message code="a.at"/> ${appointment.doctorClinic.clinic.name} (${appointment.doctorClinic.clinic.location.locationName})
                <a class="btn btn-outline-primary" href="<c:url value="/cancelApp/${appointment.doctorClinic.clinic.id}/${appointment.doctorClinic.doctor.license}/${appointment.date.get(1)}-${appointment.date.get(2)}-${appointment.date.get(5)}-${appointment.date.get(11)}"/>"><spring:message code="a.cancel"/></a>
            </p>
        </c:forEach>
    </body>
</html>
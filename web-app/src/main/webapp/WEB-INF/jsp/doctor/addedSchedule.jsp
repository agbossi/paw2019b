<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
    <head>
        <%@ page isELIgnored="false" %>
        <jsp:include page="../base/navbar.jsp" />
    </head>
    <body class="d-flex flex-column h-100">
        <h2><spring:message code="schedule.added"/></h2>
        <a class="nav-link" href="<c:url value="/doctor/addSchedule/${doctorClinic.clinic.id}/${doctorClinic.doctor.license}"/>"><spring:message code="add.another.schedule"/></a>
    </body>
</html>
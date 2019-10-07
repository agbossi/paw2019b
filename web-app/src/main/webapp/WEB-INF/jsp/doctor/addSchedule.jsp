<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
    <head>
        <%@ page isELIgnored="false" %>
        <jsp:include page="../base/navbar.jsp" />
        <link href="<c:url value="/resources/css/doctors.css" />" rel="stylesheet" type="text/css" />
    </head>
    <body class="d-flex flex-column h-100">
        <h2><spring:message code="w.clinic"/></h2>
        <div class="container">
            <c:choose>
                <c:when test="${empty doctorClinics}">
                    <spring:message code="still.no.clinic.subscription"/>&nbsp;<a href="<c:out value="/doctor/addDoctorClinic"/>"><spring:message code="here"/></a>
                </c:when>
                <c:otherwise>
                    <c:forEach var="doctorClinic" items="${doctorClinics}">
                        <div>
                            <h5><c:out value="${doctorClinic.clinic.name}"/></h5>
                            <div><c:out value="${doctorClinic.clinic.address}"/></div>
                            <div>(<c:out value="${doctorClinic.clinic.location.locationName}"/>)</div>
                            <p>
                                <a class="btn btn-secondary" href="<c:url value="/doctor/addSchedule/${doctorClinic.clinic.id}"/>" role="button">
                                <spring:message code="add.week.schedule"/>
                                </a>
                            </p>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>
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
    <body class="d-flex flex-column h-100">
            <c:choose>
                <c:when test="${empty doctorClinics}">
                    <div class="container">
                        <spring:message code="still.no.clinic.subscription"/>&nbsp;<a href="<c:url value="/doctor/addDoctorClinic"/>"><spring:message code="here"/></a>
                    </div>
                    </c:when>
                <c:otherwise>
                    <h2><spring:message code="your.clinics"/></h2>
                    <div class="container">
                        <c:forEach var="docClinic" items="${doctorClinics}">
                            <div>
                                <h5><c:out value="${docClinic.clinic.name}"/> (<c:out value="${docClinic.clinic.location.locationName}"/>)&nbsp;<a href="<c:url value="/doctor/clinics/${docClinic.clinic.id}/1"/>"><spring:message code="view.clinic"/></a></h5>
                            </div>
                            </c:forEach>
                    </div>
                </c:otherwise>
            </c:choose>
    </body>
</html>

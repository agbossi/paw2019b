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
        <div>
            <c:choose>
                <c:when test="${empty doctorClinics}">
                    <spring:message code="still.no.clinic.subscription"/>&nbsp;<a href="<c:url value="/doctor/addDoctorClinic"/>"><spring:message code="here"/></a>
                </c:when>
                <c:otherwise>
                    <h4><spring:message code="your.clinics"/></h4>
                    <c:forEach var="docClinic" items="${doctorClinics}">
                        <p><c:out value="${docClinic.clinic.name}"/> (<c:out value="${docClinic.clinic.location.locationName}"/>)&nbsp;<a href="<c:url value="/doctor/clinics/${docClinic.clinic.id}/1"/>"><spring:message code="view.clinic"/></a></p>
                        </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>

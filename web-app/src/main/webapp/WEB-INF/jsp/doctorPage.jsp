<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="format" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fc" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
    <head>
        <%@ page isELIgnored="false" %>
        <jsp:include page="base/navbar.jsp" />
        <link href="<c:url value="/resources/css/indexDoctorResult.css" />" rel="stylesheet" type="text/css" />
    </head>
    <body class="d-flex flex-column h-100">
        <div class="doctor-information">
            <h4>
                <b><c:out value="${doctor.firstName}"/> <c:out value="${doctor.lastName}"/></b>
                <sec:authorize access="hasRole('ROLE_USER')">
                    <c:if test="${isFav}">
                        <a href="<c:out value="/deleteFavorite/${doctor.license}"/>"><img src="<c:url value="/resources/images/yesfav.png" />"></a>
                    </c:if>
                    <c:if test="${!isFav}">
                        <a href="<c:out value="/addFavorite/${doctor.license}"/>"><img src="<c:url value="/resources/images/nofav.png" />"></a>
                    </c:if>
                </sec:authorize>
            </h4>
            <div class="doctor-profile">
                <h6><b><spring:message code="license"/></b> <c:out value="${doctor.license}"/></h6>
                <h6><b><spring:message code="specialty"/></b>  <c:out value="${doctor.specialty.specialtyName}"/></h6>
                <h6><b><spring:message code="phone.number"/></b>  <c:out value="${doctor.phoneNumber}"/></h6>
                <h6><b><spring:message code="user.email"/></b>  <c:out value="${doctor.email}"/></h6>
            </div>
        </div>
            <div class="doctor-index-container">
                <div class="doctor-clinics-container">
                    <h6 class="doctor-index-clinic-header"><spring:message code="check.doctor.clinic.schedule"/></h6>
                    <div class="doctor-index-clinic-container">
                        <c:forEach var="dClinic" items="${doctorClinics}">
                            <div class="clinic-card">
                                    <a href="<c:url value="/results/${doctor.license}/${dClinic.clinic.id}/1"/>"><c:out value="${dClinic.clinic.name}"/></a>
                                <p><c:out value="${dClinic.clinic.location.locationName}"/></p>
                                <p>(<c:out value="${dClinic.clinic.address}"/>)</p>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
    </body>
</html>




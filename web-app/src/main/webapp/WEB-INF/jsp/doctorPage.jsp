<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="format" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fc" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <%@ page isELIgnored="false" %>
        <jsp:include page="base/navbar.jsp" />
        <link href="<c:url value="/resources/css/doctorDetails.css" />" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <div class="doctor-information">
            <h2><c:out value="${doctor.firstName}"/> <c:out value="${doctor.lastName}"/></h2>
            <div class="doctor-profile">
                <ul class="list-group">
                    <li class="list-group-item">
                        <h6><b><spring:message code="license"/></b> <c:out value="${doctor.license}"/></h6>
                        <h6><b><spring:message code="specialty"/></b>  <c:out value="${doctor.specialty.specialtyName}"/></h6>
                        <h6><b><spring:message code="phone.number"/></b>  <c:out value="${doctor.phoneNumber}"/></h6>
                        <h6><b><spring:message code="user.email"/></b>  <c:out value="${doctor.email}"/></h6>
                    </li>
                </ul>
            </div>
        </div>
        <div class="doctor-clinics">
            <br/>
            <spring:message code="check.doctor.clinic.schedule"/>
            <br/>
            <br/>
            <br/>
            <c:forEach var="dClinic" items="${doctorClinics}">
                <div>
                    <h5>
                        <a href="<c:url value="/results/${doctor.license}/${dClinic.clinic.id}/1"/>"><c:out value="${dClinic.clinic.name}"/></a>
                        <br/>(<c:out value="${dClinic.clinic.location.locationName}"/>)
                        <br/>
                    </h5>
                </div>
                <br/><br/>
            </c:forEach>
        </div>
    </body>
</html>


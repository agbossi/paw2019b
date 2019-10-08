<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
    <head>
        <%@ page isELIgnored="false" %>
        <jsp:include page="../base/navbar.jsp" />
        <link href="<c:url value="/resources/css/doctor.css" />" rel="stylesheet" type="text/css" />
    </head>
    <body class="doctor-clinic-added-body">
        <div class="doctor-clinic-added-container">
            <div>
                <p><h5><spring:message code="doctor.added.to.clinic"/></h5></p>
                <p><b><spring:message code="clinic"/></b></p>
                <p><c:out value="${doctorClinic.clinic.name}"/></p>
                <p><b><spring:message code="consult.price"/></b></p>
                <p><c:out value="${doctorClinic.consultPrice}"/></p>
            </div>
            <div>
                <a class="nav-link" href="<c:url value="/doctor/addDoctorClinic"/>"><spring:message code="add.another.doctor.to.clinic"/></a>
            </div>
            <div>
                <a class="nav-link" href="<c:url value="/"/>"><spring:message code="go.back.home"/></a>
            </div>
        </div>
    </body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
    <head>
        <%@ page isELIgnored="false" %>
        <jsp:include page="../base/navbar.jsp" />
        <link href="<c:url value="/resources/css/adminAdded.css" />" rel="stylesheet" type="text/css" />
    </head>
    <body class="added-body">
    <div class="added-container">
        <div>
            <p><h5><spring:message code="doctor.added"/></h5></p>
            <p><b><spring:message code="user.first.name"/></b></p>
            <p><c:out value="${doctor.firstName}"/></p>
            <p><b><spring:message code="user.last.name"/></b></p>
            <p><c:out value="${doctor.lastName}"/></p>
            <p><b><spring:message code="specialty"/></b></p>
            <p><c:out value="${doctor.specialty.specialtyName}"/></p>
            <p><b><spring:message code="license"/></b></p>
            <p><c:out value="${doctor.license}"/></p>
            <p><b><spring:message code="user.email"/></b></p>
            <p><c:out value="${doctor.email}"/></p>
            <p><b><spring:message code="profile.picture"/></b></p>
            <svg class="rounded-circle"
                 width="140" height="140"
                 xmlns="http://www.w3.org/2000/svg"
                 preserveAspectRatio="xMidYMid slice"
                 focusable="false" role="img" aria-label="Placeholder: 140x140">
                <rect width="100%" height="100%" fill="#777"></rect>
                <image width="100%" height="100%" xlink:href="<c:url value="/images/${doctor.license}"/>" clip-path="url(#circleView)" />
            </svg>
        </div>
        <div>
            <a class="nav-link" href="<c:url value="/admin/addDoctor"/>"><spring:message code="add.another.doctor"/></a>
        </div>
        <div>
            <a class="nav-link" href="<c:url value="/admin/doctors"/>"><spring:message code="go.back.to.list"/></a>
        </div>
    </div>
    </body>
</html>



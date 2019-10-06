<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
    <%@ page isELIgnored="false" %>
    <link href="<c:url value="/resources/css/doctors.css" />" rel="stylesheet" type="text/css" />
    <jsp:include page="searchbar.jsp" />

</head>
<body class="d-flex flex-column h-100">
<div class="container marketing " id="doctors">
    <div class="row">
        <c:if test="${!empty doctors}">
            <c:forEach var="doctor" items="${doctors}">
                <div class="col-lg-4 single-doctor">
                    <svg class="rounded-circle"
                         width="140" height="140"
                         xmlns="http://www.w3.org/2000/svg"
                         preserveAspectRatio="xMidYMid slice"
                         focusable="false" role="img" aria-label="Placeholder: 140x140">
                        <rect width="100%" height="100%" fill="#777"></rect>
                        <image width="100%" height="100%" xlink:href="<c:url value="/images/${doctor.license}"/>" clip-path="url(#circleView)" />
                    </svg>
                    <h3>${doctor.firstName} ${doctor.lastName}</h3>
                    <p>${doctor.specialty.specialtyName}</p>
                    <p><a class="btn btn-secondary" href="<c:url value="/results/${doctor.license}"/>" role="button"><spring:message code="view.details"/></a></p>
                </div>
            </c:forEach>
        </c:if>
    </div>
</div>
</body>

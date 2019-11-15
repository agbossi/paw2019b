<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="format" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
    <head>
        <%@ page isELIgnored="false" %>
        <jsp:include page="../base/navbar.jsp" />
        <link href="<c:url value="/resources/css/doctors.css" />" rel="stylesheet" type="text/css" />
    </head>
    <body class="list-items-body">
    <div class="container marketing " id="doctors">
        <div class="doctor-row">
            <c:choose>
                <c:when test="${!empty doctors}">
                    <c:forEach var="doctor" items="${doctors}">
                        <div class="single-doctor">
                            <img class="doctor-picture" src="<c:url value="/images/${doctor.license}"/>" onerror="this.onerror=null;this.src='<c:url value="/resources/images/docpic.jpg" />';"/>
                            <h3>
                                <a class="text-dark" href="<c:url value="/results/${doctor.license}"/>">
                                    <c:out value="${doctor.firstName}"/> <c:out value=" ${doctor.lastName}"/></a>
                            </h3>
                            <p>${doctor.specialty.specialtyName}</p>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <h3><spring:message code="no.results.found"/></h3>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    </body>
</html>

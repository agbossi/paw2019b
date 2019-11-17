<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="format" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
    <head>
        <%@ page isELIgnored="false" %>
        <jsp:include page="../base/navbar.jsp" />
        <link href="<c:url value="/resources/css/doctors.css" />" rel="stylesheet" type="text/css" />
        <link href="<c:url value="/resources/css/favorites.css" />" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <div>
            <c:choose>
                <c:when test="${!empty docotrs}">
                    <div class="header-info">
                        <div class="header-block">
                            <h5 class="header-msg"><spring:message code="your.favorites.information"/></h5>
                        </div>
                    </div>
                    <div class="doctor-row">
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
                    <div class="doctor-row">
                </c:when>
                <c:otherwise>
                    <div class="header-info">
                        <div class="header-block">
                            <h5 class="header-msg"><spring:message code="no.favorites.found"/></h5>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>

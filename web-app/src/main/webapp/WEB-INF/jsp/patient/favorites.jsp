<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="format" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
    <head>
        <%@ page isELIgnored="false" %>
        <jsp:include page="../base/navbar.jsp" />
        <link href="<c:url value="/resources/css/doctors.css" />" rel="stylesheet" type="text/css" />
        <link href="<c:url value="/resources/css/listAppointments.css" />" rel="stylesheet" type="text/css" />
        <link href="<c:url value="/resources/css/favorites.css" />" rel="stylesheet" type="text/css" />
    </head>
    <body class="list-items-body">
        <div class="list-container">
            <c:choose>
                <c:when test="${not empty doctors}">
                    <div class="header-info">
                        <div class="header-block">
                            <h4 class="header-msg"><spring:message code="your.favorites.information"/></h4>
                        </div>
                    </div>
                    <div class="doctor-row">
                    <c:forEach var="doctor" items="${doctors}">
                        <div class="doctor-box">
                            <div class="single-doctor">
                                <img class="doctor-picture" src="<c:url value="/images/${doctor.license}"/>" onerror="this.onerror=null;this.src='<c:url value="/resources/images/docpic.jpg" />';"/>
                                <h3>
                                    <a class="text-dark" href="<c:url value="/results/${doctor.license}"/>">
                                        <c:out value="${doctor.firstName}"/> <c:out value=" ${doctor.lastName}"/></a>
                                </h3>
                                <p>${doctor.specialty.specialtyName}</p>
                            </div>
                            <div class="delete-box">
                                <b class="delete-element">
                                    <a href="<c:out value="/deleteFavorite/${doctor.license}"/>">
                                        <input type="submit" value="Remove from favorites" name="Remove from favorites" onclick="return confirmSubmit()">
                                    </a>
                                </b>
                            </div>
                        </div>
                    </c:forEach>
                    <div class="doctor-row">
                </c:when>
                <c:otherwise>
                    <div class="header-info">
                        <h4><spring:message code="no.favorites.found"/></h4>
                        <br/>
                        <a href=<c:url value="/"/>><spring:message code="go.back.home"/></a>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>

<script>
    function confirmSubmit()
    {
        var agree=confirm("Are you sure you want to remove this doctor from favorites?");
        if (agree)
            return true ;
        else
            return false ;
    }
</script>
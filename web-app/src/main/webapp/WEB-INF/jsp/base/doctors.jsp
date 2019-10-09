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
            <div class="doctor-row">
                <c:if test="${!empty doctors}">
                    <c:forEach var="doctor" items="${doctors}">
                        <div class="single-doctor">
                            <img class="doctor-picture" src="<c:url value="/images/${doctor.license}"/>"/>
                            <h3><a class="text-dark" href="<c:url value="/results/${doctor.license}"/>"><c:out value="${doctor.firstName}"/> <c:out value=" ${doctor.lastName}"/></a></h3>
                            <p>${doctor.specialty.specialtyName}</p>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
        </div>
    </body>
</html>

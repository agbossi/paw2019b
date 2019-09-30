<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <%@ page isELIgnored="false" %>
    <jsp:include page="base/navbar.jsp" />
</head>
<body>
    <h2><spring:message code="account.header"/></h2>
        <div>
            <p><b><spring:message code="user.first.name"/></b></p>
            <p>${user.firstName}</p>
        </div>
        <div>
            <p><b><spring:message code="user.last.name"/></b></p>
            <p>${user.lastName}</p>
        </div>
        <div>
            <p><b><spring:message code="user.email"/></b></p>
            <p>${user.email}</p>
        </div>

    <c:choose>
        <c:when test="${not empty patient.prepaid}">
            <h2><spring:message code="patient.header"/></h2>
            <div>
                <p><b><spring:message code="patient.prepaid"/></b></p>
                <p>${patient.prepaid}</p>
            </div>
            <div>
                <p><b><spring:message code="patient.prepaid.number"/></b></p>
                <p>${patient.prepaidNumber}</p>
            </div>
        </c:when>
        <c:otherwise>
        </c:otherwise>

    </c:choose>


    <!-- <a class="btn btn-outline-primary" href="/editProfile"><spring:message code="edit.personal"/></a> -->
</body>
</html>

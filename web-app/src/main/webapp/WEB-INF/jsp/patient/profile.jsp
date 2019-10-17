<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <%@ page isELIgnored="false" %>
    <jsp:include page="../base/navbar.jsp" />
    <link href="<c:url value="/resources/css/listAppointments.css" />" rel="stylesheet" type="text/css" />
</head>
    <body class="list-items-body">
    <div class="list-container">
        <div class="header-info">
            <div class="header-block">
                <h5><spring:message code="account.header"/></h5>
            </div>
        </div>
        <div class="listed-items">
            <div class="item-card personal-item-card">
                <p><b><spring:message code="user.first.name"/></b></p>
                <p>${user.firstName}</p>
            </div>
            <div class="item-card personal-item-card">
                <p><b><spring:message code="user.last.name"/></b></p>
                <p>${user.lastName}</p>
            </div>
            <div class="item-card personal-item-card">
                <p><b><spring:message code="user.email"/></b></p>
                <p>${user.email}</p>
            </div>
            <c:choose>
                <c:when test="${not empty patient.prepaid}">
                    <div class="item-card personal-item-card">
                        <p><b><spring:message code="patient.prepaid"/></b></p>
                        <p>${patient.prepaid}</p>
                    </div>
                    <div class="item-card personal-item-card">
                        <p><b><spring:message code="patient.prepaid.number"/></b></p>
                        <p>${patient.prepaidNumber}</p>
                    </div>
                </c:when>
                <c:otherwise>
                </c:otherwise>
            </c:choose>
        </div>
        <div>
            <a class="edit-patient btn btn-outline-primary" href="<c:url value="/editProfile"/>"><spring:message code="edit.personal"/></a>
        </div>
    </div>
    </body>
</html>


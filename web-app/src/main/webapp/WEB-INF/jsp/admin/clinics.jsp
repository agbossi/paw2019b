<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <%@ page isELIgnored="false" %>
    <jsp:include page="../base/navbar.jsp" />
    <link href="<c:url value="/resources/css/adminInfo.css" />" rel="stylesheet" type="text/css" />
</head>
<body class="admin-info-body">
<div class="admin-info-header">
    <h5>
        <a class="p-2 text-dark" href="<c:url value="/admin/addClinic"/>">
            <b><spring:message code="add.clinic"/></b>
        </a>
    </h5>
</div>
<div class="admin-info-container">
    <c:forEach var="clinic" items="${clinics}">
        <div>
            <h6><c:out value="${clinic.name}"/>, <c:out value="${clinic.address}"/> (<c:out value="${clinic.location.locationName}"/>)</h6>
            <h6><!--
                <b class="delete-element">
                <a href="<c:url value="/admin/deleteClinic/${clinic.id}"/>">
                        <spring:message code="remove"/>
                    </b>
                </a>
                -->
            </h6>
        </div>
    </c:forEach>
</div>
</body>
</html>
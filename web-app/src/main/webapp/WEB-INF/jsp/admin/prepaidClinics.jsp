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
        <a class="p-2 text-dark" href="<c:url value="/admin/addPrepaidToClinic"/>">
            <b><spring:message code="add.prepaid.to.clinic"/></b>
        </a>
    </h5>
</div>
<div class="admin-info-container">
    <c:forEach var="prepaidClinic" items="${prepaidClinics}">
        <div>
            <h6><c:out value="${prepaidClinic.prepaid.name}"/>, <c:out value="${prepaidClinic.clinic.name}"/></h6>
            <h6>
                <a href="<c:url value="/admin/delete/${prepaidClinic.prepaid.name}/${prepaidClinic.clinic.id}"/>">
                    <b class="delete-element">
                        <spring:message code="remove"/>
                    </b>
                </a>
            </h6>
        </div>
    </c:forEach>
</div>
</body>
</html>

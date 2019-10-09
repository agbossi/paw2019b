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
                <p><h5><spring:message code="clinic.added"/></h5></p>
                <p><b><spring:message code="clinic"/></b></p>
                <p><c:out value="${clinic.name}"/></p>
                <p><b><spring:message code="address"/></b></p>
                <p><c:out value="${clinic.address}"/></p>
                <p><b><spring:message code="location"/></b></p>
                <p><c:out value="${clinic.location.locationName}"/></p>
            </div>
            <div>
                <a class="nav-link" href="<c:url value="/admin/addClinic"/>"><spring:message code="add.another.clinic"/></a>
            </div>
            <div>
                <a class="nav-link" href="<c:url value="/admin/clinics"/>"><spring:message code="go.back.to.list"/></a>
            </div>
        </div>
    </body>
</html>



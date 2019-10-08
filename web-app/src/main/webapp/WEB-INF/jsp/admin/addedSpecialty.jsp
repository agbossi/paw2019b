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
                <p><h5><spring:message code="specialty.added"/></h5></p>
                <p><b><spring:message code="specialty"/></b></p>
                <p><c:out value="${specialty.specialtyName}"/></p>
            </div>
            <div>
                <a class="nav-link" href="<c:url value="/admin/addSpecialty"/>"><spring:message code="add.another.specialty"/></a>
            </div>
            <div>
                <a class="nav-link" href="<c:url value="/"/>"><spring:message code="go.back.home"/></a>
            </div>
        </div>
    </body>
</html>


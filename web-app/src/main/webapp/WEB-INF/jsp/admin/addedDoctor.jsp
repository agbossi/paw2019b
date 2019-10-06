<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
    <head>
        <%@ page isELIgnored="false" %>
        <jsp:include page="../base/navbar.jsp" />

    </head>
    <body class="d-flex flex-column h-100">
        <div>
            <h2><spring:message code="doctor.added"/></h2>
        </div>
        <div>
            <c:out value="${doctor.firstName}"/>
        </div>
        <div>
            <c:out value="${doctor.lastName}"/>
        </div>
        <div>
            <c:out value="${doctor.specialty.specialtyName}"/>
        </div>
        <div>
            <c:out value="${doctor.license}"/>
        </div>
        <div>
            <c:out value="${doctor.email}"/>
        </div>
        <a class="nav-link" href="<c:url value="/admin/addDoctor"/>"><spring:message code="add.another.doctor"/></a>
    </body>
</html>

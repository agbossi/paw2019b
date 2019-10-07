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
        <svg class="rounded-circle"
             width="140" height="140"
             xmlns="http://www.w3.org/2000/svg"
             preserveAspectRatio="xMidYMid slice"
             focusable="false" role="img" aria-label="Placeholder: 140x140">
            <rect width="100%" height="100%" fill="#777"></rect>
            <image width="100%" height="100%" xlink:href="<c:url value="/images/${doctor.license}"/>" clip-path="url(#circleView)" />
        </svg>
        <a class="nav-link" href="<c:url value="/admin/addDoctor"/>"><spring:message code="add.another.doctor"/></a>
    </body>
</html>

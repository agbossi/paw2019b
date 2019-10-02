<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
    <head>
        <%@ page isELIgnored="false" %>
        <jsp:include page="base/navbar.jsp" />

    </head>
    <body class="d-flex flex-column h-100">
        <div>
            <h2><spring:message code="doctor.added"/></h2>
        </div>
        <div>
            ${doctor.firstName}
        </div>
        <div>
            ${doctor.lastName}
        </div>
        <div>
            ${doctor.specialty.specialtyName}
        </div>
        <div>
            ${doctor.license}
        </div>
        <div>
            ${doctor.email}
        </div>
        <a class="nav-link" href="/admin/addDoctor"><spring:message code="add.another.doctor"/></a>
    </body>
</html>

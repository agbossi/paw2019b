<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
    <head>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <jsp:include page="admin/admin.jsp" />
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_DOCTOR')">
            <jsp:include page="doctor/doctorProfile.jsp" />
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_USER')">
            <jsp:include page="base/doctors.jsp" />
        </sec:authorize>
        <sec:authorize access="isAnonymous()">
            <jsp:include page="base/doctors.jsp" />
        </sec:authorize>
    </head>
    <body>
    </body>
</html>

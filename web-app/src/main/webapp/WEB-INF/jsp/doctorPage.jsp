<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
    <head><head>
        <%@ page isELIgnored="false" %>
        <jsp:include page="base/navbar.jsp" />
    </head>
    <body>
        <h2><c:out value="${doctor.name}"/></h2>
        <ul class="list-group">
            <li class="list-group-item">
                <h6>License: <c:out value="${doctor.license}"/></h6>
                <h6>Location: <c:out value="${doctor.location.locationName}"/></h6>
                <h6>Specialty: <c:out value="${doctor.specialty.specialtyName}"/></h6>
                <h6>Phone Number: <c:out value="${doctor.phoneNumber}"/></h6>
            </li>
        </ul>
    </body>
</html>


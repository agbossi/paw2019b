
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <%@ page isELIgnored="false" %>
    <jsp:include page="base/adminnavbar.jsp" />
</head>
<body>
    <h2><spring:message code="account.header"/></h2>
    <div>
        <p><b><spring:message code="user.first.name"/></b></p>
        <p>${user.firstName}</p>
    </div>
    <div>
        <p><b><spring:message code="user.last.name"/></b></p>
        <p>${user.lastName}</p>
    </div>
    <div>
        <p><b><spring:message code="user.email"/></b></p>
        <p>${user.email}</p>
    </div>
    <h2><spring:message code="doctor.header"/></h2>
    <div>
        <p><b><spring:message code="doctor.license"/></b></p>
        <p>${doctor.license}</p>
    </div>
    <div>
        <p><b><spring:message code="doctor.specialty"/></b></p>
        <p>${doctor.specialty}</p>
    </div>
    <div>
        <p><b><spring:message code="doctor.phone.number"/></b></p>
        <p>${doctor.phoneNumber}</p>
    </div>
</body>
</html>

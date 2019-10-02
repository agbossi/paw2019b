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
            <h2><spring:message code="location.added"/></h2>
        </div>
        <div>
            ${location.name}
        </div>
        <a class="nav-link" href="/admin/addLocation"><spring:message code="add.another.location"/></a>
</html>
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
            <h2><spring:message code="specialty.added"/></h2>
        </div>
        <div>
            ${specialty.name}
        </div>
        <a class="nav-link" href="/admin/addSpecialty"><spring:message code="add.another.specialty"/></a>
    </body>
</html>

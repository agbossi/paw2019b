<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
    <head>
        <%@ page isELIgnored="false" %>
        <jsp:include page="base/navbar.jsp" />
        <link href="<c:url value="/resources/css/errors.css" />" rel="stylesheet" type="text/css" />
    </head>
    <body class="error-body">
        <div class="error-container">
            <div class="something-wrong">
                <h3><spring:message code="permission.denied.exception"/></h3>
            </div>
        </div>
    </body>
</html>

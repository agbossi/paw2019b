<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Error page</title>
        <%@ page isELIgnored="false" %>
        <jsp:include page="base/navbar.jsp" />
        <link href="<c:url value="/resources/css/errors.css" />" rel="stylesheet" type="text/css" />
    </head>
    <body class="error-body">
        <div class="error-container">
            <div class="something-wrong">
                <h4><spring:message code="something.went.wrong"/> <c:out value="${url}"/></h4>
            </div>
            <div class="problem">
                <h5><c:out value="${message}"/></h5>
            </div>
        </div>
    </body>
</html>



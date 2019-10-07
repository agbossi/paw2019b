<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error page</title>
    <%@ page isELIgnored="false" %>
    <jsp:include page="base/navbar.jsp" />
</head>
<body>
    <h2><spring:message code="something.went.wrong"/> <c:out value="${url}"/></h2><br><br>
    <h3><c:out value="${message}"/></h3>
</body>
</html>

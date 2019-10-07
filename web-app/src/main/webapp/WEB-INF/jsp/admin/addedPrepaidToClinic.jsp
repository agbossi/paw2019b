
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
    <%@ page isELIgnored="false" %>
    <jsp:include page="../base/navbar.jsp" />
</head>
<body class="d-flex flex-column h-100">
    <h2><spring:message code="prepaid.added.to.clinic"/></h2>
    <a class="nav-link" href="<c:url value="/admin/addPrepaidToClinic"/>"><spring:message code="add.another.prepaid.to.clinic"/></a>
</body>
</html>

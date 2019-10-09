<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<html>
    <head>
        <%@ page isELIgnored="false" %>
        <jsp:include page="../base/navbar.jsp" />
        <link href="<c:url value="/resources/css/adminAdded.css" />" rel="stylesheet" type="text/css" />
    </head>
    <body class="added-body">
    <div class="added-container">
        <div>
            <p><h5><spring:message code="prepaid.added"/></h5></p>
            <p><b><spring:message code="prepaid"/></b></p>
            <p><c:out value="${prepaid.name}"/></p>
        </div>
        <div>
            <a class="nav-link" href="<c:url value="/admin/addPrepaid"/>"><spring:message code="add.another.prepaid"/></a>
        </div>
        <div>
            <a class="nav-link" href="<c:url value="/admin/prepaids"/>"><spring:message code="go.back.to.list"/></a>
        </div>
    </div>
    </body>
</html>



<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
    <link href="<c:url value="/resources/css/admin.css" />" rel="stylesheet" type="text/css" />
    <jsp:include page="../base/navbar.jsp" />
</head>
    <body class="admin-body">
            <div class="admin-container">
                <div>
                    <a class="p-2 text-dark" href="<c:url value="/admin/addDoctor"/>"><spring:message code="add.doctor"/></a>
                </div>
                <div>
                    <a class="p-2 text-dark" href="<c:url value="/admin/addClinic"/>"><spring:message code="add.clinic"/></a>
                </div>
                <div>
                    <a class="p-2 text-dark" href="<c:url value="/admin/addLocation"/>"><spring:message code="add.location"/></a>
                </div>
                <div>
                    <a class="p-2 text-dark" href="<c:url value="/admin/addSpecialty"/>"><spring:message code="add.specialty"/></a>
                </div>
                <div>
                    <a class="p-2 text-dark" href="<c:url value="/admin/addPrepaid"/>"><spring:message code="add.prepaid"/></a>
                </div>
                <div>
                    <a class="p-2 text-dark" href="<c:url value="/admin/addPrepaidToClinic"/>"><spring:message code="add.prepaid.to.clinic"/></a>
                </div>
            </div>
        </div>
    </body>
</html>

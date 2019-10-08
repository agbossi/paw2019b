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
                    <h2>
                        <a class="p-2 admin-index-option" href="<c:url value="/admin/addDoctor"/>"><spring:message code="doctor.index"/></a>
                    </h2>
                </div>
                <div>
                    <h2>
                        <a class="p-2 admin-index-option" href="<c:url value="/admin/addClinic"/>"><spring:message code="clinic.index"/></a>
                    </h2>

                </div>
                <div>
                    <h2>
                        <a class="p-2 admin-index-option" href="<c:url value="/admin/addLocation"/>"><spring:message code="location.index"/></a>
                    </h2>

                </div>
                <div>
                    <h2>
                        <a class="p-2 admin-index-option" href="<c:url value="/admin/addSpecialty"/>"><spring:message code="specialty.index"/></a>
                    </h2>

                </div>
                <div>
                    <h2>
                        <a class="p-2 admin-index-option" href="<c:url value="/admin/addPrepaid"/>"><spring:message code="prepaid.index"/></a>
                    </h2>

                </div>
                <div>
                    <h2>
                        <a class="p-2 admin-index-option" href="<c:url value="/admin/addPrepaidToClinic"/>"><spring:message code="prepaid.to.clinic.index"/></a>
                    </h2>
                </div>
            </div>
        </div>
    </body>
</html>

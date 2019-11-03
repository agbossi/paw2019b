<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <%@ page isELIgnored="false" %>
    <jsp:include page="../base/navbar.jsp" />
    <link href="<c:url value="/resources/css/adminInfo.css" />" rel="stylesheet" type="text/css" />
</head>
<body class="admin-info-body">
<div class="admin-info-header">
    <h5>
        <a class="p-2 text-dark" href="<c:url value="/admin/addPrepaidToClinic"/>">
            <b><spring:message code="add.prepaid.to.clinic"/></b>
        </a>
    </h5>
</div>
<div class="admin-info-container">
    <c:forEach var="prepaidClinic" items="${prepaidClinics}">
        <div>
            <h6><c:out value="${prepaidClinic.clinic.name}"/>, <c:out value="${prepaidClinic.prepaid.name}"/></h6>
            <h6>
                <div class="delete-box">
                    <b class="delete-element">
                        <a href="<c:url value="/admin/deletePrepaidClinic/${prepaidClinic.prepaid.name}/${prepaidClinic.clinic.id}"/>">
                            <input type="submit" value="<spring:message code="remove"/>" name="<spring:message code="remove"/>" onclick="return confirmSubmit()">
                        </a>
                    </b>
                </div>
            </h6>
        </div>
    </c:forEach>
</div>
</body>
</html>

<script>
    function confirmSubmit()
    {
        var agree=confirm("Are you sure you want to remove this prepaid for this clinic?");
        if (agree)
            return true ;
        else
            return false ;
    }
</script>

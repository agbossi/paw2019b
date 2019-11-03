<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <%@ page isELIgnored="false" %>
    <jsp:include page="../base/navbar.jsp" />
    <link href="<c:url value="/resources/css/adminInfo.css" />" rel="stylesheet" type="text/css" />
</head>
<body class="admin-info-body">
<div class="admin-info-header">
    <h5>
        <a class="p-2 text-dark" href="<c:url value="/admin/prepaids"/>">
            <b><spring:message code="go.back.to.list"/></b>
        </a>
    </h5>
</div>
<div class="admin-info-container">
    <div class="edit-info-container">
        <c:url value="/admin/editPrepaid/${prepaid.name}/post" var="postPath"/>
        <form:form modelAttribute="prepaidForm" action="${postPath}" method="post">
            <h6>
                <div>
                    <form:label path="name"><spring:message code="name"/> </form:label>
                    <form:input type="text" path="name" placeholder="${prepaid.name}"/>
                    <form:errors class="errors" path="name" element="p"/>
                </div>
            </h6>
            <h6>
                <div class="delete-box">
                    <b class="delete-element">
                        <div>
                            <input type="submit" class="edit-button" value="<spring:message code="update"/>" name="<spring:message code="update"/>" onclick="return confirmUpdate()">
                        </div>
                    </b>
                </div>
            </h6>
        </form:form>
    </div>
</div>
</body>
</html>

<script>
    function confirmUpdate() {
        var agree=confirm("Are you sure you want to change the name?");
        if (agree)
            return true ;
        else
            return false ;
    }
</script>
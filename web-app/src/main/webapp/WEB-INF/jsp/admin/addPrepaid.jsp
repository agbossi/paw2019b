<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
    <%@ page isELIgnored="false" %>
    <jsp:include page="../base/navbar.jsp" />
    <link href="<c:url value="/resources/css/admin.css" />" rel="stylesheet" type="text/css" />
</head>
<body class="d-flex flex-column h-100">
    <div class="add-doctor-container-container">
        <h2 class="doctor-form-title"><spring:message code="prep.info"/> </h2>
        <div class="add-doctor-container">
            <c:url value="/admin/addedPrepaid" var="postPath"/>
            <form:form modelAttribute="prepaidForm" action="${postPath}" method="post">
                <div class="add-doctor-form-container">
                    <div>
                        <div>
                            <div class="label-div">
                                <form:label path="name"><spring:message code="name"/> </form:label>
                            </div>
                            <div class="input-div">
                                <form:input type="text" path="name"/>
                            </div>
                        </div>
                        <form:errors class="errors" path="name" element="p"/>
                    </div>
                    <div class="add-doctor-add-button">
                        <input class="submit-add" type="submit" value="<spring:message code="submit.add"/>">
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</body>
</html>

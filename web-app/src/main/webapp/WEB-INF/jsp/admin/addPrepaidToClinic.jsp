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
    <h2 class="doctor-form-title"><spring:message code="prep.to.clinic.info"/> </h2>
    <div class="add-doctor-container">
        <c:url value="/admin/addedPrepaidToClinic" var="postPath"/>
        <form:form modelAttribute="prepaidToClinicForm" action="${postPath}" method="post">
            <div class="add-doctor-form-container">

                <div>
                    <div>
                        <div class="label-div">
                            <form:label path="clinic"><spring:message code="clinic"/> </form:label>
                        </div>
                        <div class="input-div">
                            <form:select path="clinic">
                                <c:forEach var="clinic" items="${clinics}">
                                    <form:option value="${clinic.id}"> <c:out value="${clinic.name}"/> (<c:out value="${clinic.location.locationName}"/>) </form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                    </div>
                    <form:errors class="errors" path="clinic" element="p"/>
                </div>

                <div>
                    <div>
                        <div class="label-div">
                            <form:label path="prepaid"><spring:message code="prepaid"/> </form:label>
                        </div>
                        <div class="input-div">
                            <form:select path="prepaid">
                                <c:forEach var="prepaid" items="${prepaids}">
                                    <form:option value="${prepaid.name}"/>
                                </c:forEach>
                            </form:select>
                        </div>
                    </div>
                    <form:errors class="errors" path="prepaid" element="p"/>
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

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
    <head>
        <%@ page isELIgnored="false" %>
        <jsp:include page= "../base/navbar.jsp"/>
        <link href="<c:url value="/resources/css/admin.css" />" rel="stylesheet" type="text/css" />
    </head>
    <body class="d-flex flex-column h-100">
        <div class="add-doctor-container-container">
            <h2 class="doctor-form-title"><spring:message code="prep.to.clinic.info"/> </h2>
            <div class="add-doctor-container">
                <c:url value="/admin/addedClinic" var="postPath"/>
                <form:form modelAttribute="clinicForm" action="${postPath}" method="post">
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
                        <div>
                            <div>
                                <div class="label-div">
                                    <form:label path="address"><spring:message code="address"/> </form:label>
                                </div>
                                <div class="input-div">
                                    <form:input type="text" path="address"/>
                                </div>
                            </div>
                            <form:errors class="errors" path="address" element="p"/>
                        </div>
                        <div>
                            <div>
                                <div class="label-div">
                                    <form:label path="location"><spring:message code="location"/> </form:label>
                                </div>
                                <div class="input-div">
                                    <form:select class="select-input-div" path="location">
                                        <c:forEach var="location" items="${locations}">
                                            <form:option value="${location.locationName}"/>
                                        </c:forEach>
                                    </form:select>
                                </div>
                            </div>
                            <form:errors class="errors" path="location" element="p"/>
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


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>

<html>
    <head>
        <%@ page isELIgnored="false" %>
        <jsp:include page="../base/navbar.jsp" />
        <link href="<c:url value="/resources/css/admin.css" />" rel="stylesheet" type="text/css" />
    </head>
    <body class="d-flex flex-column h-100">
        <div class="add-doctor-container-container">
            <h2 class="doctor-form-title"><spring:message code="doc.info"/> </h2>
            <div class="add-doctor-container">
                <c:url value="/admin/addedDoctor" var="postPath"/>
                <form:form modelAttribute="doctorForm" action="${postPath}" method="post" enctype="multipart/form-data">
                    <div class="add-doctor-form-container">
                        <div>
                            <div>
                                <div class="label-div">
                                    <form:label path="firstName"><spring:message code="user.first.name"/> </form:label>
                                </div>
                                <div class="input-div">
                                    <form:input type="text" path="firstName"/>
                                </div>
                            </div>
                            <form:errors class="errors" path="firstName" element="p"/>
                        </div>
                        <div>
                            <div>
                                <div class="label-div">
                                    <form:label path="lastName"><spring:message code="user.last.name"/> </form:label>
                                </div>
                                <div class="input-div">
                                    <form:input type="text" path="lastName"/>
                                </div>
                            </div>
                            <form:errors class="errors" path="lastName" element="p"/>
                        </div>
                        <div>
                            <div>
                                <div class="label-div">
                                    <form:label path="specialty"><spring:message code="specialty"/> </form:label>
                                </div>
                                <div class="input-div">
                                    <form:select class="select-input-div" path="specialty">
                                        <c:forEach var="specialty" items="${specialties}">
                                            <form:option value="${specialty.specialtyName}"/>
                                        </c:forEach>
                                    </form:select>
                                </div>
                            </div>
                            <form:errors class="errors" path="specialty" element="p"/>
                        </div>
                        <div>
                            <div>
                                <div class="label-div">
                                    <form:label path="license"><spring:message code="license"/> </form:label>
                                </div>
                                <div class="input-div">
                                    <form:input type="text" path="license"/>
                                </div>
                            </div>
                            <form:errors class="errors" path="license" element="p"/>
                        </div>
                        <div>
                            <div>
                                <div class="label-div">
                                    <form:label path="phoneNumber"><spring:message code="phone.number"/> </form:label>
                                </div>
                                <div class="input-div">
                                    <form:input type="text" path="phoneNumber"/>
                                </div>
                            </div>
                            <form:errors class="errors" path="phoneNumber" element="p"/>
                        </div>
                        <div>
                            <div>
                                <div class="label-div">
                                    <form:label path="email"><spring:message code="user.email"/> </form:label>
                                </div>
                                <div class="input-div">
                                    <form:input type="text" path="email"/>
                                </div>
                            </div>
                            <form:errors class="errors" path="email" element="p"/>
                        </div>
                        <div>
                            <div>
                                <div class="label-div">
                                    <form:label path="password"><spring:message code="password"/> </form:label>
                                </div>
                                <div class="input-div">
                                    <form:input type="password" path="password" />
                                </div>
                            </div>
                            <form:errors class="errors" path="password" element="p"/>
                        </div>
                        <div>
                            <div>
                                <div class="label-div">
                                    <form:label path="repeatPassword"><spring:message code="repeat.password"/> </form:label>
                                </div>
                                <div class="input-div">
                                    <form:input type="password" path="repeatPassword"/>
                                </div>
                            </div>
                            <form:errors class="errors" path="repeatPassword" element="p"/>
                        </div>
                        <div>
                            <div>
                                <div class="label-div">
                                    <spring:message code="default.picture"/>
                                </div>
                                <div class="input-div">
                                    <input type="file" name="photo" class="photo-input-tag"/>
                                </div>
                            </div>
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
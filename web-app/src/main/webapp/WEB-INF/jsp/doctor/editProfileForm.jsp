<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="format" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <%@ page isELIgnored="false" %>
    <jsp:include page="../base/navbar.jsp" />
    <link href="<c:url value="/resources/css/admin.css" />" rel="stylesheet" type="text/css" />
    <link href="<c:url value="/resources/css/editDoctorProfile.css" />" rel="stylesheet" type="text/css" />

</head>
<body>
    <c:url value="/doctor/editProfileFormPost" var="postPath"/>
    <form:form modelAttribute="editProfileForm" action="${postPath}" method="post" enctype="multipart/form-data">
        <div class="container">
            <div class="row">
                <div class="col-6">
                    <div class="add-doctor-container-container">
                        <h2><spring:message code="account.header"/></h2>
                        <div class="add-doctor-form-container edit-doctor-profile-container">
                            <div>
                                <div>
                                    <div class="label-div">
                                        <form:label path="lastName"><spring:message code="user.first.name"/> </form:label>
                                    </div>
                                    <div class="input-div">
                                        <h6><form:input path="firstName" placeholder="${user.firstName}"/></h6>
                                    </div>
                                </div>
                                <form:errors path="firstName" element="p"/>
                            </div>
                            <div>
                                <div>
                                    <div class="label-div">
                                        <form:label path="lastName"><spring:message code="user.last.name"/> </form:label>
                                    </div>
                                    <div class="input-div">
                                        <h6><form:input path="lastName" placeholder="${user.lastName}"/></h6>
                                    </div>
                                </div>
                                <form:errors path="lastName" element="p"/>
                            </div>
                            <div>
                                <div>
                                    <div class="label-div">
                                        <form:label path="email"><spring:message code="user.email"/> </form:label>
                                    </div>
                                    <div class="input-div">
                                        <h6><form:input path="email" placeholder="${user.email}"/></h6>
                                    </div>
                                </div>
                                <form:errors path="email" element="p"/>
                            </div>
                            <div>
                                <div>
                                    <div class="label-div">
                                        <label path="email"><spring:message code="profile.picture"/></label>
                                    </div>
                                    <div class="input-div">
                                        <input type="file" name="photo" class="photo-input-tag"/>
                                    </div>
                                </div>
                            </div>
                            <div>
                                <div>
                                    <div class="label-div">
                                        <form:label path="oldPassword"><spring:message code="old.password"/></form:label>
                                        <form:label path="oldPassword"/>
                                    </div>
                                    <div class="input-div">
                                        <h6><form:input path="oldPassword"/></h6>
                                    </div>
                                </div>
                                <form:errors path="oldPassword" element="p"/>
                            </div>
                            <div>
                                <div>
                                    <div class="label-div">
                                        <form:label path="newPassword"><spring:message code="password"/></form:label>
                                        <form:label path="newPassword"/>
                                    </div>
                                    <div class="input-div">
                                        <h6><form:input path="newPassword"/></h6>
                                    </div>
                                </div>
                                <form:errors path="newPassword" element="p"/>
                            </div>
                            <div>
                                <div>
                                    <div class="label-div">
                                        <form:label path="repeatNewPassword"><spring:message code="repeat.password"/></form:label>
                                        <form:label path="repeatNewPassword"/>
                                    </div>
                                    <div class="input-div">
                                        <h6><form:input path="repeatNewPassword"/></h6>
                                    </div>
                                </div>
                                <form:errors path="repeatNewPassword" element="p"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-6">
                    <div class="add-doctor-container-container">
                        <h2><spring:message code="doctor.header"/></h2>
                        <div class="add-doctor-form-container edit-doctor-profile-container">
                            <div>
                                <div>
                                    <div class="label-div">
                                        <form:label path="specialty"><spring:message code="specialty"/> </form:label>
                                    </div>
                                    <div class="input-div">
                                        <form:select class="select-input-div" path="specialty">
                                            <form:option value="${doctor.specialty.specialtyName}"/>
                                            <c:forEach var="specialty" items="${specialties}">
                                                <c:if test="${ specialty.specialtyName != doctor.specialty.specialtyName }">
                                                    <form:option value="${specialty.specialtyName}"/>
                                                </c:if>
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
                                        <form:input type="text" path="license" placeholder="${doctor.license}"/>
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
                                        <form:input type="text" path="phoneNumber" placeholder="${doctor.phoneNumber}"/>
                                    </div>
                                </div>
                                <form:errors class="errors" path="phoneNumber" element="p"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <br/><br/>
            <div class="profile-save-options">
                <a href="/doctor/editProfile"><spring:message code="a.cancel"/></a> <input class="profile-save-changes" type="submit" value="<spring:message code="submit.save.changes"/>">
            </div>
        </div>
    </form:form>
</body>
</html>


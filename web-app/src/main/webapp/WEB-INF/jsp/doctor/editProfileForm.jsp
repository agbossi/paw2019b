<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="format" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <%@ page isELIgnored="false" %>
    <jsp:include page="../base/navbar.jsp" />

</head>
<body>
    <c:url value="/doctor/editProfileFormPost" var="postPath"/>
    <form:form modelAttribute="editProfileForm" action="${postPath}" method="post" enctype="multipart/form-data">
        <div class="container mycont">
            <div class="row">
                <div class="col-6">
                    <h2><spring:message code="account.header"/></h2>
                    <div>
                        <h5><b><spring:message code="user.first.name"/></b></h5>
                        <form:label path="firstName"/>
                        <h6><form:input path="firstName" placeholder="${user.firstName}"/></h6>
                        <form:errors path="firstName" element="p"/>
                    </div>
                    <div>
                        <h5><b><spring:message code="user.last.name"/></b></h5>
                        <form:label path="lastName"/>
                        <h6><form:input path="lastName" placeholder="${user.lastName}"/></h6>
                        <form:errors path="lastName" element="p"/>
                    </div>
                    <div>
                        <h5><b><spring:message code="user.email"/></b></h5>
                        <form:label path="email"/>
                        <h6><form:input path="email" placeholder="${user.email}"/></h6>
                        <form:errors path="email" element="p"/>
                    </div>
                    <div>
                        <h5><b><spring:message code="profile.picture"/></b></h5>
                        <svg class="rounded-circle"
                             width="140" height="140"
                             xmlns="http://www.w3.org/2000/svg"
                             preserveAspectRatio="xMidYMid slice"
                             focusable="false" role="img" aria-label="Placeholder: 140x140">
                            <rect width="100%" height="100%" fill="#777"></rect>
                            <image width="100%" height="100%" xlink:href="<c:url value="/images/${doctor.license}"/>" clip-path="url(#circleView)" />
                        </svg>
                        <div>
                            <spring:message code="update.picture"/> <input type="file" name="photo"/>
                        </div>
                    </div>
                    <br/>
                    <div>
                        <h5><b><spring:message code="password"/></b></h5>
                        <div
                            <form:label path="oldPassword"><spring:message code="old.password"/></form:label>
                            <h6><form:input path="oldPassword" placeholder="********"/></h6>
                            <form:errors path="oldPassword" element="p"/>
                        </div>
                        <div>
                            <form:label path="newPassword"><spring:message code="repeat.password"/></form:label>
                            <h6><form:input path="newPassword" placeholder="********"/></h6>
                            <form:errors path="newPassword" element="p"/>
                        </div>
                        <div>
                            <form:label path="repeatNewPassword"><spring:message code="repeat.password"/></form:label>
                            <h6><form:input path="repeatNewPassword" placeholder="********"/></h6>
                            <form:errors path="repeatNewPassword" element="p"/>
                        </div>
                    </div>
                </div>
                <div class="col-6">
                    <h2><spring:message code="doctor.header"/></h2>
                    <div>
                        <h5><b><spring:message code="doctor.license"/></b></h5>
                        <form:label path="license"/>
                        <h6><form:input path="license" placeholder="${doctor.license}"/></h6>
                        <form:errors path="license" element="p"/>
                    </div>
                    <div>
                        <h5><b><spring:message code="doctor.specialty"/></b></h5>
                        <form:label path="specialty"/>
                        <h6><form:input path="specialty" placeholder="${doctor.specialty.specialtyName}"/></h6>
                        <form:errors path="specialty" element="p"/>
                    </div>
                    <div>
                        <h5><b><spring:message code="doctor.phone.number"/></b></h5>
                        <form:label path="phoneNumber"/>
                        <h6><form:input path="phoneNumber" placeholder="${doctor.phoneNumber}"/></h6>
                        <form:errors path="phoneNumber" element="p"/>
                    </div>
                </div>
            </div>
            <br/><br/>
            <div>
                <a href="/doctor/editProfile"><spring:message code="a.cancel"/></a> <input type="submit" value="<spring:message code="submit.save.changes"/>">
            </div>
        </div>
    </form:form>
</body>
</html>




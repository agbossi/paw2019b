<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="format" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <%@ page isELIgnored="false" %>
        <jsp:include page="../base/navbar.jsp" />
        <link href="<c:url value="/resources/css/doctor.css" />" rel="stylesheet" type="text/css" />
    </head>
    <body class="doctor-profile-body">
            <div class="profile-container">
                    <div class="profile-user-container">
                        <div>
                            <h5><b><spring:message code="user.first.name"/></b></h5>
                            <h5 class="field-div"><c:out value="${user.firstName}"/>  </h5>
                        </div>
                        <div>
                            <h5><b><spring:message code="user.last.name"/></b></h5>
                            <h5 class="field-div"><c:out value="${user.lastName}"/></h5>
                        </div>
                        <div>
                            <h5><b><spring:message code="user.email"/></b></h5>
                            <h5 class="field-div"><c:out value="${user.email}"/></h5>
                        </div>
                        <div>
                            <h5><b><spring:message code="profile.picture"/></b></h5>
                        </div>
                        <div>
                            <svg class="rounded-circle"
                                 width="140" height="140"
                                 xmlns="http://www.w3.org/2000/svg"
                                 preserveAspectRatio="xMidYMid slice"
                                 focusable="false" role="img" aria-label="Placeholder: 140x140">
                                <rect width="100%" height="100%" fill="#777"></rect>
                                <image width="100%" height="100%" xlink:href="<c:url value="/images/${doctor.license}"/>" clip-path="url(#circleView)" />
                            </svg>
                        </div>
                    </div>
                    <div class="doctor-user-container">
                        <div>
                            <h5><b><spring:message code="doctor.license"/></b></h5>
                            <h5 class="field-div"><c:out value="${doctor.license}"/></h5>
                        </div>
                        <div>
                            <h5><b><spring:message code="doctor.specialty"/></b></h5>
                            <h5 class="field-div"><c:out value="${doctor.specialty.specialtyName}"/></h5>
                        </div>
                        <div>
                            <h5><b><spring:message code="doctor.phone.number"/></b></h5>
                            <h5 class="field-div"><c:out value="${doctor.phoneNumber}"/></h5>
                        </div>
                    </div>
                </div>
            <div class="edit-profile button">
                <a href="/doctor/editProfileForm"><spring:message code="edit"/></a>
            </div>
    </body>
</html>

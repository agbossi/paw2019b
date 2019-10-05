<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="format" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <%@ page isELIgnored="false" %>
        <jsp:include page="../base/navbar.jsp" />
        <link href="<c:url value="/resources/css/doctorimage.css" />" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <div class="container mycont">
            <div class="row">
                <div class="col-6">
                    <h2><spring:message code="account.header"/></h2>
                    <div>
                        <h4><b><spring:message code="user.first.name"/></b></h4>
                        <h6><c:out value="${user.firstName}"/>  </h6>
                    </div>
                    <div>
                        <h4><b><spring:message code="user.last.name"/></b></h4>
                        <h6><c:out value="${user.lastName}"/></h6>
                    </div>
                    <div>
                        <h4><b><spring:message code="user.email"/></b></h4>
                        <h6><c:out value="${user.email}"/></h6>
                    </div>
                    <div>
                        <c:choose>
                            <c:when test="${not empty image}">
                                <img class="doctor-image" src="<c:url value="/images/${doctor.license}"/>" alt="Profile Image" />
                            </c:when>
                            <c:otherwise>
                                <spring:message code="still.no.picture"/>&nbsp;<a href="<c:url value="/doctor/uploadPicture"/>"><spring:message code="here"/></a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="col-6">
                    <h2><spring:message code="doctor.header"/></h2>
                    <div>
                        <h4><b><spring:message code="doctor.license"/></b></h4>
                        <h6><c:out value="${doctor.license}"/></h6>
                    </div>
                    <div>
                        <h4><b><spring:message code="doctor.specialty"/></b></h4>
                        <h6><c:out value="${doctor.specialty.specialtyName}"/></h6>
                    </div>
                    <div>
                        <h4><b><spring:message code="doctor.phone.number"/></b></h4>
                        <h6><c:out value="${doctor.phoneNumber}"/></h6>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

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
        <form action="/doctor/updateProfile" method="post" enctype="multipart/form-data">
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

                        <br/><br/><br/>
                        <div>
                            <input type="submit" value="<spring:message code="submit.save.changes"/>">
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
        </form>
    </body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
    <head>
        <%@ page isELIgnored="false" %>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <title>DoctorSearch</title>

        <link rel="canonical" href="<c:url value="https://getbootstrap.com/docs/4.3/examples/navbar-fixed/"/>">

        <!-- Bootstrap core css -->
        <link rel="stylesheet" href="<c:url value="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"/>" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

    </head>

    <body>
        <div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom box-shadow">

            <h5 class="my-0 mr-md-auto font-weight-normal"><a href="<c:url value="/"/>"><spring:message code="doctor.search.title"/></a></h5>

            <nav class="my-2 my-md-0 mr-md-3">

                <sec:authorize access="hasRole('ROLE_ADMIN')">
                </sec:authorize>

                <sec:authorize access="hasRole('ROLE_DOCTOR')">
                    <a class="p-2 text-dark" href="<c:url value="/doctor/addDoctorClinic"/>"><spring:message code="subscribe.to.clinic"/></a>
                    <a class="p-2 text-dark" href="<c:url value="/doctor/editProfile"/>"><spring:message code="profile"/></a>
                </sec:authorize>

                <sec:authorize access="hasRole('ROLE_USER')">
                    <a class="p-2 text-dark" href="<c:url value="/favorites"/>"><spring:message code="favorites"/></a
                    <a class="p-2 text-dark" href="<c:url value="/appointments"/>"><spring:message code="appointments"/></a>
                    <a class="p-2 text-dark" href="<c:url value="/profile"/>"><spring:message code="profile"/></a>
                </sec:authorize>

                &nbsp;
                <a href="?lang=en"><spring:message code="english"/></a>
                <a href="?lang=es"><spring:message code="spanish"/></a>
                &nbsp;

                <sec:authorize access="hasAnyRole('ROLE_USER','ROLE_DOCTOR','ROLE_ADMIN')">
                    <a class="btn btn-outline-primary" href="<c:url value="/logout"/>"><spring:message code="log.out"/></a>
                </sec:authorize>

                <sec:authorize access="isAnonymous()">
                    <a class="btn btn-outline-primary" href="<c:url value="/signUp"/>"><spring:message code="sign.up"/></a>
                    <a class="btn btn-outline-primary" href="<c:url value="/login"/>"><spring:message code="log.in"/></a>
                </sec:authorize>

            </nav>
        </div>
    </body>
</html>

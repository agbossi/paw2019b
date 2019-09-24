<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <%@ page isELIgnored="false" %>

    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <title>DoctorSearch - Admin</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.3/examples/navbar-fixed/">

    <!-- Bootstrap core css -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

</head>
<body>
<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom box-shadow">
    <h5 class="my-0 mr-md-auto font-weight-normal"><a href="/admin">Doctor Search</a></h5>

    <nav class="my-2 my-md-0 mr-md-3">
        <sec:authorize access="isAnonymous()">
            <a class="p-2 text-dark" href="/addDoctor">Add Doctor</a>
            <a class="p-2 text-dark" href="/addClinic">Add Clinic</a>
            <a class="p-2 text-dark" href="/addLocation">Add Location</a>
            <a class="p-2 text-dark" href="/addSpecialty">Add Specialty</a>
            <a class="p-2 text-dark" href="/addSchedule">Add Schedule</a>
            <a class="p-2 text-dark" href="/addDoctorClinic">Add Doctor to Clinic</a>
        </sec:authorize>
    </nav>

    <sec:authorize access="hasRole('ROLE_USER')">
        <a class="btn btn-outline-primary" href="/logout">log out</a>
    </sec:authorize>
    <sec:authorize access="isAnonymous()">
        <a class="btn btn-outline-primary" href="/signUp">Sign up</a>
        <a class="btn btn-outline-primary" href="/login">log in</a>
    </sec:authorize>
</div>
</body>
</html>
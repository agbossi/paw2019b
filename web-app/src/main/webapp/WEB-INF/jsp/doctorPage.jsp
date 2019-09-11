<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
    <head><head>
        <%@ page isELIgnored="false" %>

        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <title>DoctorSearch - <c:out value="${doctor.name}"/></title>

        <link rel="canonical" href="https://getbootstrap.com/docs/4.3/examples/navbar-fixed/">

        <!-- Bootstrap core css -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

        <link href="<c:url value="/resources/css/index.css" />" rel="stylesheet" type="text/css" />

    </head>
    <body class="d-flex flex-column h-100">
        <header>
            <!-- Fixed navbar -->
            <nav class="navbar navbar-expand-md navbar-dark bg-dark">
                <a class="navbar-brand" href="/">DoctorSearch</a>
                <div class="collapse navbar-collapse" id="navbarCollapse">
                    <a href="/search"><button class="btn btn-outline-success my-2 my-sm-0" type="submit">SEARCH</button></a>
                </div>
            </nav>
        </header>
        <h2><c:out value="${doctor.name}"/> </h2>
        <ul class="list-group">
                <li class="list-group-item">
                    <h6>License: <c:out value="${doctor.license}"/></h6>
                    <h6>Location: <c:out value="${doctor.location.locationName}"/></h6>
                    <h6>Specialty: <c:out value="${doctor.specialty.specialtyName}"/></h6>
                    <h6>Phone Number: <c:out value="${doctor.phoneNumber}"/></h6>
                </li>
        </ul>
    </body>
</html>


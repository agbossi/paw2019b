<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<html>
    <head>
        <%@ page isELIgnored="false" %>

        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <title>DoctorSearch</title>

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

        <div class="container marketing">
            <div class="row">
                <c:forEach var="doctor" items="${doctors}">
                    <div class="col-lg-4">
                        <svg class="bd-placeholder-img rounded-circle" width="140" height="140" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: 140x140">
                            <title>
                                ::before
                                "Placeholder"
                                ::after
                            </title>
                            <rect width="100%" height="100%" fill="#777"></rect>
                            <text x="50%" y="50%" fill="#777" dy=".3em">140x140</text>
                        </svg>
                        <h3>${doctor.name}</h3>
                        <p>${doctor.specialty.specialtyName}</p>
                        <p>${doctor.location.locationName}</p>
                        <p><a class="btn btn-secondary" href="/results/${doctor.license}" role="button">View Details</a></p>
                    </div>
                </c:forEach>
            </div>
        </div>

    </body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <title>DoctorSearch</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.3/examples/navbar-fixed/">

    <!-- Bootstrap core css -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" type="text/css" />
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

</head>
<body class="d-flex flex-column h-100">
<header>
    <!-- Fixed navbar -->
    <nav class="navbar navbar-expand-md navbar-dark bg-dark">
        <a class="navbar-brand" href="#">DoctorSearch</a>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/addDoctor">Add Doctor</a>
                </li>

            </ul>

            <form class="form-inline mt-2 mt-md-0" action="/results" method="post" style="margin-bottom: 0px">
                <div class="searchSelect" style="margin-right: 10px">
                    <select class="browser-default custom-select" name="location">
                        <option value="noLocation">Select location</option>
                        <option value="CABA">CABA</option>
                        <option value="Buenos Aires">Buenos Aires</option>
                    </select>
                    <select class="browser-default custom-select" name="specialty">
                        <option value="noSpecialty">Select specialty</option>
                        <option value="Surgeon">Surgeon</option>
                        <option value="Clinical">Clinical</option>
                        <option value="Pediatrician">Pediatrician</option>
                    </select>
                    <select class="browser-default custom-select" name="clinic">
                        <option value="noClinic">Select clinic</option>
                        <option value="CEMIC">CEMIC</option>
                        <option value="Hospital Italiano">Hospital italiano</option>
                    </select>
                </div>
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>
        </div>
    </nav>
</header>
<div class="container">
</div>

</body>
</html>

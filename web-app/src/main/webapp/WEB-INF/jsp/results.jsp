<%--
  Created by IntelliJ IDEA.
  User: abossi
  Date: 4/9/19
  Time: 18:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head><head>
    <%@ page isELIgnored="false" %>

    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <title>DoctorSearch - Results for <c:out value="${location}"/></title>

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
        <a class="navbar-brand" href="/">DoctorSearch</a>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/">Home</a>
                </li>
                <li class="nav-item ">
                    <a class="nav-link" href="/addDoctor">Add Doctor</a>
                </li>
            </ul>
            <form class="form-inline mt-2 mt-md-0" action="/results" method="post" style="margin-bottom: 0px">
                <div class="searchSelect" style="margin-right: 10px">
                        <select class="browser-default custom-select" name="selectLocation">
                            <option value="noLocation">Select location</option>
                            <option value="CABA">CABA</option>
                            <option value="Buenos Aires">Buenos Aires</option>
                        </select>
                        <select class="browser-default custom-select" name="selectClinic">
                            <option value="noClinic">Select clinic</option>
                            <option value="CEMIC">CEMIC</option>
                            <option value="Hospital Italiano">Hospital italiano</option>
                        </select>
                        <select class="browser-default custom-select" name="selectSpecialty">
                            <option value="noSpecialty">Select specialty</option>
                            <option value="Surgeon">Surgeon</option>
                            <option value="Clinical">Clinical</option>
                            <option value="Pediatrician">Pediatrician</option>
                        </select>
                </div>
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>
        </div>
    </nav>
</header>
    <h2>Results in <c:out value="${location}"/> </h2>
    <ul class="list-group">
        <c:forEach var="doctor" items="${doctors}">
            <li class="list-group-item">
                <a href="/results/${doctor.license}"><c:out value="${doctor.name}"/></a>
                <h6><c:out value="${doctor.specialty}"/></h6>
                <h6>License: <c:out value="${doctor.license}"/></h6>
            </li>
        </c:forEach>
    </ul>
</body>
</html>

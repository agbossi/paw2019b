<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <%@ page isELIgnored="false" %>

        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <title>DoctorSearch - Add Location</title>

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
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="/admin">DoctorSearch<span class="sr-only">(current)</span></a>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
    <h2>Location Information</h2>
    <div class="container">
        <c:url value="/addedLocation" var="postPath"/>
        <form:form modelAttribute="locationForm" action="${postPath}" method="post">
            <div>
                <form:label path="name">Name: </form:label>
                <form:input type="text" path="name"/>
                <form:errors path="name" element="p"/>
            </div>
            <div>
                <input type="submit" value="Add location!">
            </div>
        </form:form>
    </div>
    </body>
</html>

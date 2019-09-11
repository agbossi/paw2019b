<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
        <div class="container">
            <c:url value="/results" var="postPath"/>
            <form:form modelAttribute="searchForm" action="${postPath}" method="post">
                <div>
                    <form:label path="location">Location: </form:label>
                    <form:select path="location">
                        <c:forEach var="location" items="${locations}">
                            <form:option value="${location.locationName}"/>
                        </c:forEach>
                    </form:select>
                    <form:errors path="location" element="p"/>
                </div>
                <div>
                    <form:label path="specialty">Specialty: </form:label>
                    <form:select path="specialty">
                        <c:forEach var="specialty" items="${specialties}">
                            <form:option value="${specialty.specialtyName}"/>
                        </c:forEach>
                    </form:select>
                    <form:errors path="specialty" element="p"/>
                </div>
                <div>
                    <input type="submit" value="Search">
                </div>
            </form:form>
        </div>
    </body>
</html>

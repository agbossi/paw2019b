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
        <title>DoctorSearch - Add Doctor</title>

        <link rel="canonical" href="https://getbootstrap.com/docs/4.3/examples/navbar-fixed/">

        <!-- Bootstrap core css -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
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
        <h2>Doctor Information</h2>
        <div class="container">
            <c:url value="/addedDoctor" var="postPath"/>
            <form:form modelAttribute="doctorForm" action="${postPath}" method="post">
                <div>
                    <form:label path="firstName">First name: </form:label>
                    <form:input type="text" path="firstName"/>
                    <form:errors path="firstName" element="p"/>
                </div>
                <div>
                    <form:label path="lastName">Last name: </form:label>
                    <form:input type="text" path="lastName"/>
                    <form:errors path="lastName" element="p"/>
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
                    <form:label path="license">License: </form:label>
                    <form:input type="text" path="license"/>
                    <form:errors path="license" element="p"/>
                </div>
                <div>
                    <form:label path="phoneNumber">Phone Number: </form:label>
                    <form:input type="text" path="phoneNumber"/>
                    <form:errors path="phoneNumber" element="p"/>
                </div>
                <div>
                    <form:label path="email">Email: </form:label>
                    <form:input type="text" path="email"/>
                    <form:errors path="email" element="p"/>
                </div>
                <div>
                    <form:label path="password">Password: </form:label>
                    <form:input type="password" path="password" />
                    <form:errors path="password" element="p"/></div>
                </div>
                <div>
                    <form:label path="repeatPassword">Repeat password: </form:label>
                    <form:input type="password" path="repeatPassword"/>
                    <form:errors path="repeatPassword" element="p"/>
                </div>
                <div>
                    <input type="submit" value="Add">
                </div>
            </form:form>
        </div>
    </body>
</html>
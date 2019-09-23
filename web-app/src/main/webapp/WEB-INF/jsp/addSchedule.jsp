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
    <title>DoctorSearch - Add  Schedule to Doctor</title>

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
    <c:url value="/addedSchedule" var="postPath"/>
    <form:form modelAttribute="scheduleForm" action="${postPath}" method="post">
        <div>
            <form:label path="doctor">Doctor: </form:label>
            <form:select path="doctor">
                <c:forEach var="doctorClinic" items="${doctorClinics}">
                    <form:option value="${doctorClinic.doctor.license}"> <c:out value="${doctorClinic.doctor.name}"/> </form:option>
                </c:forEach>
            </form:select>
            <form:errors path="doctor" element="p"/>
        </div>
        <div>
            <form:label path="clinic">Clinic: </form:label>
            <form:select path="clinic">
                <c:forEach var="doctorClinic" items="${doctorClinics}">
                    <form:option value="${doctorClinic.clinic.id}"> <c:out value="${doctorClinic.clinic.name}"/> </form:option>
                </c:forEach>
            </form:select>
            <form:errors path="clinic" element="p"/>
        </div>
        <div>
            <form:label path="day">Day: </form:label>
            <form:select path="day">
                <form:option value="MONDAY"/>
                <form:option value="TUESDAY"/>
                <form:option value="WEDNESDAY"/>
                <form:option value="THURSDAY"/>
                <form:option value="FRIDAY"/>
            </form:select>

            <form:errors path="day" element="p"/>
        </div>
        <div>
            <form:label path="hour">Day: </form:label>
            <form:select path="hour">
                <form:option value="8:00"/>
                <form:option value="9:00"/>
                <form:option value="10:00"/>
                <form:option value="11:00"/>
                <form:option value="12:00"/>
                <form:option value="13:00"/>
                <form:option value="14:00"/>
                <form:option value="15:00"/>
                <form:option value="16:00"/>
                <form:option value="17:00"/>
                <form:option value="18:00"/>
                <form:option value="19:00"/>
            </form:select>

            <form:errors path="day" element="p"/>
        </div>
        <div>
            <input type="submit" value="Add">
        </div>
    </form:form>
</div>
</body>
</html>
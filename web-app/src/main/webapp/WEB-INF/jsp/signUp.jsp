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
    <title>DoctorSearch - Sign up</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.3/examples/navbar-fixed/">

    <!-- Bootstrap core css -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
<body>
<header>

    <!-- Fixed navbar -->
    <nav class="navbar navbar-expand-md navbar-dark bg-dark">
        <a class="navbar-brand" href="/">DoctorSearch</a>
    </nav>
</header>
<div class="container">
    <c:url value="/signUp" var="postPath"/>
    <form:form modelAttribute="signUpForm" action="${postPath}" method="post">
        <div>
            <form:label path="name">Name: </form:label>
            <form:input type="text" path="name"/>
            <form:errors path="name" element="p"/>
        </div>
         <div>
            <form:label path="password">Password: </form:label>
            <form:input type="password" path="password" />
            <form:errors path="password" cssClass="formError" element="p"/></div>
        </div>
        <div>
            <form:label path="repeatPassword">Repeat password: </form:label>
            <form:input type="text" path="repeatPassword"/>
            <form:errors path="repeatPassword" element="p"/>
        </div>
        <div>
            <form:label path="email">Email: </form:label>
            <form:input type="text" path="email"/>
            <form:errors path="email" element="p"/>
        </div>
        <div>
            <form:label path="id">Id: </form:label>
            <form:input type="text" path="id"/>
            <form:errors path="id" element="p"/>
        </div>
        <div>
            <form:label path="healthInsurance">Health insurance: </form:label>
            <form:input type="text" path="healthInsurance"/>
            <form:errors path="healthInsurance" element="p"/>
        </div>
            <input type="submit" value="Register!">
        </div>
    </form:form>
</div>
</body>
</html>

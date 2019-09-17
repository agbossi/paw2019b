
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <header>
        <!-- Fixed navbar -->
        <nav class="navbar navbar-expand-md navbar-dark bg-dark">
            <a class="navbar-brand" href="/">DoctorSearch</a>
        </nav>
    </header>
    <c:url value="/login" var="loginUrl" />
    <form action="${loginUrl}" method="post" enctype="application/x-www-form-urlencoded"/>
        <div>
            <label for="email">Email: </label>
            <input id="email" name="j_username" type="text">
        </div>
        <div>
            <label for="password">Password: </label>
            <input id="password" name="j_password" type="password">
        </div>
    <div>
        <label><input name="j_rememberme" type="checkbox"/> <spring:message code="remember_me"/></label>
    </div>
    <div>
        <input type="submit" value="Login!"/>
    </div>
</body>
</html>

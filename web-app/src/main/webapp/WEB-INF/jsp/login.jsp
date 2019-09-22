
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <%@ page isELIgnored="false" %>
    <jsp:include page="base/navbar.jsp" />
</head>
<body>
    <c:url value="/login" var="loginUrl" />
    <form action="${loginUrl}" method="post" enctype="application/x-www-form-urlencoded"/>
        <div>
            <label for="email">Email: </label>
            <input id="email" name="email" type="text">
        </div>
        <div>
            <label for="password">Password: </label>
            <input id="password" name="password" type="password">
        </div>
    <div>
        <label><input name="rememberme" type="checkbox"/> <spring:message code="remember_me"/></label>
    </div>
    <div>
        <input type="submit" value="Login"/>
    </div>
    </div>
    <a href="/signUp">Sign up</a>
    </div>
</body>
</html>

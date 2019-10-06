<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
    <%@ page isELIgnored="false" %>
    <jsp:include page="base/navbar.jsp" />
</head>
<body>
    <div class="container">
        <c:url value="/login" var="loginUrl" />
        <form action="${loginUrl}" method="post" enctype="application/x-www-form-urlencoded"/>
        <div>
            <label for="email"><spring:message code="user.email"/> </label>
            <input id="email" name="email" type="text">
        </div>
        <div>
            <label for="password"><spring:message code="password"/> </label>
            <input id="password" name="password" type="password">
        </div>
        <div>
            <label><input name="rememberme" type="checkbox"/> <spring:message code="rememberme"/></label>
        </div>
        <div>
            <input type="submit" value="<spring:message code="log.in"/>"/>
        </div>
        <div>
            <a href="<c:url value="/signUp"/>"><spring:message code="sign.up"></a>
        </div>
    </div>

</body>
</html>

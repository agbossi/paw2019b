
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>Doctor profile</title>
</head>
<body>
<h1>Hello <c:out value="${doctor.firstName}"></c:out> <c:out value="${doctor.lastName}"></c:out></h1>
</body>
</html>

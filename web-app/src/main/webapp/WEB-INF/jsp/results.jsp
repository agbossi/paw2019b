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
<head>
    <%@ page isELIgnored="false" %>
    <title>results</title>
</head>
<body>
    <h2>Results for <c:out value="${dni}"/> in <c:out value="${ubicacion}"/> </h2>
    <ul>
        <c:forEach var="doctor" items="${doctors}">
            <li>
                <h4><c:out value="${doctor.name}"/></h4>
                <h4><c:out value="${doctor.specialty}"/></h4>
                <h4><c:out value="${doctor.license}"/></h4>
            </li>
        </c:forEach>
    </ul>
</body>
</html>

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
    <title>resultados</title>
</head>
<body>
    <ul>
        <c:forEach var="doctor" items="${doctors}">
            <li><c:out value="${doctor}"/></li>
        </c:forEach>
    </ul>
</body>
</html>

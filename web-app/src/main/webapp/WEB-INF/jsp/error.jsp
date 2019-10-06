
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error page</title>
    <%@ page isELIgnored="false" %>
    <jsp:include page="base/navbar.jsp" />
</head>
<body>
    <h2>Something went wrong when requested: ${url} please contact support</h2><br><br>
    <h3>${message}</h3>
</body>
</html>

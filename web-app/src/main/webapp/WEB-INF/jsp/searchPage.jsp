<%--
  Created by IntelliJ IDEA.
  User: abossi
  Date: 4/9/19
  Time: 18:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Doctor Search</title>
</head>
<body>
    <form action="/results" method="post">
        <h3>Select location</h3>
        <select name="selectLocation">
            <option>CABA</option>
            <option>Buenos Aires</option>
        </select>
        <h3>Enter DNI</h3>
        <input type="text" name="inputDni">
        <input type="submit" value="search">
    </form>
</body>
</html>

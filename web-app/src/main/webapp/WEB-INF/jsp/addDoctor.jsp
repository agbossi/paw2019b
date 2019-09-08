<%--
  Created by IntelliJ IDEA.
  User: gigizuber
  Date: 2019-09-05
  Time: 22:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <%@ page isELIgnored="false" %>
    <title>added Doctor</title>
</head>
<body>
<form action="/added" method="post">
    <h3>Name</h3>
    <input type="text" name="name">
    <h3>Specialty</h3>
    <input type="text" name="specialty">
    <h3>Location</h3>
    <select name="location">
        <option value="CABA">CABA</option>
        <option value="CABA">Buenos Aires</option>
    </select>
    <h3>License</h3>
    <input type="text" name="license">
    <input type="submit" value="Add">
</form>
</body>
</html>
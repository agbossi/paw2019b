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
    <title>PAW duele</title>
</head>
<body>
    <form action="/resultados" method="post">
        <h3>seleccione ubicacion</h3>
        <select name="selectUbicacion">
            <option>CABA</option>
            <option>Buenos Aires</option>
        </select>
        <h3>ingrese DNI</h3>
        <input type="text" name="inputDni">
        <input type="submit" value="buscar">
    </form>
</body>
</html>

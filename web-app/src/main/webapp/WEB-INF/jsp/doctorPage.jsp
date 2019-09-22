<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
    <head>
        <%@ page isELIgnored="false" %>
        <jsp:include page="base/navbar.jsp" />
    </head>
    <body>
        <h2><c:out value="${doctor.name}"/></h2>
        <ul class="list-group">
            <li class="list-group-item">
                <h6>License: <c:out value="${doctor.license}"/></h6>
                <h6>Location: <c:out value="${doctor.location.locationName}"/></h6>
                <h6>Specialty: <c:out value="${doctor.specialty.specialtyName}"/></h6>
                <h6>Phone Number: <c:out value="${doctor.phoneNumber}"/></h6>
            </li>
        </ul>
        <table style="width:100%">
            <tr>
                <th></th>
                <th>Monday</th>
                <th>Tuesday</th>
                <th>Wednesday</th>
                <th>Thursday</th>
                <th>Friday</th>
            </tr>
            <tr>
                <td>8:00</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>9:00</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>10:00</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>11:00</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>12:00</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>13:00</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>14:00</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>15:00</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
         </table>
    </body>
</html>


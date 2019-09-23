<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
    <head>
        <%@ page isELIgnored="false" %>
        <jsp:include page="base/navbar.jsp" />
    </head>
    <body>
        <h2><c:out value="${doctor.doctor.name}"/></h2>
        <ul class="list-group">
            <li class="list-group-item">
                <h6>License: <c:out value="${doctor.doctor.license}"/></h6>
                <h6>Clinic: <c:out value="${doctor.clinic.name}"/> (<c:out value="${doctor.clinic.location.locationName}"/>)</h6>
                <h6>Specialty: <c:out value="${doctor.doctor.specialty.specialtyName}"/></h6>
                <h6>Phone Number: <c:out value="${doctor.doctor.phoneNumber}"/></h6>
                <h6>Schedule :</h6>
            </li>
        </ul>



        <table class="table">
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
                    <td>
                        <c:forEach var="schedule" items="${doctor.schedule}">
                            <c:if test="${schedule.day == 'MONDAY' && schedule.hour =='8:00'}">
                                <h6>Available</h6>
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <c:forEach var="schedule" items="${doctor.schedule}">
                            <c:if test="${schedule.day == 'TUESDAY' && schedule.hour =='8:00'}">
                                <h6>Available</h6>
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <c:forEach var="schedule" items="${doctor.schedule}">
                            <c:if test="${schedule.day == 'WEDNESDAY' && schedule.hour =='8:00'}">
                                <h6>Available</h6>
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <c:forEach var="schedule" items="${doctor.schedule}">
                            <c:if test="${schedule.day == 'THURSDAY' && schedule.hour =='8:00'}">
                                <h6>Available</h6>
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <c:forEach var="schedule" items="${doctor.schedule}">
                            <c:if test="${schedule.day == 'FRIDAY' && schedule.hour =='8:00'}">
                                <h6>Available</h6>
                            </c:if>
                        </c:forEach>
                    </td>
            </tr>
            <tr>
                <td>9:00</td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'MONDAY' && schedule.hour =='9:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'TUESDAY' && schedule.hour =='9:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'WEDNESDAY' && schedule.hour =='9:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'THURSDAY' && schedule.hour =='9:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'FRIDAY' && schedule.hour =='9:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <td>10:00</td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'MONDAY' && schedule.hour =='10:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'TUESDAY' && schedule.hour =='10:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'WEDNESDAY' && schedule.hour =='10:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'THURSDAY' && schedule.hour =='10:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'FRIDAY' && schedule.hour =='10:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <td>11:00</td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'MONDAY' && schedule.hour =='11:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'TUESDAY' && schedule.hour =='11:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'WEDNESDAY' && schedule.hour =='11:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'THURSDAY' && schedule.hour =='11:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'FRIDAY' && schedule.hour =='11:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <td>12:00</td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'MONDAY' && schedule.hour =='12:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'TUESDAY' && schedule.hour =='12:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'WEDNESDAY' && schedule.hour =='12:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'THURSDAY' && schedule.hour =='12:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'FRIDAY' && schedule.hour =='12:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <td>13:00</td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'MONDAY' && schedule.hour =='13:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'TUESDAY' && schedule.hour =='13:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'WEDNESDAY' && schedule.hour =='13:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'THURSDAY' && schedule.hour =='13:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'FRIDAY' && schedule.hour =='13:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <td>14:00</td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'MONDAY' && schedule.hour =='14:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'TUESDAY' && schedule.hour =='14:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'WEDNESDAY' && schedule.hour =='14:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'THURSDAY' && schedule.hour =='14:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'FRIDAY' && schedule.hour =='14:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <td>15:00</td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'MONDAY' && schedule.hour =='15:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'TUESDAY' && schedule.hour =='15:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'WEDNESDAY' && schedule.hour =='15:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'THURSDAY' && schedule.hour =='15:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'FRIDAY' && schedule.hour =='15:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <td>16:00</td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'MONDAY' && schedule.hour =='16:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'TUESDAY' && schedule.hour =='16:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'WEDNESDAY' && schedule.hour =='16:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'THURSDAY' && schedule.hour =='16:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'FRIDAY' && schedule.hour =='16:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <td>17:00</td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'MONDAY' && schedule.hour =='17:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'TUESDAY' && schedule.hour =='17:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'WEDNESDAY' && schedule.hour =='17:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'THURSDAY' && schedule.hour =='17:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'FRIDAY' && schedule.hour =='17:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <td>18:00</td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'MONDAY' && schedule.hour =='18:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'TUESDAY' && schedule.hour =='18:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'WEDNESDAY' && schedule.hour =='18:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'THURSDAY' && schedule.hour =='18:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'FRIDAY' && schedule.hour =='18:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <td>19:00</td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'MONDAY' && schedule.hour =='19:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'TUESDAY' && schedule.hour =='19:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'WEDNESDAY' && schedule.hour =='19:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'THURSDAY' && schedule.hour =='19:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="schedule" items="${doctor.schedule}">
                        <c:if test="${schedule.day == 'FRIDAY' && schedule.hour =='19:00'}">
                            <h6>Available</h6>
                        </c:if>
                    </c:forEach>
                </td>
            </tr>
        </table>

    </body>
</html>


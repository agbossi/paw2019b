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
                <c:forEach var="schedule" items="${doctor.schedule}">
                    <td>8:00</td>
                    <c:choose>
                        <c:when test="${schedule.hour == '8:00'}">
                            <td>
                                <c:if test="${schedule.day == 'MONDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${schedule.day == 'TUESDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${schedule.day == 'WEDNESDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${schedule.day == 'THURSDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${schedule.day == 'FRIDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </tr>
            <tr>
                <c:forEach var="schedule" items="${doctor.schedule}">
                    <td>9:00</td>
                    <c:choose>
                        <c:when test="${schedule.hour == '9:00'}">
                            <td>
                                <c:if test="${schedule.day == 'MONDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${schedule.day == 'TUESDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${schedule.day == 'WEDNESDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${schedule.day == 'THURSDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${schedule.day == 'FRIDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </tr>
            <tr>
                <c:forEach var="schedule" items="${doctor.schedule}">
                    <td>10:00</td>
                    <c:choose>
                        <c:when test="${schedule.hour == '10:00'}">
                            <td>
                                <c:if test="${schedule.day == 'MONDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${schedule.day == 'TUESDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${schedule.day == 'WEDNESDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${schedule.day == 'THURSDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${schedule.day == 'FRIDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </tr>
            <tr>
                <c:forEach var="schedule" items="${doctor.schedule}">
                    <td>11:00</td>
                    <c:choose>
                        <c:when test="${schedule.hour == '11:00'}">
                            <td>
                                <c:if test="${schedule.day == 'MONDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${schedule.day == 'TUESDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${schedule.day == 'WEDNESDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${schedule.day == 'THURSDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${schedule.day == 'FRIDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </tr>
            <tr>
                <c:forEach var="schedule" items="${doctor.schedule}">
                    <td>12:00</td>
                    <c:choose>
                        <c:when test="${schedule.hour == '12:00'}">
                            <td>
                                <c:if test="${schedule.day == 'MONDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${schedule.day == 'TUESDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${schedule.day == 'WEDNESDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${schedule.day == 'THURSDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${schedule.day == 'FRIDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </tr>
            <tr>
                <c:forEach var="schedule" items="${doctor.schedule}">
                    <td>13:00</td>
                    <c:choose>
                        <c:when test="${schedule.hour == '13:00'}">
                            <td>
                                <c:if test="${schedule.day == 'MONDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${schedule.day == 'TUESDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${schedule.day == 'WEDNESDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${schedule.day == 'THURSDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${schedule.day == 'FRIDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </tr>
            <tr>
                <c:forEach var="schedule" items="${doctor.schedule}">
                    <td>14:00</td>
                    <c:choose>
                        <c:when test="${schedule.hour == '14:00'}">
                            <td>
                                <c:if test="${schedule.day == 'MONDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${schedule.day == 'TUESDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${schedule.day == 'WEDNESDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${schedule.day == 'THURSDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${schedule.day == 'FRIDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </tr>
            <tr>
                <c:forEach var="schedule" items="${doctor.schedule}">
                    <td>15:00</td>
                    <c:choose>
                        <c:when test="${schedule.hour == '15:00'}">
                            <td>
                                <c:if test="${schedule.day == 'MONDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${schedule.day == 'TUESDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${schedule.day == 'WEDNESDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${schedule.day == 'THURSDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${schedule.day == 'FRIDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </tr>
            <tr>
                <c:forEach var="schedule" items="${doctor.schedule}">
                    <td>16:00</td>
                    <c:choose>
                        <c:when test="${schedule.hour == '16:00'}">
                            <td>
                                <c:if test="${schedule.day == 'MONDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${schedule.day == 'TUESDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${schedule.day == 'WEDNESDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${schedule.day == 'THURSDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${schedule.day == 'FRIDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </tr>
            <tr>
                <c:forEach var="schedule" items="${doctor.schedule}">
                    <td>17:00</td>
                    <c:choose>
                        <c:when test="${schedule.hour == '17:00'}">
                            <td>
                                <c:if test="${schedule.day == 'MONDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${schedule.day == 'TUESDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${schedule.day == 'WEDNESDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${schedule.day == 'THURSDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${schedule.day == 'FRIDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </tr>
            <tr>
                <c:forEach var="schedule" items="${doctor.schedule}">
                    <td>18:00</td>
                    <c:choose>
                        <c:when test="${schedule.hour == '18:00'}">
                            <td>
                                <c:if test="${schedule.day == 'MONDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${schedule.day == 'TUESDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${schedule.day == 'WEDNESDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${schedule.day == 'THURSDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${schedule.day == 'FRIDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </tr>
            <tr>
                <c:forEach var="schedule" items="${doctor.schedule}">
                    <td>19:00</td>
                    <c:choose>
                        <c:when test="${schedule.hour == '19:00'}">
                            <td>
                                <c:if test="${schedule.day == 'MONDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${schedule.day == 'TUESDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${schedule.day == 'WEDNESDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${schedule.day =='THURSDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${schedule.day == 'FRIDAY'}">
                                    <h6>Available</h6>
                                </c:if>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </tr>
        </table>

    </body>
</html>


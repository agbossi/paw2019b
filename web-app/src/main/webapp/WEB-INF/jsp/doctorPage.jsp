<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="format" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <%@ page isELIgnored="false" %>
        <jsp:include page="base/navbar.jsp" />
        <link href="<c:url value="/resources/css/doctorDetails.css" />" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <div class="doctor-information">
            <h2><c:out value="${doctor.doctor.firstName}"/> <c:out value="${doctor.doctor.lastName}"/></h2>
            <div class="doctor-profile">
                <ul class="list-group">
                    <li class="list-group-item">
                        <h6><b><spring:message code="license"/></b> <c:out value="${doctor.doctor.license}"/></h6>
                        <h6><b><spring:message code="clinic"/></b>  <c:out value="${doctor.clinic.name}"/> (<c:out value="${doctor.clinic.location.locationName}"/>)</h6>
                        <h6><b><spring:message code="specialty"/></b>  <c:out value="${doctor.doctor.specialty.specialtyName}"/></h6>
                        <h6><b><spring:message code="phone.number"/></b>  <c:out value="${doctor.doctor.phoneNumber}"/></h6>
                        <h6><b><spring:message code="user.email"/></b>  <c:out value="${doctor.doctor.email}"/></h6>
                        <h6><b><spring:message code="schedule"/></b> </h6>
                        <h6><format:formatDate value="${today.getTime()}" type="date" pattern="dd/MM"/></h6>
                    </li>
                </ul>
            </div>
        </div>
        <div class="schedule">
            <table class="table doctor-schedule">
                <tr>
                    <th>
                        <c:if test="${week > 1}">
                            <a href="/results/${doctor.clinic.id}/${doctor.doctor.license}/${week - 1}"> - </a>
                        </c:if>
                    </th>
                    <c:forEach var="day" items="${days}">
                        <c:choose>
                            <c:when test="${day.get(7) == 2}">
                                <th><spring:message code="monday"/>
                                    <format:formatDate value="${day.getTime()}" type="date" pattern="dd/MM"/></th>
                            </c:when>
                            <c:when test="${day.get(7) == 3}">
                                <th><spring:message code="tuesday"/>
                                    <format:formatDate value="${day.getTime()}" type="date" pattern="dd/MM"/></th>
                            </c:when>
                            <c:when test="${day.get(7) == 4}">
                                <th><spring:message code="wednesday"/>
                                    <format:formatDate value="${day.getTime()}" type="date" pattern="dd/MM"/></th>
                            </c:when>
                            <c:when test="${day.get(7) == 5}">
                                <th><spring:message code="thursday"/>
                                    <format:formatDate value="${day.getTime()}" type="date" pattern="dd/MM"/></th>
                            </c:when>
                            <c:when test="${day.get(7) == 6}">
                                <th><spring:message code="friday"/>
                                    <format:formatDate value="${day.getTime()}" type="date" pattern="dd/MM"/></th>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                    <th>
                        <a href="/results/${doctor.clinic.id}/${doctor.doctor.license}/${week + 1}"> + </a>
                    </th>
                </tr>
                <tr>
                    <td><spring:message code="8AM"/></td>

                    <c:forEach var="day" items="${days}">
                        <td>
                            <c:forEach var="schedule" items="${doctor.schedule}">
                                <c:if test="${schedule.day == day.get(7) && schedule.hour == 8 && today.compareTo(day) < 0}">
                                    <a href="/createApp/${doctor.clinic.id}/${doctor.doctor.license}/${day.get(1)}-${day.get(2)}-${day.get(5)}-${schedule.hour}"><spring:message code="available"/></a>
                                </c:if>
                            </c:forEach>
                        </td>
                    </c:forEach>
                </tr>
                <tr>
                    <td><spring:message code="9AM"/></td>

                    <c:forEach var="day" items="${days}">
                        <td>
                            <c:forEach var="schedule" items="${doctor.schedule}">
                                <c:if test="${schedule.day == day.get(7) && schedule.hour == 9 && today.compareTo(day) < 0}">
                                    <a href="/createApp/${doctor.clinic.id}/${doctor.doctor.license}/${day.get(1)}-${day.get(2)}-${day.get(5)}-${schedule.hour}"><spring:message code="available"/></a>
                                </c:if>
                            </c:forEach>
                        </td>
                    </c:forEach>
                </tr>
                <tr>
                    <td><spring:message code="10AM"/></td>

                    <c:forEach var="day" items="${days}">
                        <td>
                            <c:forEach var="schedule" items="${doctor.schedule}">
                                <c:if test="${schedule.day == day.get(7) && schedule.hour == 10 && today.compareTo(day) < 0}">
                                    <a href="/createApp/${doctor.clinic.id}/${doctor.doctor.license}/${day.get(1)}-${day.get(2)}-${day.get(5)}-${schedule.hour}"><spring:message code="available"/></a>
                                </c:if>
                            </c:forEach>
                        </td>
                    </c:forEach>
                </tr>
                <tr>
                    <td><spring:message code="11AM"/></td>

                    <c:forEach var="day" items="${days}">
                        <td>
                            <c:forEach var="schedule" items="${doctor.schedule}">
                                <c:if test="${schedule.day == day.get(7) && schedule.hour == 11 && today.compareTo(day) < 0}">
                                    <a href="/createApp/${doctor.clinic.id}/${doctor.doctor.license}/${day.get(1)}-${day.get(2)}-${day.get(5)}-${schedule.hour}"><spring:message code="available"/></a>
                                </c:if>
                            </c:forEach>
                        </td>
                    </c:forEach>
                </tr>
                <tr>
                    <td><spring:message code="12AM"/></td>

                    <c:forEach var="day" items="${days}">
                        <td>
                            <c:forEach var="schedule" items="${doctor.schedule}">
                                <c:if test="${schedule.day == day.get(7) && schedule.hour == 12 && today.compareTo(day) < 0}">
                                    <a href="/createApp/${doctor.clinic.id}/${doctor.doctor.license}/${day.get(1)}-${day.get(2)}-${day.get(5)}-${schedule.hour}"><spring:message code="available"/></a>
                                </c:if>
                            </c:forEach>
                        </td>
                    </c:forEach>
                </tr>
                <tr>
                    <td><spring:message code="1PM"/></td>

                    <c:forEach var="day" items="${days}">
                        <td>
                            <c:forEach var="schedule" items="${doctor.schedule}">
                                <c:if test="${schedule.day == day.get(7) && schedule.hour == 13 && today.compareTo(day) < 0}">
                                    <a href="/createApp/${doctor.clinic.id}/${doctor.doctor.license}/${day.get(1)}-${day.get(2)}-${day.get(5)}-${schedule.hour}"><spring:message code="available"/></a>
                                </c:if>
                            </c:forEach>
                        </td>
                    </c:forEach>
                </tr>
                <tr>
                    <td><spring:message code="2PM"/></td>

                    <c:forEach var="day" items="${days}">
                        <td>
                            <c:forEach var="schedule" items="${doctor.schedule}">
                                <c:if test="${schedule.day == day.get(7) && schedule.hour == 14 && today.compareTo(day) < 0}">
                                    <a href="/createApp/${doctor.clinic.id}/${doctor.doctor.license}/${day.get(1)}-${day.get(2)}-${day.get(5)}-${schedule.hour}"><spring:message code="available"/></a>
                                </c:if>
                            </c:forEach>
                        </td>
                    </c:forEach>
                </tr>
                <tr>
                    <td><spring:message code="3PM"/></td>

                    <c:forEach var="day" items="${days}">
                        <td>
                            <c:forEach var="schedule" items="${doctor.schedule}">
                                <c:if test="${schedule.day == day.get(7) && schedule.hour == 15 && today.compareTo(day) < 0}">
                                    <a href="/createApp/${doctor.clinic.id}/${doctor.doctor.license}/${day.get(1)}-${day.get(2)}-${day.get(5)}-${schedule.hour}"><spring:message code="available"/></a>
                                </c:if>
                            </c:forEach>
                        </td>
                    </c:forEach>
                </tr>
                <tr>
                    <td><spring:message code="4PM"/></td>

                    <c:forEach var="day" items="${days}">
                        <td>
                            <c:forEach var="schedule" items="${doctor.schedule}">
                                <c:if test="${schedule.day == day.get(7) && schedule.hour == 16 && today.compareTo(day) < 0}">
                                    <a href="/createApp/${doctor.clinic.id}/${doctor.doctor.license}/${day.get(1)}-${day.get(2)}-${day.get(5)}-${schedule.hour}"><spring:message code="available"/></a>
                                </c:if>
                            </c:forEach>
                        </td>
                    </c:forEach>
                </tr>
                <tr>
                    <td><spring:message code="5PM"/></td>

                    <c:forEach var="day" items="${days}">
                        <td>
                            <c:forEach var="schedule" items="${doctor.schedule}">
                                <c:if test="${schedule.day == day.get(7) && schedule.hour == 17 && today.compareTo(day) < 0}">
                                    <a href="/createApp/${doctor.clinic.id}/${doctor.doctor.license}/${day.get(1)}-${day.get(2)}-${day.get(5)}-${schedule.hour}"><spring:message code="available"/></a>
                                </c:if>
                            </c:forEach>
                        </td>
                    </c:forEach>
                </tr>
                <tr>
                    <td><spring:message code="6PM"/></td>

                    <c:forEach var="day" items="${days}">
                        <td>
                            <c:forEach var="schedule" items="${doctor.schedule}">
                                <c:if test="${schedule.day == day.get(7) && schedule.hour == 18 && today.compareTo(day) < 0 }">
                                    <a href="/createApp/${doctor.clinic.id}/${doctor.doctor.license}/${day.get(1)}-${day.get(2)}-${day.get(5)}-${schedule.hour}"><spring:message code="available"/></a>
                                </c:if>
                            </c:forEach>
                        </td>
                    </c:forEach>
                </tr>
                <tr>
                    <td><spring:message code="7PM"/></td>

                    <c:forEach var="day" items="${days}">
                        <td>
                            <c:forEach var="schedule" items="${doctor.schedule}">
                                <c:if test="${schedule.day == day.get(7) && schedule.hour == 19  && today.compareTo(day) < 0}">
                                    <a href="/createApp/${doctor.clinic.id}/${doctor.doctor.license}/${day.get(1)}-${day.get(2)}-${day.get(5)}-${schedule.hour}"><spring:message code="available"/></a>
                                </c:if>
                            </c:forEach>
                        </td>
                    </c:forEach>
                </tr>
            </table>
        </div>
    </body>
</html>


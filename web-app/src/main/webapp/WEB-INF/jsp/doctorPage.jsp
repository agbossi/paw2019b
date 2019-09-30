<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
                    </li>
                </ul>
            </div>
            <table>
                <th>
                    <table class="table doctor-schedule">
                        <tr>
                            <th></th>
                            <th><spring:message code="monday"/></th>
                            <th><spring:message code="tuesday"/></th>
                            <th><spring:message code="wednesday"/></th>
                            <th><spring:message code="thursday"/></th>
                            <th><spring:message code="friday"/></th>
                        </tr>
                        <tr>
                            <td><spring:message code="8AM"/></td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'MONDAY' && schedule.hour =='8:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'TUESDAY' && schedule.hour =='8:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'WEDNESDAY' && schedule.hour =='8:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'THURSDAY' && schedule.hour =='8:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'FRIDAY' && schedule.hour =='8:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                        </tr>
                        <tr>
                            <td><spring:message code="9AM"/></td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'MONDAY' && schedule.hour =='9:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'TUESDAY' && schedule.hour =='9:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'WEDNESDAY' && schedule.hour =='9:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'THURSDAY' && schedule.hour =='9:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'FRIDAY' && schedule.hour =='9:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                        </tr>
                        <tr>
                            <td><spring:message code="10AM"/></td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'MONDAY' && schedule.hour =='10:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'TUESDAY' && schedule.hour =='10:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'WEDNESDAY' && schedule.hour =='10:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'THURSDAY' && schedule.hour =='10:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'FRIDAY' && schedule.hour =='10:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                        </tr>
                        <tr>
                            <td><spring:message code="11AM"/></td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'MONDAY' && schedule.hour =='11:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'TUESDAY' && schedule.hour =='11:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'WEDNESDAY' && schedule.hour =='11:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'THURSDAY' && schedule.hour =='11:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'FRIDAY' && schedule.hour =='11:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                        </tr>
                        <tr>
                            <td><spring:message code="12AM"/></td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'MONDAY' && schedule.hour =='12:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'TUESDAY' && schedule.hour =='12:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'WEDNESDAY' && schedule.hour =='12:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'THURSDAY' && schedule.hour =='12:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'FRIDAY' && schedule.hour =='12:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                        </tr>
                        <tr>
                            <td><spring:message code="1PM"/></td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'MONDAY' && schedule.hour =='13:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'TUESDAY' && schedule.hour =='13:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'WEDNESDAY' && schedule.hour =='13:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'THURSDAY' && schedule.hour =='13:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'FRIDAY' && schedule.hour =='13:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                        </tr>
                        <tr>
                            <td><spring:message code="2PM"/></td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'MONDAY' && schedule.hour =='14:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'TUESDAY' && schedule.hour =='14:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'WEDNESDAY' && schedule.hour =='14:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'THURSDAY' && schedule.hour =='14:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'FRIDAY' && schedule.hour =='14:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                        </tr>
                        <tr>
                            <td><spring:message code="3PM"/></td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'MONDAY' && schedule.hour =='15:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'TUESDAY' && schedule.hour =='15:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'WEDNESDAY' && schedule.hour =='15:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'THURSDAY' && schedule.hour =='15:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'FRIDAY' && schedule.hour =='15:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                        </tr>
                        <tr>
                            <td><spring:message code="4PM"/></td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'MONDAY' && schedule.hour =='16:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'TUESDAY' && schedule.hour =='16:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'WEDNESDAY' && schedule.hour =='16:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'THURSDAY' && schedule.hour =='16:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'FRIDAY' && schedule.hour =='16:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                        </tr>
                        <tr>
                            <td><spring:message code="5PM"/></td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'MONDAY' && schedule.hour =='17:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'TUESDAY' && schedule.hour =='17:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'WEDNESDAY' && schedule.hour =='17:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'THURSDAY' && schedule.hour =='17:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'FRIDAY' && schedule.hour =='17:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                        </tr>
                        <tr>
                            <td><spring:message code="6PM"/></td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'MONDAY' && schedule.hour =='18:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'TUESDAY' && schedule.hour =='18:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'WEDNESDAY' && schedule.hour =='18:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'THURSDAY' && schedule.hour =='18:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'FRIDAY' && schedule.hour =='18:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                        </tr>
                        <tr>
                            <td><spring:message code="7PM"/></td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'MONDAY' && schedule.hour =='19:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'TUESDAY' && schedule.hour =='19:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'WEDNESDAY' && schedule.hour =='19:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'THURSDAY' && schedule.hour =='19:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="schedule" items="${doctor.schedule}">
                                    <c:if test="${schedule.day == 'FRIDAY' && schedule.hour =='19:00'}">
                                        <spring:message code="available"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                        </tr>
                    </table>
                </th>
                <th>
                    <!-- SOMETHING TO CHOOSE DATE AND TIME SHOULD GO HERE -->
                </th>
            </table>
        </div>
    </body>
</html>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="format" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <%@ page isELIgnored="false" %>
        <jsp:include page="../base/navbar.jsp" />
        <link href="<c:url value="/resources/css/appointments.css" />" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <div>
            <h6><c:out value="${doctorClinic.clinic.name}"/> (<c:out value="${doctorClinic.clinic.location.locationName}"/>)</h6>
        </div>
        <div>
            <div class="schedule">
                <table class="table doctor-schedule">
                    <tr>
                        <th class="mycell">
                            <c:if test="${weekNum > 1}">
                                <a href="<c:url value="/results/${doctorClinic.clinic.id}/${doctorClinic.doctor.license}/${weekNum - 1}"/>"> - </a>
                            </c:if>
                        </th>
                        <c:forEach var="d" items="${days}">
                            <c:choose>
                                <c:when test="${d.get(7) == 2}">
                                    <th class="mycell"><spring:message code="monday"/>
                                        <format:formatDate value="${d.getTime()}" type="date" pattern="dd/MM"/></th>
                                </c:when>
                                <c:when test="${d.get(7) == 3}">
                                    <th class="mycell"><spring:message code="tuesday"/>
                                        <format:formatDate value="${d.getTime()}" type="date" pattern="dd/MM"/></th>
                                </c:when>
                                <c:when test="${d.get(7) == 4}">
                                    <th class="mycell"><spring:message code="wednesday"/>
                                        <format:formatDate value="${d.getTime()}" type="date" pattern="dd/MM"/></th>
                                </c:when>
                                <c:when test="${d.get(7) == 5}">
                                    <th class="mycell"><spring:message code="thursday"/>
                                        <format:formatDate value="${d.getTime()}" type="date" pattern="dd/MM"/></th>
                                </c:when>
                                <c:when test="${d.get(7) == 6}">
                                    <th class="mycell"><spring:message code="friday"/>
                                        <format:formatDate value="${d.getTime()}" type="date" pattern="dd/MM"/></th>
                                </c:when>
                            </c:choose>
                        </c:forEach>
                        <th class="mycell">
                            <a href="<c:url value="/results/${doctor.clinic.id}/${doctor.doctor.license}/${weekNum + 1}"/>"> + </a>
                        </th>
                    </tr>

                    <c:forEach var="row" items="${week}">
                        <tr>

                            <c:forEach var="day" items="${row}">
                                <c:if test="${day.date.get(7) == 2}">
                                    <td><spring:message code="hour.${day.date.get(11)}"/></td>
                                </c:if>
                                <td class="mycell">
                                    <c:choose>
                                        <c:when test="${day.scheduled && empty day.hasAppointment}">
                                            <a href="<c:url value="/doctorApp/${doctorClinic.clinic.id}/${day.date.get(1)}-${day.date.get(2)}-${day.date.get(5)}-${day.date.get(11)}"/>"><spring:message code="available"/></a>
                                        </c:when>
                                        <c:when test="${day.scheduled && not empty day.hasAppointment}">
                                            <div class="info">
                                                <p>
                                                    <c:out value="${day.hasAppointment.patient.lastName}"/>, <c:out value="${day.hasAppointment.patient.firstName}"/>
                                                </p>
                                                <p>
                                                    <c:out value="${day.hasAppointment.patient.email}"/>
                                                </p>
                                            </div>
                                            <a href="<c:url value="/docCancelApp/${doctorClinic.clinic.id}/${day.hasAppointment.patient.email}/${day.date.get(1)}-${day.date.get(2)}-${day.date.get(5)}-${day.date.get(11)}"/>"><spring:message code="a.cancel"/></a>
                                        </c:when>
                                    </c:choose>
                                </td>
                            </c:forEach>
                            <td></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </body>
</html>

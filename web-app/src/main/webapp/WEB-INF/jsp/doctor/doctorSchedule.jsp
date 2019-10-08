<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="format" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <%@ page isELIgnored="false" %>
    <jsp:include page="../base/navbar.jsp" />
    <link href="<c:url value="/resources/css/clinicPage.css" />" rel="stylesheet" type="text/css" />
</head>
    <body>
    <div class="schedule-container">
        <div class="schedule-header-container">
            <div>
                <a href="<c:url value="/doctor/clinics/${docClinic.clinic.id}/1"/>"> <c:out value="${doctorClinic.clinic.name}"/> - <c:out value="${doctorClinic.clinic.address}"/> (<c:out value="${doctorClinic.clinic.location.locationName}"/>)
                </a>
            </div>
        </div>
        <div>
            <div class="schedule">
                <table class="table doctor-schedule">
                    <tr>
                        <th class="schedule-cell"></th>
                        <c:forEach var="d" items="${days}">
                            <c:choose>
                                <c:when test="${d == 2}">
                                    <th class="schedule-cell"><spring:message code="monday"/></th>
                                </c:when>
                                <c:when test="${d == 3}">
                                    <th class="schedule-cell"><spring:message code="tuesday"/></th>
                                </c:when>
                                <c:when test="${d == 4}">
                                    <th class="schedule-cell"><spring:message code="wednesday"/></th>
                                </c:when>
                                <c:when test="${d == 5}">
                                    <th class="schedule-cell"><spring:message code="thursday"/></th>
                                </c:when>
                                <c:when test="${d == 6}">
                                    <th class="schedule-cell"><spring:message code="friday"/></th>
                                </c:when>
                            </c:choose>
                        </c:forEach>
                        <th class="schedule-cell"></th>
                    </tr>
                    <c:forEach var="row" items="${schedule}">
                        <tr class="schedule-row">
                            <c:forEach var="day" items="${row}">
                                <c:if test="${day.date.get(7) == 2}">
                                    <td><spring:message code="hour.${day.date.get(11)}"/></td>
                                </c:if>
                                <td class="schedule-cell">
                                    <c:choose>
                                        <c:when test="${not day.scheduled && not day.clinic}">
                                            <a href="<c:url value="/doctor/addSchedule/${doctorClinic.clinic.id}/${day.date.get(7)}-${day.date.get(11)}"/>"><spring:message code="add"/></a>
                                        </c:when>
                                        <c:when test="${day.scheduled && day.clinic}">
                                            <a class="remove" href="<c:url value="/doctor/removeSchedule/${doctorClinic.clinic.id}/${day.date.get(7)}-${day.date.get(11)}"/>"><spring:message code="remove"/></a>
                                        </c:when>
                                        <c:otherwise>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </c:forEach>
                            <td></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
    </body>
</html>




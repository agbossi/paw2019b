<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="format" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <%@ page isELIgnored="false" %>
        <jsp:include page="base/navbar.jsp" />
        <link href="<c:url value="/resources/css/clinicPage.css" />" rel="stylesheet" type="text/css" />
        <link href="<c:url value="/resources/css/indexDoctorResult.css" />" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <div class="doctor-information">
            <a href="<c:url value="/results/${doctorClinic.doctor.license}"/>">
                <h6><spring:message code="go.back"/></h6>
                <br/>
            </a>
            <div class="doctor-profile">
                <div>
                    <h6><b><spring:message code="clinic"/></b>  <c:out value="${doctorClinic.clinic.name}"/></h6>
                </div>
                <div>
                    (<c:out value="${doctorClinic.clinic.location.locationName}"/>)
                </div>
                <div>
                    <spring:message code="consult.price"/>&nbsp;<b><c:out value="${doctorClinic.consultPrice}"/></b><spring:message code="currency"/>
                </div>
            </div>
        </div>
        <div class="schedule">
            <table class="table doctor-schedule">
                <tr>
                    <th>
                        <c:if test="${weekNum > 1}">
                            <a href="<c:url value="/results/${doctorClinic.doctor.license}/${doctorClinic.clinic.id}/${weekNum - 1}"/>">-</a>
                        </c:if>
                    </th>
                    <c:forEach var="d" items="${days}">
                        <c:choose>
                            <c:when test="${d.get(7) == 2}">
                                <th><spring:message code="monday"/>
                                    <format:formatDate value="${d.getTime()}" type="date" pattern="dd/MM"/></th>
                            </c:when>
                            <c:when test="${d.get(7) == 3}">
                                <th><spring:message code="tuesday"/>
                                    <format:formatDate value="${d.getTime()}" type="date" pattern="dd/MM"/></th>
                            </c:when>
                            <c:when test="${d.get(7) == 4}">
                                <th><spring:message code="wednesday"/>
                                    <format:formatDate value="${d.getTime()}" type="date" pattern="dd/MM"/></th>
                            </c:when>
                            <c:when test="${d.get(7) == 5}">
                                <th><spring:message code="thursday"/>
                                    <format:formatDate value="${d.getTime()}" type="date" pattern="dd/MM"/></th>
                            </c:when>
                            <c:when test="${d.get(7) == 6}">
                                <th><spring:message code="friday"/>
                                    <format:formatDate value="${d.getTime()}" type="date" pattern="dd/MM"/></th>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                    <th>
                        <a href="<c:url value="/results/${doctorClinic.doctor.license}/${doctorClinic.clinic.id}/${weekNum + 1}"/>"> + </a>
                    </th>
                </tr>
                <c:forEach var="row" items="${week}">
                    <tr>
                        <c:forEach var="day" items="${row}">
                            <c:if test="${day.date.get(7) == 2}">
                                <td><spring:message code="hour.${day.date.get(11)}"/></td>
                            </c:if>
                            <td>
                                <c:if test="${day.scheduled && empty day.hasAppointment}">
                                    <a href="<c:url value="/createApp/${doctorClinic.clinic.id}/${doctorClinic.doctor.license}/${day.date.get(1)}-${day.date.get(2)}-${day.date.get(5)}-${day.date.get(11)}"/>"><spring:message code="available"/></a>
                                </c:if>
                            </td>
                        </c:forEach>
                        <td></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
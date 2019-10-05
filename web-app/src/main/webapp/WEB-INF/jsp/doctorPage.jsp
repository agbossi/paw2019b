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
                    </li>
                </ul>
            </div>
        </div>
        <div class="schedule">
            <table class="table doctor-schedule">
                <tr>
                    <th>
                        <c:if test="${weekNum > 1}">
                            <a href="<c:url value="/results/${doctor.clinic.id}/${doctor.doctor.license}/${weekNum - 1}"/>"> - </a>
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
                        <a href="<c:url value="/results/${doctor.clinic.id}/${doctor.doctor.license}/${weekNum + 1}"/>"> + </a>
                    </th>
                </tr>

                <c:forEach var="row" items="${week}">
                    <tr>

                       <c:forEach var="day" items="${row}">
                           <c:if test="${day.date.get(7) == 2}">
                               <td><spring:message code="hour.${day.date.get(11)}"/></td>
                           </c:if>
                           <td>
                               <c:if test="${day.scheduled && !day.hasAppointment}">
                                   <a href="<c:url value="/createApp/${doctor.clinic.id}/${doctor.doctor.license}/${day.date.get(1)}-${day.date.get(2)}-${day.date.get(5)}-${day.date.get(11)}"/>"><spring:message code="available"/></a>
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


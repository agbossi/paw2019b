<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <head>
        <%@ page isELIgnored="false" %>
        <jsp:include page="../base/navbar.jsp" />
        <link href="<c:url value="/resources/css/appointments.css" />" rel="stylesheet" type="text/css" />
    </head>
    <body class="d-flex flex-column h-100">
        <h2><spring:message code="schedule.for"/> <c:out value="${doctorClinic.clinic.name}"/> (<c:out value="${doctorClinic.clinic.location.locationName}"/>)</h2>
        <div class="schedule">
            <table class="table doctor-schedule">
                <tr>
                    <th></th>
                    <c:forEach var="d" items="${days}">
                        <c:choose>
                            <c:when test="${d == 2}">
                                <th class="mycell"><spring:message code="monday"/></th>
                            </c:when>
                            <c:when test="${d == 3}">
                                <th class="mycell"><spring:message code="tuesday"/></th>
                            </c:when>
                            <c:when test="${d == 4}">
                                <th class="mycell"><spring:message code="wednesday"/></th>
                            </c:when>
                            <c:when test="${d == 5}">
                                <th class="mycell"><spring:message code="thursday"/></th>
                            </c:when>
                            <c:when test="${d == 6}">
                                <th class="mycell"><spring:message code="friday"/></th>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </tr>
                <c:forEach var="row" items="${schedule}">
                    <tr>

                        <c:forEach var="day" items="${row}">
                            <c:if test="${day.date.get(7) == 2}">
                                <td><spring:message code="hour.${day.date.get(11)}"/></td>
                            </c:if>
                            <td class="mycell">
                                <c:choose>
                                    <c:when test="${not day.scheduled}">
                                        <a href="<c:url value="/doctor/addSchedule/${doctorClinic.clinic.id}/${day.date.get(7)}-${day.date.get(11)}"/>"><spring:message code="add"/></a>
                                    </c:when>
                                    <c:otherwise>
                                        <a class="remove" href="<c:url value="/doctor/removeSchedule/${doctorClinic.clinic.id}/${day.date.get(7)}-${day.date.get(11)}"/>"><spring:message code="remove"/></a>

                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </c:forEach>
                        <td></td>
                    </tr>
                </c:forEach>
            </table>
        </div>

<%--        <div class="container">--%>
<%--            <c:url value="/doctor/addedSchedule/${doctorClinic.clinic.id}/${doctorClinic.doctor.license}" var="postPath"/>--%>
<%--            <form:form modelAttribute="scheduleForm" action="${postPath}" method="post">--%>
<%--                <div>--%>
<%--                    <form:label path="day">Day: </form:label>--%>
<%--                    <form:select path="day">--%>
<%--                       <c:forEach var="day" items="${days}">--%>
<%--                           <form:option value="${day}">--%>
<%--                               <c:choose>--%>
<%--                                   <c:when test="${day == 2}">--%>
<%--                                       <spring:message code="monday"/>--%>
<%--                                   </c:when>--%>
<%--                                   <c:when test="${day == 3}">--%>
<%--                                       <spring:message code="tuesday"/>--%>
<%--                                   </c:when>--%>
<%--                                   <c:when test="${day == 4}">--%>
<%--                                       <spring:message code="wednesday"/>--%>
<%--                                   </c:when>--%>
<%--                                   <c:when test="${day == 5}">--%>
<%--                                       <spring:message code="thursday"/>--%>
<%--                                   </c:when>--%>
<%--                                   <c:when test="${day == 6}">--%>
<%--                                       <spring:message code="friday"/>--%>
<%--                                   </c:when>--%>
<%--                               </c:choose>--%>
<%--                           </form:option>--%>
<%--                       </c:forEach>--%>
<%--                    </form:select>--%>
<%--                    <form:errors path="day" element="p"/>--%>
<%--                </div>--%>
<%--                <div>--%>
<%--                    <form:label path="hour">Day: </form:label>--%>
<%--                    <form:select path="hour">--%>
<%--                        <c:forEach var="time" items="${times}">--%>
<%--                            <form:option value="${time}">--%>
<%--                                <c:choose>--%>
<%--                                    <c:when test="${time == 8}">--%>
<%--                                        <spring:message code="8AM"/>--%>
<%--                                    </c:when>--%>
<%--                                    <c:when test="${time == 9}">--%>
<%--                                        <spring:message code="9AM"/>--%>
<%--                                    </c:when>--%>
<%--                                    <c:when test="${time == 10}">--%>
<%--                                        <spring:message code="10AM"/>--%>
<%--                                    </c:when>--%>
<%--                                    <c:when test="${time == 11}">--%>
<%--                                        <spring:message code="11AM"/>--%>
<%--                                    </c:when>--%>
<%--                                    <c:when test="${time == 12}">--%>
<%--                                        <spring:message code="12AM"/>--%>
<%--                                    </c:when>--%>
<%--                                    <c:when test="${time == 13}">--%>
<%--                                        <spring:message code="1PM"/>--%>
<%--                                    </c:when>--%>
<%--                                    <c:when test="${time == 14}">--%>
<%--                                        <spring:message code="2PM"/>--%>
<%--                                    </c:when>--%>
<%--                                    <c:when test="${time == 15}">--%>
<%--                                        <spring:message code="3PM"/>--%>
<%--                                    </c:when>--%>
<%--                                    <c:when test="${time == 16}">--%>
<%--                                        <spring:message code="4PM"/>--%>
<%--                                    </c:when>--%>
<%--                                    <c:when test="${time == 17}">--%>
<%--                                        <spring:message code="5PM"/>--%>
<%--                                    </c:when>--%>
<%--                                    <c:when test="${time == 18}">--%>
<%--                                        <spring:message code="6PM"/>--%>
<%--                                    </c:when>--%>
<%--                                    <c:when test="${time == 19}">--%>
<%--                                        <spring:message code="7PM"/>--%>
<%--                                    </c:when>--%>
<%--                                </c:choose>--%>
<%--                            </form:option>--%>
<%--                        </c:forEach>--%>
<%--                    </form:select>--%>
<%--                    <form:errors path="day" element="p"/>--%>
<%--                </div>--%>
<%--                <div>--%>
<%--                    <input type="submit" value="<spring:message code="submit.add"/>">--%>
<%--                </div>--%>
<%--            </form:form>--%>
<%--        </div>--%>
    </body>
</html>

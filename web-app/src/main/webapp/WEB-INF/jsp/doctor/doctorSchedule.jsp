<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <head>
        <%@ page isELIgnored="false" %>
        <jsp:include page="../base/navbar.jsp" />
    </head>
<body class="d-flex flex-column h-100">
<h2>Doctor Information</h2>
<div class="container">
    <c:url value="/doctor/addedSchedule/${doctorClinic.clinic.id}/${doctorClinic.doctor.license}" var="postPath"/>
    <form:form modelAttribute="scheduleForm" action="${postPath}" method="post">
        <div>
            <form:label path="day">Day: </form:label>
            <form:select path="day">
               <c:forEach var="day" items="${days}">
                   <form:option value="${day}">
                       <c:choose>
                           <c:when test="${day == 2}">
                               <spring:message code="monday"/>
                           </c:when>
                           <c:when test="${day == 3}">
                               <spring:message code="tuesday"/>
                           </c:when>
                           <c:when test="${day == 4}">
                               <spring:message code="wednesday"/>
                           </c:when>
                           <c:when test="${day == 5}">
                               <spring:message code="thursday"/>
                           </c:when>
                           <c:when test="${day == 6}">
                               <spring:message code="friday"/>
                           </c:when>
                       </c:choose>
                   </form:option>
               </c:forEach>
            </form:select>
            <form:errors path="day" element="p"/>
        </div>
        <div>
            <form:label path="hour">Day: </form:label>
            <form:select path="hour">
                <c:forEach var="time" items="${times}">
                    <form:option value="${time}">
                        <c:choose>
                            <c:when test="${time == 8}">
                                <spring:message code="8AM"/>
                            </c:when>
                            <c:when test="${time == 9}">
                                <spring:message code="9AM"/>
                            </c:when>
                            <c:when test="${time == 10}">
                                <spring:message code="10AM"/>
                            </c:when>
                            <c:when test="${time == 11}">
                                <spring:message code="11AM"/>
                            </c:when>
                            <c:when test="${time == 12}">
                                <spring:message code="12AM"/>
                            </c:when>
                            <c:when test="${time == 13}">
                                <spring:message code="1PM"/>
                            </c:when>
                            <c:when test="${time == 14}">
                                <spring:message code="2PM"/>
                            </c:when>
                            <c:when test="${time == 15}">
                                <spring:message code="3PM"/>
                            </c:when>
                            <c:when test="${time == 16}">
                                <spring:message code="4PM"/>
                            </c:when>
                            <c:when test="${time == 17}">
                                <spring:message code="5PM"/>
                            </c:when>
                            <c:when test="${time == 18}">
                                <spring:message code="6PM"/>
                            </c:when>
                            <c:when test="${time == 19}">
                                <spring:message code="7PM"/>
                            </c:when>
                        </c:choose>
                    </form:option>
                </c:forEach>
            </form:select>
            <form:errors path="day" element="p"/>
        </div>
        <div>
            <input type="submit" value="Add">
        </div>
    </form:form>
</div>
</body>
</html>

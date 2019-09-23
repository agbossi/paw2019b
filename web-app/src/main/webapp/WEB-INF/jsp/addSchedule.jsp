<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <head>
        <%@ page isELIgnored="false" %>
        <jsp:include page="base/adminnavbar.jsp" />

    </head>
    <body class="d-flex flex-column h-100">
        <h2>Doctor Information</h2>
        <div class="container">
            <c:url value="/addedSchedule" var="postPath"/>
            <form:form modelAttribute="scheduleForm" action="${postPath}" method="post">
                <div>
                    <form:label path="doctor">Doctor: </form:label>
                    <form:select path="doctor">
                        <c:forEach var="doctorClinic" items="${doctorClinics}">
                            <form:option value="${doctorClinic.doctor.license}"> <c:out value="${doctorClinic.doctor.name}"/> </form:option>
                        </c:forEach>
                    </form:select>
                    <form:errors path="doctor" element="p"/>
                </div>
                <div>
                    <form:label path="clinic">Clinic: </form:label>
                    <form:select path="clinic">
                        <c:forEach var="doctorClinic" items="${doctorClinics}">
                            <form:option value="${doctorClinic.clinic.id}"> <c:out value="${doctorClinic.clinic.name}"/> </form:option>
                        </c:forEach>
                    </form:select>
                    <form:errors path="clinic" element="p"/>
                </div>
                <div>
                    <form:label path="day">Day: </form:label>
                    <form:select path="day">
                        <form:option value="MONDAY"/>
                        <form:option value="TUESDAY"/>
                        <form:option value="WEDNESDAY"/>
                        <form:option value="THURSDAY"/>
                        <form:option value="FRIDAY"/>
                    </form:select>
                    <form:errors path="day" element="p"/>
                </div>
                <div>
                    <form:label path="hour">Day: </form:label>
                    <form:select path="hour">
                        <form:option value="8:00"/>
                        <form:option value="9:00"/>
                        <form:option value="10:00"/>
                        <form:option value="11:00"/>
                        <form:option value="12:00"/>
                        <form:option value="13:00"/>
                        <form:option value="14:00"/>
                        <form:option value="15:00"/>
                        <form:option value="16:00"/>
                        <form:option value="17:00"/>
                        <form:option value="18:00"/>
                        <form:option value="19:00"/>
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
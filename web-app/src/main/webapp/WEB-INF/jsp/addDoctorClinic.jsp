<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
    <head>
        <%@ page isELIgnored="false" %>
        <jsp:include page="base/adminnavbar.jsp" />
    </head>
    <body class="d-flex flex-column h-100">

        <h2>Add a Doctor to a Clinic</h2>
        <div class="container">
            <c:url value="/addedDoctorClinic" var="postPath"/>
            <form:form modelAttribute="doctorClinicForm" action="${postPath}" method="post">
                <div>
                    <form:label path="doctor">Doctor: </form:label>
                    <form:select path="doctor">
                        <c:forEach var="doctor" items="${doctors}">
                            <form:option value="${doctor.license}"> <c:out value="${doctor.firstName}"/> <c:out value="${doctor.lastName}"/> </form:option>
                        </c:forEach>
                    </form:select>
                    <form:errors path="doctor" element="p"/>
                </div>
                <div>
                    <form:label path="clinic">Clinic: </form:label>
                    <form:select path="clinic">
                        <c:forEach var="clinic" items="${clinics}">
                            <form:option value="${clinic.id}"> <c:out value="${clinic.name}"/> (<c:out value="${clinic.location.locationName}"/>) </form:option>
                        </c:forEach>
                    </form:select>
                    <form:errors path="clinic" element="p"/>
                </div>
                <div>
                    <form:label path="consultPrice">Consult Price: </form:label>
                    <form:input type="number" path="consultPrice"/>
                    <form:errors path="consultPrice" element="p"/>
                </div>
                <div>
                    <input type="submit" value="Add">
                </div>
            </form:form>
        </div>
    </body>
</html>
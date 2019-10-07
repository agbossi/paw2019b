<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
    <head>
        <%@ page isELIgnored="false" %>
        <jsp:include page="../base/navbar.jsp" />
    </head>
    <body class="d-flex flex-column h-100">

        <h2><spring:message code="add.doctor.to.clinic"/></h2>
        <div class="container">
            <c:url value="/doctor/addedDoctorClinic" var="postPath"/>
            <form:form modelAttribute="doctorClinicForm" action="${postPath}" method="post">
                <div>
                    <form:label path="clinic"><spring:message code="clinic"/> </form:label>
                    <form:select path="clinic">
                        <c:forEach var="clinic" items="${clinics}">
                            <form:option value="${clinic.id}"> <c:out value="${clinic.name}"/> (<c:out value="${clinic.location.locationName}"/>) </form:option>
                        </c:forEach>
                    </form:select>
                    <form:errors path="clinic" element="p"/>
                </div>
                <div>
                    <form:label path="consultPrice"><spring:message code="consult.price"/> </form:label>
                    <form:input type="number" path="consultPrice"/>
                    <form:errors path="consultPrice" element="p"/>
                </div>
                <div>
                    <input type="submit" value="<spring:message code="submit.add"/>">
                </div>
            </form:form>
        </div>
    </body>
</html>
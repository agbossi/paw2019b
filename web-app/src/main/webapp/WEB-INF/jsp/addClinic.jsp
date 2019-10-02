<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <%@ page isELIgnored="false" %>
    <jsp:include page="base/navbar.jsp" />

</head>
<body class="d-flex flex-column h-100">
<h2>Clinic Information</h2>
<div class="container">
    <c:url value="/admin/addedClinic" var="postPath"/>
    <form:form modelAttribute="clinicForm" action="${postPath}" method="post">
        <div>
            <form:label path="name">Name: </form:label>
            <form:input type="text" path="name"/>
            <form:errors path="name" element="p"/>
        </div>
        <div>
            <form:label path="location">Location: </form:label>
            <form:select path="location">
                <c:forEach var="location" items="${locations}">
                    <form:option value="${location.locationName}"/>
                </c:forEach>
            </form:select>
            <form:errors path="location" element="p"/>
        </div>
        <div>
            <input type="submit" value="Add">
        </div>
    </form:form>
</div>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <%@ page isELIgnored="false" %>
        <jsp:include page="base/adminnavbar.jsp" />
    </head>
    <body class="d-flex flex-column h-100">
        <h2>Specialty Information</h2>
        <div class="container">
            <c:url value="/addedSpecialty" var="postPath"/>
            <form:form modelAttribute="specialtyForm" action="${postPath}" method="post">
                <div>
                    <form:label path="name">Name: </form:label>
                    <form:input type="text" path="name"/>
                    <form:errors path="name" element="p"/>
                </div>
                <div>
                    <input type="submit" value="Add specialty!">
                </div>
            </form:form>
        </div>
    </body>
</html>
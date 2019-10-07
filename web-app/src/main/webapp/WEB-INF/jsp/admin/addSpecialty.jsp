<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
    <head>
        <%@ page isELIgnored="false" %>
        <jsp:include page="../base/navbar.jsp" />
    </head>
    <body class="d-flex flex-column h-100">
        <h2><spring:message code="sp.info"/> </h2>
        <div class="container">
            <c:url value="/admin/addedSpecialty" var="postPath"/>
            <form:form modelAttribute="specialtyForm" action="${postPath}" method="post">
                <div>
                    <form:label path="name"><spring:message code="name"/> </form:label>
                    <form:input type="text" path="name"/>
                    <form:errors path="name" element="p"/>
                </div>
                <div>
                    <input type="submit" value="<spring:message code="submit.add"/>">
                </div>
            </form:form>
        </div>
    </body>
</html>

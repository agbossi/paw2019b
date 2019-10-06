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
        <h2>Doctor Information</h2>
        <div class="container">
            <c:url value="/admin/addedDoctor" var="postPath"/>
            <form:form modelAttribute="doctorForm" action="${postPath}" method="post">
                <div>
                    <form:label path="firstName"><spring:message code="user.first.name"/> </form:label>
                    <form:input type="text" path="firstName"/>
                    <form:errors path="firstName" element="p"/>
                </div>
                <div>
                    <form:label path="lastName"><spring:message code="user.last.name"/> </form:label>
                    <form:input type="text" path="lastName"/>
                    <form:errors path="lastName" element="p"/>
                </div>
                <div>
                    <form:label path="specialty"><spring:message code="specialty"/> </form:label>
                    <form:select path="specialty">
                        <c:forEach var="specialty" items="${specialties}">
                            <form:option value="${specialty.specialtyName}"/>
                        </c:forEach>
                    </form:select>
                    <form:errors path="specialty" element="p"/>
                </div>
                <div>
                    <form:label path="license"><spring:message code="license"/> </form:label>
                    <form:input type="text" path="license"/>
                    <form:errors path="license" element="p"/>
                </div>
                <div>
                    <form:label path="phoneNumber"><spring:message code="phone.number"/> </form:label>
                    <form:input type="text" path="phoneNumber"/>
                    <form:errors path="phoneNumber" element="p"/>
                </div>
                <div>
                    <form:label path="email"><spring:message code="user.email"/> </form:label>
                    <form:input type="text" path="email"/>
                    <form:errors path="email" element="p"/>
                </div>
                <div>
                    <form:label path="password"><spring:message code="password"/> </form:label>
                    <form:input type="password" path="password" />
                    <form:errors path="password" element="p"/>
                </div>
                <div>
                    <form:label path="repeatPassword"><spring:message code="repeat.password"/> </form:label>
                    <form:input type="password" path="repeatPassword"/>
                    <form:errors path="repeatPassword" element="p"/>
                </div>
                <div>
                    <input type="submit" value="<spring:message code="submit.add"/>">
                </div>
            </form:form>
        </div>
    </body>
</html>
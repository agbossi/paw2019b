<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <%@ page isELIgnored="false" %>
        <jsp:include page="../base/navbar.jsp" />
    </head>
    <body>
        <a class="btn btn-outline-primary" href="<c:url value="/profile"/>"><spring:message code="profile.message"/></a>
        <div class="container">
            <c:url value="/editProfile" var="postPath"/>
            <form:form modelAttribute="form" action="${postPath}" method="post">
                <div>
                    <form:label path="firstName">First name:</form:label>
                    <form:input type="text" path="firstName"/>
                    <form:errors path="firstName" element="p"/>
                </div>
                <div>
                    <form:label path="lastName">Last name: </form:label>
                    <form:input type="text" path="lastName"/>
                    <form:errors path="lastName" element="p"/>
                </div>
                <div>
                    <form:label path="password">Change password: </form:label>
                    <form:input type="password" path="password" placeholder="********"/>
                    <form:errors path="password" element="p"/></div>
                </div>
                <div>
                    <form:label path="repeatPassword">Repeat password: </form:label>
                    <form:input type="password" path="repeatPassword"/>
                    <form:errors path="repeatPassword" element="p"/>
                </div>
                <div>
                    <form:label path="prepaid">Prepaid:</form:label>
                    <form:input type="text" path="prepaid" placeholder="Choose a prepaid"/>
                    <form:errors path="prepaid" element="p"/>
                </div>
                <div>
                    <form:label path="prepaidNumber">Prepaid Number: </form:label>
                    <form:input type="text" path="prepaidNumber" placeholder="Insert prepaid number"/>
                    <form:errors path="prepaidNumber" element="p"/>
                </div>
                </div>
                <input type="submit" value="Update personal information">
                </div>
            </form:form>

        </div>
    </body>
</html>

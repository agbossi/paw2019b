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
                    <form:label path="firstName"><spring:message code="user.first.name"/></form:label>
                    <form:input type="text" path="firstName"/>
                    <form:errors path="firstName" element="p"/>
                </div>
                <div>
                    <form:label path="lastName"><spring:message code="user.last.name"/> </form:label>
                    <form:input type="text" path="lastName"/>
                    <form:errors path="lastName" element="p"/>
                </div>
                <div>
                    <form:label path="password"><spring:message code="change.password"/> </form:label>
                    <form:input type="password" path="password" placeholder="********"/>
                    <form:errors path="password" element="p"/></div>
                </div>
                <div>
                    <form:label path="repeatPassword"><spring:message code="repeat.password"/> </form:label>
                    <form:input type="password" path="repeatPassword"/>
                    <form:errors path="repeatPassword" element="p"/>
                </div>
                <div>
                    <form:label path="prepaid"><spring:message code="patient.prepaid"/></form:label>
                    <form:input type="text" path="prepaid" placeholder="Choose a prepaid"/>
                    <form:errors path="prepaid" element="p"/>
                </div>
                <div>
                    <form:label path="prepaidNumber"><spring:message code="patient.prepaid.number"/> </form:label>
                    <form:input type="text" path="prepaidNumber" placeholder="Insert prepaid number"/>
                    <form:errors path="prepaidNumber" element="p"/>
                </div>
                </div>
                <input type="submit" value="<spring:message code="submit.update.patient.info"/>">
                </div>
            </form:form>
        </div>
    </body>
</html>

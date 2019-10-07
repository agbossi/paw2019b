<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <head>
        <%@ page isELIgnored="false" %>
        <jsp:include page="base/navbar.jsp" />
    </head>
    <body>
        <header>
        </header>
        <div class="container">
            <c:url value="/signUp" var="postPath"/>
            <form:form modelAttribute="signUpForm" action="${postPath}" method="post">
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
                    <form:label path="id"><spring:message code="user.id"/> </form:label>
                    <form:input type="text" path="id"/>
                    <form:errors path="id" element="p"/>
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
                    <form:label path="email"><spring:message code="user.email"/> </form:label>
                    <form:input type="text" path="email"/>
                    <form:errors path="email" element="p"/>
                </div>
                <div>
                    <form:label path="prepaid"><spring:message code="patient.prepaid"/></form:label>
                    <form:select type="select" path="prepaid" id="prepaid" onclick="handleClick(this.id)">
                        <form:option value="" ><spring:message code="no.prepaid"/></form:option>
                        <c:forEach var="prepaid" items="${prepaids}">
                            <form:option value="${prepaid.name}"/>
                        </c:forEach>
                    </form:select>
                    <form:errors path="prepaid" element="p"/>
                </div>
                <div>
                    <form:label path="prepaidNumber"><spring:message code="patient.prepaid.number"/> </form:label>
                    <form:input type="text" path="prepaidNumber" id="prepaidNumber" placeholder="Insert prepaid number" disabled="true"/>
                    <form:errors path="prepaidNumber" element="p"/>
                </div>
                <div>
                    <input type="submit" value="<spring:message code="submit.register"/>">
                </div>
            </form:form>
            <div>
                <a href="<c:url value="/login"/>"><spring:message code="log.in"/></a>
            </div>
        </div>
    </body>
</html>

<script type="text/javascript">
    function handleClick(clickedId)
    {
        if(clickedId == "prepaid") {
            if( document.getElementById('prepaid').value == "" ) {
                document.getElementById('prepaidNumber').disabled = true;
            }
            else {
                document.getElementById('prepaidNumber').disabled = false;
            }
        }
    }
</script>

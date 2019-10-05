<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
                    <form:label path="firstName">First name: </form:label>
                    <form:input type="text" path="firstName"/>
                    <form:errors path="firstName" element="p"/>
                </div>
                <div>
                    <form:label path="lastName">Last name: </form:label>
                    <form:input type="text" path="lastName"/>
                    <form:errors path="lastName" element="p"/>
                </div>
                <div>
                    <form:label path="id">Id: </form:label>
                    <form:input type="text" path="id"/>
                    <form:errors path="id" element="p"/>
                </div>
                 <div>
                    <form:label path="password">Password: </form:label>
                    <form:input type="password" path="password" />
                    <form:errors path="password" element="p"/>
                </div>
                <div>
                    <form:label path="repeatPassword">Repeat password: </form:label>
                    <form:input type="password" path="repeatPassword"/>
                    <form:errors path="repeatPassword" element="p"/>
                </div>
                <div>
                    <form:label path="email">Email: </form:label>
                    <form:input type="text" path="email"/>
                    <form:errors path="email" element="p"/>
                </div>
                <div>
                    <form:label path="prepaid">Prepaid:</form:label>
                    <form:select type="select" path="prepaid">
                        <form:option value="">None</form:option>
                        <c:forEach var="prepaid" items="${prepaids}">
                            <form:option value="${prepaid.name}"/>
                        </c:forEach>
                    </form:select>
                    <form:errors path="prepaid" element="p"/>
                </div>
                <div>
                    <form:label path="prepaidNumber">Prepaid Number: </form:label>
                    <form:input type="text" path="prepaidNumber" placeholder="Insert prepaid number"/>
                    <form:errors path="prepaidNumber" element="p"/>
                </div>
                <div>
                    <input type="submit" value="Register!">
                </div>
            </form:form>
            <div>
                <a href="<c:url value="/login"/>">Login</a>
            </div>
        </div>
    </body>
</html>

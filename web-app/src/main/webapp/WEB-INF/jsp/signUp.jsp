<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
                    <form:label path="password">Password: </form:label>
                    <form:input type="password" path="password" />
                    <form:errors path="password" element="p"/></div>
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
                    <form:label path="id">Id: </form:label>
                    <form:input type="text" path="id"/>
                    <form:errors path="id" element="p"/>
                </div>
                <div>
                    <form:label path="healthInsurance">Health insurance: </form:label>
                    <form:input type="text" path="healthInsurance"/>
                    <form:errors path="healthInsurance" element="p"/>
                </div>
                    <input type="submit" value="Register!">
                </div>
            </form:form>
            </div>
            <a href="/login">Login!</a>
            </div>
        </div>
    </body>
</html>

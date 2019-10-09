<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
    <%@ page isELIgnored="false" %>
    <jsp:include page="base/navbar.jsp" />
    <link href="<c:url value="/resources/css/admin.css" />" rel="stylesheet" type="text/css" />
</head>
<body class="admin-body">
    <div class="add-doctor-container-container">
        <div class="add-doctor-container">
            <c:url value="/login" var="loginUrl" />
            <form action="${loginUrl}" method="post" enctype="application/x-www-form-urlencoded"/>
            <div class="add-doctor-form-container">
                <div>
                    <div>
                        <div class="label-div">
                            <label for="email"><spring:message code="user.email"/> </label>
                        </div>
                        <div class="input-div">
                            <input id="email" name="email" type="text">
                        </div>
                    </div>
                    <div>
                        <div class="label-div">
                            <label for="password"><spring:message code="password"/> </label>
                        </div>
                        <div class="input-div">
                            <input id="password" name="password" type="password">
                        </div>
                    </div>
                    <div>
                        <div class="label-div">
                            <input name="rememberme" type="checkbox"/> <spring:message code="rememberme"/>
                        </div>
                    </div>
                </div>
                <div class="add-doctor-add-button">
                    <input class="submit-add log-in-submit" type="submit" value="<spring:message code="log.in"/>">
                </div>
            </div>
            <div class="log-in-errors">
                <c:if test="${errorMessage != null}">
                    <div id="error">
                        <b><c:out value="${errorMessage}"/></b>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</body>
</html>



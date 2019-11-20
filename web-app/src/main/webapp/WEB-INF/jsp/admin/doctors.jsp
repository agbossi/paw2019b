<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <%@ page isELIgnored="false" %>
    <jsp:include page="../base/navbar.jsp" />
    <link href="<c:url value="/resources/css/adminInfo.css" />" rel="stylesheet" type="text/css" />
</head>
<body class="admin-info-body">
    <div class="admin-info-header">
        <h5>
            <a class="p-2" href="<c:url value="/admin/addDoctor"/>">
                <spring:message code="add.doctor"/>
            </a>
        </h5>
    </div>
    <c:choose>
        <c:when test="${not empty objects}">
            <div class="admin-info-container">
                <c:forEach var="doctor" items="${objects}">
                    <div>
                        <h6><c:out value="${doctor.firstName}"/>, <c:out value="${doctor.lastName}"/> (<c:out value="${doctor.license}"/>)</h6>
                        <h6>
                            <div class="delete-box">
                                <b class="delete-element">
                                    <a href="<c:url value="/admin/deleteDoctor/${doctor.license}"/>">
                                        <input type="submit" value="<spring:message code="remove"/>" name="<spring:message code="remove"/>" onclick="return confirmSubmit()">
                                    </a>
                                </b>
                            </div>
                        </h6>
                    </div>
                </c:forEach>
            </div>
            <div class="pagination">
                <h6 class="prev">
                    <c:if test="${page > 0}">
                        <a  href="<c:url value="/admin/locations/${page-1}"/>">
                            <spring:message code="previously"/>
                        </a>
                    </c:if>
                </h6>
                <h6 class="next">
                    <c:if test="${page < (maxPage-1)}">
                        <a  href="<c:url value="/admin/locations/${page+1}"/>">
                            <spring:message code="next"/>
                        </a>
                    </c:if>
                </h6>
            </div>
        </c:when>
        <c:otherwise>
            <c:choose>
                <c:when test="${page == 0}">
                    <h5 class="no-results"><spring:message code="no.result"/></h5>
                </c:when>
                <c:otherwise>
                    <h5 class="no-results wrong-page"><spring:message code="no.results.wrong.page"/></h5>
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>
</body>
</html>

<script>
    function confirmSubmit()
    {
        var agree=confirm("Are you sure you want to remove this doctor?");
        if (agree)
            return true ;
        else
            return false ;
    }
</script>

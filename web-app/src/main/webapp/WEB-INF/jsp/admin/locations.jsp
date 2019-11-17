<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <%@ page isELIgnored="false" %>
    <jsp:include page="../base/navbar.jsp" />
    <link href="<c:url value="/resources/css/adminInfo.css" />" rel="stylesheet" type="text/css" />
</head>
    <body class="admin-info-body">
        <div class="admin-info-header">
            <h5>
                <a class="p-2" href="<c:url value="/admin/addLocation"/>">
                    <spring:message code="add.location"/>
                </a>
            </h5>
        </div>
         <c:choose>
            <c:when test="${not empty objects}">
                <div class="admin-info-container">
                    <c:forEach var="location" items="${objects}">
                        <div>
                            <h6><c:out value="${location.locationName}"/></h6>
                            <h6>
                                <div class="delete-box">
                                    <b class="delete-element">
                                        <div>
                                            <a href="<c:url value="/admin/deleteLocation/${location.locationName}"/>">
                                                <input type="submit" class="edit-button" value="<spring:message code="remove"/>" name="<spring:message code="remove"/>" onclick="return confirmSubmit()">
                                            </a>
                                        </div>
                                        <div>
                                            <a href="<c:url value="/admin/editLocation/${location.locationName}"/>">
                                                <input type="submit" class="edit-button go-back-button" value="<spring:message code="edit"/>" name="<spring:message code="edit"/>">
                                            </a>
                                        </div>
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
                        <c:if test="${page < (maxPage)}">
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
    function confirmSubmit() {
        var agree=confirm("Are you sure you want to remove this location?");
        if (agree)
            return true ;
        else
            return false ;
    }
</script>



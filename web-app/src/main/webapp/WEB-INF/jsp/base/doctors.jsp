<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
    <head>
        <%@ page isELIgnored="false" %>
        <link href="<c:url value="/resources/css/adminInfo.css" />" rel="stylesheet" type="text/css" />
        <link href="<c:url value="/resources/css/doctors.css" />" rel="stylesheet" type="text/css" />
        <jsp:include page="searchbar.jsp" />
    </head>
    <body class="d-flex flex-column h-100">
        <div class="container-container">
            <div class="container marketing " class="container-doctors">
                <c:choose>
                    <c:when test="${!empty doctors}">
                                <div class="doctor-row">
                                    <c:forEach var="doctor" items="${doctors}">
                                        <div class="single-doctor">
                                            <img class="doctor-picture" src="<c:url value="/images/${doctor.license}"/>" onerror="this.onerror=null;this.src='<c:url value="/resources/images/docpic.jpg" />';"/>
                                            <h5>
                                                <a class="p-2" href="<c:url value="/results/${doctor.license}"/>">
                                                <c:out value="${doctor.firstName}"/> <c:out value=" ${doctor.lastName}"/></a>
                                            </h5>
                                            <p>${doctor.specialty.specialtyName}</p>
                                        </div>
                                    </c:forEach>
                                </div>
                                <div class="pagination">
                                    <h6 class="prev">
                                        <c:if test="${page > 0}">
                                            <a  href="<c:url value="/doctors/${page-1}"/>">
                                                <spring:message code="previously"/>
                                            </a>
                                        </c:if>
                                    </h6>
                                    <h6 class="next">
                                        <c:if test="${page < (maxPage-1)}">
                                            <a  href="<c:url value="/doctors/${page+1}"/>">
                                                <spring:message code="next"/>
                                            </a>
                                        </c:if>
                                    </h6>
                                </div>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${page == 0}">
                                <h3 class="no-results"><spring:message code="no.results.found"/></h3>
                            </c:when>
                            <c:otherwise>
                                <h3 class="no-results wrong-page"><spring:message code="no.results.wrong.page"/></h3>
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </body>
</html>

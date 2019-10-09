<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
    <head>
        <%@ page isELIgnored="false" %>
        <link href="<c:url value="/resources/css/searchbar.css" />" rel="stylesheet" type="text/css" />
        <jsp:include page="navbar.jsp" />
        <script>
            function clinicByLocation() {

            }
        </script>
    </head>
    <body>
        <div class="search-bar">
            <div class="list-group list-group-flush">
                <c:url value="/results" var="postPath"/>
                <%--@elvariable id="searchForm" type="java"--%>
                <form:form modelAttribute="searchForm" action="${postPath}" method="post">

                <div class="list-group-item list-group-item-action">
                    <div>
                        <form:label path="location"><spring:message code="location"/></form:label>
                    </div>
                    <div>
                        <form:select path="location">
                            <form:option value=""><spring:message code="none"/></form:option>
                            <c:forEach var="location" items="${locations}">
                                <form:option value="${location.locationName}"/>
                            </c:forEach>
                        </form:select>
                        <form:errors path="location" element="p"/>
                        <br/>
                    </div>
                </div>
                <div class="list-group-item list-group-item-action">
                    <div>
                        <form:label path="specialty"><spring:message code="specialty"/></form:label>
                    </div>
                    <div>
                        <form:select path="specialty">
                            <form:option value=""><spring:message code="none"/></form:option>
                            <c:forEach var="specialty" items="${specialties}">
                                <form:option value="${specialty.specialtyName}"/>
                            </c:forEach>
                        </form:select>
                        <form:errors path="specialty" element="p"/>
                        <br/>
                    </div>
                </div>
                    <div class="list-group-item list-group-item-action">
                        <div>
                            <form:label path="prepaid"><spring:message code="prepaid"/></form:label>
                        </div>
                        <div>
                            <form:select path="prepaid">
                                <form:option value=""><spring:message code="none"/></form:option>
                                <c:forEach var="prepaid" items="${prepaids}">
                                    <form:option value="${prepaid.name}"/>
                                </c:forEach>
                            </form:select>
                            <form:errors path="prepaid" element="p"/>
                            <br/>
                        </div>
                    </div>
                    <div class="list-group-item">
                        <div>
                            <form:label path="firstName"><spring:message code="user.first.name"/></form:label>
                        </div>
                        <div>
                            <form:input type="text" path="firstName"/>
                            <form:errors path="firstName" element="p"/>
                            <br/>
                        </div>
                    </div>
                    <div class="list-group-item">
                        <div>
                            <form:label path="lastName"><spring:message code="user.last.name"/></form:label>
                        </div>
                        <div>
                            <form:input type="text" path="lastName"/>
                            <form:errors path="lastName" element="p"/>
                            <br/>
                        </div>
                    </div>
                    <div class="list-group-item">
                        <div>
                            <form:label path="consultPrice"><spring:message code="consult.price"/> </form:label>
                        </div>
                        <div>
                            <form:input type="number" path="consultPrice"/>
                            <form:errors path="consultPrice" element="p"/>
                            <br/>
                        </div>
                    </div>
                <div class="list-group-item">
                    <input type="submit" value="<spring:message code="submit.search"/>">
                </div>
                </form:form>
            </div>
        </div>
    </body>
</html>

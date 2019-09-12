<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
    <head>
        <%@ page isELIgnored="false" %>
        <link href="<c:url value="/resources/css/searchbar.css" />" rel="stylesheet" type="text/css" />
        <jsp:include page="navbar.jsp" />
    </head>
    <body>
        <div class="search-bar">
            <div class="list-group list-group-flush">

                <c:url value="/results" var="postPath"/>
                
                <%--@elvariable id="searchForm" type="java"--%>
                <form:form modelAttribute="searchForm" action="${postPath}" method="post">

                <div class="list-group-item list-group-item-action">
                    <div>
                        <form:label path="location">Location</form:label>
                    </div>
                    <div>
                        <form:select path="location">
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
                        <form:label path="specialty">Specialty</form:label>
                    </div>
                    <div>
                        <form:select path="specialty">
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
                        <form:label path="clinic">Clinic</form:label>
                    </div>
                    <div>
                        <form:input type="text" path="clinic"/>
                        <form:errors path="clinic" element="p"/>
                        <br/>
                    </div>
                </div>
                <div class="list-group-item list-group-item-action">
                    <div>
                        <p>Availability</p>
                    </div>
                    <div class="md-form">
                        <form:label path="from" for="inputFrom">From</form:label>
                        <form:input path="from" type="date" id="inputFrom" class="form-control"/>
                        <form:errors path="from" element="p"/>
                        <br/>
                    </div>
                    <div class="md-form">
                        <form:label path="to" for="inputTo">To</form:label>
                        <form:input path="to" type="date" id="inputTo" class="form-control"/>
                        <form:errors path="to" element="p"/>
                        <br/>
                    </div>
                </div>
                <div class="list-group-item">
                    <input type="submit" value="Search">
                </div>
                </form:form>
            </div>
        </div>
    </body>
</html>

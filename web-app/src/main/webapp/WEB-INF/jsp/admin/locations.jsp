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
                <a class="p-2 text-dark" href="<c:url value="/admin/addLocation"/>">
                    <b><spring:message code="add.location"/></b>
                </a>
            </h5>
        </div>
        <div class="admin-info-container">
            <c:forEach var="location" items="${locations}">
                <div id="location-information" onclick="locationEdit(this.id)">
                    <h6><c:out value="${location.locationName}"/></h6>
                    <h6>
                        <div class="delete-box">
                            <b class="delete-element">
                                <a href="<c:url value="/admin/deleteLocation/${location.locationName}"/>">
                                    <input type="submit" value="<spring:message code="remove"/>" name="<spring:message code="remove"/>" onclick="return confirmSubmit()">
                                </a>
                            </b>
                        </div>
                    </h6>
                </div>
            </c:forEach>
            <div id="location-edit" hidden="true">
                <div>
                    <c:url value="/admin/editLocation/{location.${location.locationName}" var="postPath"/>
                    <form:form modelAttribute="locationForm" action="${postPath}" method="post">
                        <form:label path="name"><spring:message code="name"/> </form:label>
                        <form:input type="text" path="name" placeholder="${location.locationName}"/>
                        <form:errors class="errors" path="name" element="p"/>
                    </form:form>
                </div>
                <h6>
                    <div class="delete-box">
                        <b class="delete-element">
                            <div>
                                <a href="<c:url value="/admin/editLocation/${location.locationName}"/>">
                                    <input type="submit" class="edit-button" value="<spring:message code="update"/>" name="<spring:message code="update"/>" onclick="return confirmUpdate()">
                                </a>
                            </div>
                            <div>
                                <input type="submit" class="edit-button go-back-button" value="<spring:message code="go.back"/>" name="<spring:message code="go.back"/>" id="go-back-edit" onclick="locationEdit(this.id)">
                            </div>
                        </b>
                    </div>
                </h6>
            </div>
        </div>
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

<script>
    function locationEdit(clickedId) {
        if(clickedId == 'location-information') {
            document.getElementById('location-information').hidden = true;
            document.getElementById('location-edit').hidden = false
        }
        if(clickedId == 'go-back-edit') {
            document.getElementById('location-information').hidden = false
            document.getElementById('location-edit').hidden = true
        }
    }
</script>

<script>
    function confirmUpdate() {
        var agree=confirm("Are you sure you want to change the name?");
        if (agree)
            return true ;
        else
            return false ;
    }
</script>



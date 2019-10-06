<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <%@ page isELIgnored="false" %>
        <jsp:include page="../base/navbar.jsp" />
    </head>
    <body>
        <form action="/insertDoctorProfileImage" method="post" enctype="multipart/form-data">
            <div>
                <div>
                    <spring:message code="upload.photo"/> <input type="file" name="photo"/>
                </div>
                <div>
                    <input type="submit" value="<spring:message code="submit.load.pic"/>"/>
                </div>
            </div>
        </form>
    </body>
</html>

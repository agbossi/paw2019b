<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ page isELIgnored="false" %>
    <jsp:include page="../base/navbar.jsp" />
</head>
<body>
    <form action="insertImage" method="post" enctype="multipart/form-data">
        <div>
            <div>
                Upload a profile photo: <input type="file" name="photo">
            </div>
            <div>
                <input type="submit" value="Submit">
            </div>
        </div>
    </form>
</body>
</html>

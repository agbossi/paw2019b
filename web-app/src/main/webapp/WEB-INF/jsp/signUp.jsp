<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <head>
        <%@ page isELIgnored="false" %>
        <jsp:include page="base/navbar.jsp" />
        <link href="<c:url value="/resources/css/admin.css" />" rel="stylesheet" type="text/css" />
        <link href="<c:url value="/resources/css/editDoctorProfile.css" />" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <c:url value="/signUp" var="postPath"/>
        <form:form modelAttribute="signUpForm" action="${postPath}" method="post">
            <div class="container">
                <div class="row">
                    <div class="col-6">
                        <div class="add-doctor-container-container">
                            <div class="add-doctor-form-container edit-doctor-profile-container">
                                <div>
                                    <div>
                                        <div class="label-div">
                                            <form:label path="firstName"><spring:message code="user.first.name"/> </form:label>
                                        </div>
                                        <div class="input-div">
                                            <h6><form:input path="firstName"/></h6>
                                        </div>
                                    </div>
                                    <form:errors class="errors" path="firstName" element="p"/>
                                </div>
                                <div>
                                    <div>
                                        <div class="label-div">
                                            <form:label path="lastName"><spring:message code="user.last.name"/> </form:label>
                                        </div>
                                        <div class="input-div">
                                            <h6><form:input path="lastName"/></h6>
                                        </div>
                                    </div>
                                    <form:errors class="errors" path="lastName" element="p"/>
                                </div>
                                <div>
                                    <div>
                                        <div class="label-div">
                                            <form:label path="email"><spring:message code="user.email"/> </form:label>
                                        </div>
                                        <div class="input-div">
                                            <h6><form:input path="email"/></h6>
                                        </div>
                                    </div>
                                    <form:errors class="errors" path="email" element="p"/>
                                </div>
                                <div>
                                    <div>
                                        <div class="label-div">
                                            <form:label path="id"><spring:message code="user.id"/> </form:label>
                                        </div>
                                        <div class="input-div">
                                            <h6><form:input path="id"/></h6>
                                        </div>
                                    </div>
                                    <form:errors class="errors" path="id" element="p"/>
                                </div>
                                <div>
                                    <div>
                                        <div class="label-div">
                                            <form:label path="password"><spring:message code="password"/></form:label>
                                            <form:label path="password"/>
                                        </div>
                                        <div class="input-div">
                                            <h6><form:input type="password" path="password"/></h6>
                                        </div>
                                    </div>
                                    <form:errors class="errors" path="password" element="p"/>
                                </div>
                                <div>
                                    <div>
                                        <div class="label-div">
                                            <form:label path="repeatPassword"><spring:message code="repeat.password"/></form:label>
                                            <form:label path="repeatPassword"/>
                                        </div>
                                        <div class="input-div">
                                            <h6><form:input type="password" path="repeatPassword"/></h6>
                                        </div>
                                    </div>
                                    <form:errors class="errors" path="repeatPassword" element="p"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-6">
                        <div class="add-doctor-container-container">
                            <div class="add-doctor-form-container edit-doctor-profile-container">
                                <div>
                                    <div>
                                        <div class="label-div">
                                            <form:label path="prepaid"><spring:message code="prepaid"/> </form:label>
                                        </div>
                                        <div class="input-div">
                                            <form:select class="select-input-div" path="prepaid" id="prepaid" onclick="handleClick(this.id)">
                                                <form:option value=""><spring:message code="no.prepaid"/></form:option>
                                                <c:forEach var="prepaid" items="${prepaids}">
                                                    <form:option value="${prepaid.name}"/>
                                                </c:forEach>
                                            </form:select>
                                        </div>
                                    </div>
                                    <form:errors class="errors" path="prepaid" element="p"/>
                                </div>
                                <div>
                                    <div>
                                        <div class="label-div">
                                            <form:label path="prepaidNumber"><spring:message code="patient.prepaid.number"/> </form:label>
                                        </div>
                                        <div class="input-div">
                                            <form:input type="text" disabled="true" id="prepaidNumber" path="prepaidNumber"/>
                                        </div>
                                    </div>
                                    <form:errors class="errors" path="prepaidNumber" element="p"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <br/><br/>
                <div class="profile-save-options">
                    <a href="<c:url value="/login"/>"><spring:message code="log.in"/></a> <input class="profile-save-changes" type="submit" value="<spring:message code="submit.register"/>">
                </div>
            </div>
        </form:form>
    </body>
</html>

<script type="text/javascript">
    function handleClick(clickedId)
    {
        if(clickedId == "prepaid") {
            if( document.getElementById('prepaid').value == "" ) {
                document.getElementById('prepaidNumber').disabled = true;
            }
            else {
                document.getElementById('prepaidNumber').disabled = false;
            }
        }
    }
</script>

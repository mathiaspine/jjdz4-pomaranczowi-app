<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${sessionScope.language == null}">
    <c:set var="language" scope="session" value="en_GB"/>
</c:if>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="fin-app" var="finAppLanguage"/>
<!DOCTYPE html>
<html lang="${language}">
<head>
    <meta charset="UTF-8"/>
    <title><fmt:message key="home.appName" bundle="${finAppLanguage}"/></title>
    <link rel="stylesheet" href="../css/style.css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"
          integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
    <script src="../js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="general/navbar.jsp"/>
<div class="container-fluid">
    <div class="row">
        <div class="content col col-sm-12 col-lg-8 offset-lg-2">

            <%-- Here you need to set total number of form steps --%>
            <c:set var="steps" scope="application" value="3"/>

            <h2><fmt:message key="home.step" bundle="${finAppLanguage}"/> <c:out value="${step} z ${steps}"/></h2>

            <c:set var="progress" scope="request" value="${(100/steps)*step}"/>

            <div class="progress">
                <div class="progress-bar progress-bar-striped bg-info progress-bar-animated" role="progressbar"
                     style="width: <c:out value="${progress}"/>%"
                     aria-valuenow="<c:out value="${progress}"/>"
                     aria-valuemin="0" aria-valuemax="100">
                </div>
            </div>

            <jsp:include page="forms/form-step-${step}.jsp"/>

        </div>
    </div>
</div>

<script src="../js/jquery-3.2.1.slim.min.js"></script>
<script src="../js/popper.min.js"></script>

<script>
    (function () {
        'use strict';

        window.addEventListener('load', function () {
            var form = document.getElementById('form');
            document.getElementById('next').addEventListener('click', function (event) {
                if (form.checkValidity() === false) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.classList.add('was-validated');
            }, false);
        }, false);
    })();
</script>

</body>
</html>
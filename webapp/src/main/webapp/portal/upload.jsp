<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
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

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/portal/home"><fmt:message key="home.appName" bundle="${finAppLanguage}"/></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown"
            aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="/portal/home"><fmt:message key="home.homePage" bundle="${finAppLanguage}"/> <span
                        class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#"><fmt:message key="home.contact" bundle="${finAppLanguage}"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/logout"><fmt:message key="home.logout" bundle="${finAppLanguage}"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="upload.jsp"><fmt:message key="home.upload" bundle="${finAppLanguage}"/></a>
            </li>
        </ul>

    </div>
    <jsp:include page="general/language-flags.jsp"/>
</nav>
<div class="container-fluid">
    <div class="row">
        <div class="col col-sm-12 col-lg-8 offset-lg-2" style="background-color: rgba(255,255,255,0.7)">


                <input type="file" name="dataFile" id="fileChooser"/><br/><br/>
                <input type="submit" value="Upload" />


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
<%--








<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
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
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/portal/home"><fmt:message key="home.appName" bundle="${finAppLanguage}"/></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown"
            aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="/portal/home"><fmt:message key="home.homePage" bundle="${finAppLanguage}"/> <span
                        class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#"><fmt:message key="home.contact" bundle="${finAppLanguage}"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/logout"><fmt:message key="home.logout" bundle="${finAppLanguage}"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="./upload.jsp"><fmt:message key="home.upload" bundle="${finAppLanguage}"/></a>
            </li>
        </ul>

    </div>
    <jsp:include page="general/language-flags.jsp"/>
</nav>




<form method="post" action="UploadServlet" enctype="multipart/form-data">
<div class="container-fluid">
        <div class="row">
            <div class="col col-sm-12 col-lg-8 offset-lg-2" style="background-color: rgba(255,255,255,0.7)">


                <h2><fmt:message key="upload.selectFile" bundle="${finAppLanguage}"/> </h2>

                <input type="file" name="dataFile" id="fileChooser"/><br/><br/>
                <input type="submit" value="Upload" />




            </div>
        </div>
    </div>






    <h4>

    </h4>

</form>

<script src="../js/jquery-3.2.1.slim.min.js"></script>
<script src="../js/popper.min.js"></script>

</body>
</html>


--%>
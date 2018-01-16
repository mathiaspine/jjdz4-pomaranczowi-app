<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8"/>
    <title>Analizator Finansowy</title>
    <link rel="stylesheet" href="../css/style.css"/>
    <link rel="stylesheet" href="../css/bootstrap.min.css"/>
    <script src="../js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/portal/home">Financial Analyser</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown"
            aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="#">Strona główna <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Kontakt</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/logout">Wyloguj</a>
            </li>
        </ul>
    </div>
</nav>
<div class="container-fluid">
    <div class="row">
        <div class="col col-sm-12 col-lg-8 offset-lg-2" style="background-color: rgba(255,255,255,0.7)">

            <c:set var="steps" scope="application" value="4"/>
            <h2>Krok <c:out value="${sessionScope.step} z ${applicationScope.steps}"/></h2>

            <c:choose>
                <c:when test="${sessionScope.step == 0}">
                    <c:set var="progress" scope="session" value="0"/>
                </c:when>
                <c:when test="${sessionScope.step == 1}">
                    <c:set var="progress" scope="session" value="25"/>
                </c:when>
                <c:when test="${sessionScope.step == 2}">
                    <c:set var="progress" scope="session" value="50"/>
                </c:when>
                <c:when test="${sessionScope.step == 3}">
                    <c:set var="progress" scope="session" value="75"/>
                </c:when>
                <c:when test="${sessionScope.step == 4}">
                    <c:set var="progress" scope="session" value="100"/>
                </c:when>
            </c:choose>

            <div class="progress">
                <div class="progress-bar progress-bar-striped bg-info progress-bar-animated" role="progressbar"
                     style="width: <c:out value="${sessionScope.progress}"/>%"
                     aria-valuenow="<c:out value="${sessionScope.progress}"/>"
                     aria-valuemin="0" aria-valuemax="100"></div>
            </div>

            <c:choose>
                <c:when test="${sessionScope.step == 0}">
                    <%@include file="form-step-0.jsp" %>
                </c:when>
                <c:when test="${sessionScope.step == 1}">
                    <%@include file="form-step-1.jsp" %>
                </c:when>
                <c:when test="${sessionScope.step == 2}">
                    <%@include file="form-step-2.jsp" %>
                </c:when>
                <c:when test="${sessionScope.step == 3}">
                    <%@include file="form-step-3.jsp" %>
                </c:when>
                <c:when test="${sessionScope.step == 4}">
                    <%@include file="analysis.jsp" %>
                </c:when>
            </c:choose>

        </div>
    </div>
</div>

<script src="../js/jquery-3.2.1.slim.min.js"></script>
<script src="../js/popper.min.js"></script>
</body>
</html>
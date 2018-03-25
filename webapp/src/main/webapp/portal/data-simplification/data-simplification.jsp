<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="fin-app" var="finAppLanguage"/>

<h4>
    <fmt:message key="${dataSimplificationMessage}" bundle="${finAppLanguage}"/> ${sessionScope.code}
</h4>
<h5>
    <fmt:message key="dataSimplification.period" bundle="${finAppLanguage}"/>
    <c:out value="${startDate}"/> - <c:out value="${endDate}"/>
</h5>
<jsp:include page="../general/table-with-data-simplified.jsp"/>

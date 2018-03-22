<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<table class="table">
    <tr>
        <th></th>
        <th colspan="2">OPEN</th>
        <th colspan="2">LOW</th>
        <th colspan="2">HIGH</th>
        <th colspan="2">CLOSE</th>
    </tr>
    <tr>
        <td></td>
        <td>max</td>
        <td>min</td>
        <td>max</td>
        <td>min</td>
        <td>max</td>
        <td>min</td>
        <td>max</td>
        <td>min</td>
    </tr>

    <c:forEach var="singlePeriodPrice" items="${periodPriceList}">
        <tr>
            <td><c:out value="${singlePeriodPrice.getPeriod()}"/>:</td>
            <td><c:out value="${singlePeriodPrice.getMaxOpen()}"/></td>
            <td><c:out value="${singlePeriodPrice.getMinOpen()}"/></td>
            <td><c:out value="${singlePeriodPrice.getMaxLow()}"/></td>
            <td><c:out value="${singlePeriodPrice.getMinLow()}"/></td>
            <td><c:out value="${singlePeriodPrice.getMaxHigh()}"/></td>
            <td><c:out value="${singlePeriodPrice.getMinHigh()}"/></td>
            <td><c:out value="${singlePeriodPrice.getMaxClose()}"/></td>
            <td><c:out value="${singlePeriodPrice.getMinClose()}"/></td>
        </tr>
    </c:forEach>

</table>
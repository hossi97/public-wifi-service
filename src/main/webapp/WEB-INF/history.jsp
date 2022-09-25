<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   <!-- -->

<html>
    <head>
        <title>위치 히스토리 목록</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="/resources/css/index.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <h1>와이파이 정보 구하기</h1>
        <%@ include file="./common/menuBar.jsp"%>
        <table class="history-table" style="able-layout: fixed; width: 1650px">
            <thead>
            <colgroup>
                <col style="width: 100px">
                <col style="width: 400px">
                <col style="width: 400px">
                <col style="width: 600px">
                <col style="width: 200px">
            </colgroup>
            <tr>
                <th class="history-info">ID</th>
                <th class="history-info">x좌표</th>
                <th class="history-info">y좌표</th>
                <th class="history-info">조회일자</th>
                <th class="history-info">비고</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="history">
                <tr>
                    <td><c:out value="${history.id}"/></td>
                    <td><c:out value="${history.lat}"/></td>
                    <td><c:out value="${history.lnt}"/></td>
                    <td><c:out value="${history.date}"/></td>
                    <td>
                        <button type="button" class="deleteBtn">삭제</button>
                    </td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
        <script src="/resources/js/history.js"></script>
    </body>
</html>
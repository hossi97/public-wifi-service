<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   <!-- -->

<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <title>Title</title>
</head>
<body>
<%@ include file="./common/menuBar.jsp"%>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>x좌표</th>
            <th>y좌표</th>
            <th>조회일자</th>
            <th>비고</th>
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
    </body>
</html>

<script>
    $(".deleteBtn").click(function () {
        let checkBtn = $(this);
        let tr = checkBtn.parent().parent();
        let td = tr.children();
        let historyId = td.eq(0).text();

        //서블릿 호출
        $.ajax({
            type: "post",
            url : "http://localhost:8080/RemoveHistory?historyId=" + historyId  // 포트 변경하기, 여러개는 & 이용
        }).done(function () {
            location.reload();
        })
    })
</script>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>와이파이 정보 구하기</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="/resources/css/index.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <h1>와이파이 정보 구하기</h1>
        <%@ include file="/WEB-INF/common/menuBar.jsp" %>
        <form class="location-form" id="locationForm" action="/NearWifi" method="post">
            <label>LAT:</label>
            <label>
                <input id="x-coordinate" type="text" name="lat" value="${lat}" placeholder="X좌표"/>
            </label>

            <label>, LNT:</label>
            <label>
                <input id="y-coordinate" type="text" name="lnt" value="${lnt}" placeholder="Y좌표"/>  <!-- value 를 없이도 체크해보자 -->
            </label>

            <button id="myLocationButton" type="button">내 위치 가져오기</button>
            <button id="nearWifiButton" class="getNearBtn">근처 WIFI 정보 보기</button>
        </form>

        <table class="wifi-table" style="able-layout: fixed; width: 1650px">
            <colgroup>
                <col style="width: 76px">
                <col style="width: 100px">
                <col style="width: 60px">
                <col style="width: 116px">
                <col style="width: 108px">
                <col style="width: 180px">
                <col style="width: 88px">
                <col style="width: 132px">
                <col style="width: 112px">
                <col style="width: 92px">
                <col style="width: 100px">
                <col style="width: 72px">
                <col style="width: 80px">
                <col style="width: 96px">
                <col style="width: 80px">
                <col style="width: 80px">
                <col style="width: 92px">
            </colgroup>
            <thead>
            <tr>
                <th class="wifi-info">거리(Km)</th>
                <th class="wifi-info">관리번호</th>
                <th class="wifi-info">자치구</th>
                <th class="wifi-info">와이파이명</th>
                <th class="wifi-info">도로명주소</th>
                <th class="wifi-info">상세주소</th>
                <th class="wifi-info">설치위치(층)</th>
                <th class="wifi-info">설치유형</th>
                <th class="wifi-info">설치기관</th>
                <th class="wifi-info">서비스구분</th>
                <th class="wifi-info">망종류</th>
                <th class="wifi-info">설치년도</th>
                <th class="wifi-info">실내외구분</th>
                <th class="wifi-info">WIFI접속환경</th>
                <th class="wifi-info">X좌표</th>
                <th class="wifi-info">Y좌표</th>
                <th class="wifi-info">작업일자</th>
            </tr>
            </thead>
            <tbody>
            <c:choose>
                <c:when test="${list.size() > 0}">
                    <c:forEach items="${list}" var="wifi">
                        <tr>
                            <td><c:out value="${wifi.distance}"/></td>
                            <td><c:out value="${wifi.mgrNo}"/></td>
                            <td><c:out value="${wifi.wrdofc}"/></td>
                            <td><c:out value="${wifi.mainNm}"/></td>
                            <td><c:out value="${wifi.adres1}"/></td>
                            <td><c:out value="${wifi.adres2}"/></td>
                            <td><c:out value="${wifi.floor}"/></td>
                            <td><c:out value="${wifi.ty}"/></td>
                            <td><c:out value="${wifi.mby}"/></td>
                            <td><c:out value="${wifi.svcSe}"/></td>
                            <td><c:out value="${wifi.cmcwr}"/></td>
                            <td><c:out value="${wifi.year}"/></td>
                            <td><c:out value="${wifi.door}"/></td>
                            <td><c:out value="${wifi.remars3}"/></td>
                            <td><c:out value="${wifi.lat}"/></td>
                            <td><c:out value="${wifi.lnt}"/></td>
                            <td><c:out value="${wifi.dttm}"/></td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <td colspan="17">위치 정보를 입력한 후에 조회해 주세요.</td>
                </c:otherwise>
            </c:choose>
            </tbody>
        </table>
        <script src="/resources/js/index.js"></script>
    </body>
</html>
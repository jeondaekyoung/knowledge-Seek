<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" >
    <meta charset="UTF-8">
    <meta name="apple-mobile-web-app-capable" content="yes" >
    <meta name="viewport" content="user-scalable=no, width=device-width, initial-scale=1.0, maximum-scale=1.0">
	<title>영어학습알람</title>
	
	<style>
        *{padding:0; margin: 0; border-spacing: 0; border-collapse:collapse; font-size: 12px; font-family: 'malgun gothic', sans-serif; text-decoration: none}
        #wrap {width: 100%; height: 100%; display: table}
        .alarm_eng {background: url(${pageContext.request.contextPath }/resources/images/bg/bg_eng.jpg); width:100%; height: 320px; overflow:hidden; display: table}
        .alarm_eng>div {vertical-align: middle; display: table-cell}
        .alarm_eng p.script {font-size: 36px; font-weight: bold; color: #fff; text-align: center;}
        .alarm_eng p.script_mean {font-size: 20px; font-weight: bold;text-align: center;}
        .btnPlay {}
        .lastAlarm {width: 100%; height:20%; display: table}        
        .lastAlarm div {width: 33.3%; vertical-align: middle; text-align: center; display: table-row;}
        .lastAlarm div:first-child p {font-size:20px}
        .lastAlarm div:nth-child(2) p {font-size:12px}
        .lastAlarm div:nth-child(3) p {font-size:12px}
        .lastAlarm div p {padding: 20px 0; color: #424242; font-weight: bold; display: table-cell;}
        .lastAlarm div p:first-child {background: #61d2d6;}
        .lastAlarm div p:nth-child(2) {background: #9bf0e9;}
        .lastAlarm div p:nth-child(3) {background: #ede5e2;}
    </style>
    
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/vjs/and/eng.js"></script>
</head>

<body>
    <div id="wrap">
        <div class="alarm_eng">
            <div>
                <p class="script_mean">오늘의 펀펀 영어</p>
                <p class="script">-</p>
                <p class="script">${eng.eng_sentence}</p>
                <p class="script_mean">${eng.eng_mean}</p>
            </div>
        </div>
        <div class="btnPlay"></div>
        
       	<div class="lastAlarm">
       		<div>
       			<c:forEach var="engs" items="${engs }">
       				<p>${engs.today_date }</p>
       			</c:forEach>
       		</div>
       		<div>
       			<c:forEach var="engs" items="${engs }">
       				<p>${engs.eng_sentence }</p>
       			</c:forEach>
       		</div>
       		<div>
       			<c:forEach var="engs" items="${engs }">
       				<p>${engs.eng_mean }</p>
       			</c:forEach>
       		</div>
       	</div> 
    </div>
    
    <%-- <audio src="${pageContext.request.contextPath }/fileupload/sound/${eng.eng_sound_server}" hidden="true" autoplay loop></audio> --%>
    <input type="text" id="audiofile" value="${pageContext.request.contextPath }/fileupload/sound/${eng.eng_sound_server}" hidden="true" >
    <%-- <audio id="audio" controls="controls" autoplay="autoplay" loop="loop">
    <source src="${pageContext.request.contextPath }/resources/${eng.eng_sound_server}" type="audio/mp3" />
    </audio> --%>

</body>
</html>
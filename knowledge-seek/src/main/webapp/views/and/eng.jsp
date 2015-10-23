<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" >
    <meta charset="UTF-8">
    <meta name="apple-mobile-web-app-capable" content="yes" >
    <meta name="viewport" content="user-scalable=no, width=device-width, initial-scale=1.0, maximum-scale=1.0">
	<title>영어학습알람</title>
	
	<style>
        *{padding:0; margin: 0; border-spacing: 0; border-collapse:collapse; font-size: 12px; font-family: 'malgun gothic', sans-serif; text-decoration: none; list-style:none}
        #wrap {width: 100%; height: 100%; display: table}
        .alarm_eng {background: url(${pageContext.request.contextPath }/resources/images/bg/bg_eng2.jpg); width:100%; height: 320px; overflow:hidden; display: table; opacity:0.7;}
        .alarm_eng>div {vertical-align: middle; display: table-cell}
        .alarm_eng p.script {font-size: 36px; font-weight: bold; color: #fff; text-align: center; text-shadow: 2px 2px 5px #333;}
        .alarm_eng p.script_mean {font-size: 20px; font-weight: bold;text-align: center;}
        
        .lastAlarm {position:relative;left:0px;overflow:hidden;}
        .lastAlarm .slide {width: 80%; height: 90px; position: relative; left: 0; overflow-x: scroll; float: left}
        .lastAlarm .slideContent {width: 100%; position: relative; left: 0;}
        .lastAlarm .slideContent ul{width: 300%}
        .lastAlarm .slideContent ul li {float:left;width:11%;}
        .lastAlarm .slideContent ul li div {background: #F3E66D; color:#444; padding: 20px 0; margin-right: 1%; text-align: center; font-size: 20px; font-weight: bold}
        .lastAlarm .fl,
        .lastAlarm .fr {width: 10%; margin-top: 15px; cursor:pointer}        
        .lastAlarm .fl {float: left;}
        .lastAlarm .fr {float: right;}
        .productImg .fl img,
        .productImg .fr img {width: 100%}
    </style>
    
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/vjs/and/eng.js"></script>
	<script type="text/javascript">
		/* 버튼 클릭시 페이지 이동 자바스크립트 */
		$(document).ready(function(){
	        $("#btnPrev").click(function() {
	            //현재 left값 구하기
	            var left = parseInt($(".imgContent").css("left"));
	            if(left >= 0) return;
	            left += 300;
	            var movePos = Math.floor(left/300.0)*300; 
	            $(".imgContent").animate({left : movePos}, 1000);
	        });
	        $("#btnNext").click(function() {
	            var left = parseInt($(".imgContent").css("left"));
	            if(left <= -1000) return;
	            left -= 300;
	            var movePos = Math.ceil(left/300.0)*300;
	            $(".imgContent").animate({left : movePos}, 1000);
	        });
	    });
    </script>
</head>

<body>
    <div id="wrap">
        <div class="alarm_eng">
            <div>
                <p class="script">ㅡ</p>
                <p class="script_mean">오늘의 펀펀 영어</p>
                <p class="script">${eng.eng_sentence}</p>
                <p class="script_mean">${eng.eng_mean}</p>
            </div>
        </div>
        
        
       	<div class="lastAlarm">
	       	<div class="fl" id="btnPrev">	<!-- 이전 페이지로 -->
	             <img src="${pageContext.request.contextPath}/resources/images/icon/arrow_left.png" alt="Prev">
	        </div>
	        
	      	<c:set var="engsLength" value="${fn:length(engs) }"></c:set>
	       		<div class="slide">	<!-- 9일치 영어 알람 목록 슬라이드 -->
	       			<c:forEach var="i" begin="0" end="${engsLength-1 }" varStatus="status">
       				<c:set var="eng" value="${engs[engsLength-i-1 ]}" />
       				<div class="slideContent">
	                    <ul>
	                        <li><div><fmt:formatDate value="${eng.today_date }" pattern="MM/dd" /></div></li>
                        </ul>
                    </div>
       			</c:forEach>
       		</div>
       		
            <div class="fr" id="btnNext">	<!-- 다음 페이지로 -->
                <img src="${pageContext.request.contextPath}/resources/images/icon/arrow_right.png" alt="Next">
            </div>
       		
       		<%-- <div>
       			<c:forEach var="i" begin="0" end="${engsLength-1 }" varStatus="status">
       				<c:set var="eng" value="${engs[engsLength-i-1 ]}" />
       				<p>${fn:substring(eng.eng_sentence, 0, 10) }~</p>
       				<p>${eng.eng_sentence }</p>
       			</c:forEach>
       		</div>
       		<div>
       			<c:forEach var="i" begin="0" end="${engsLength-1 }" varStatus="status">
       				<c:set var="eng" value="${engs[engsLength-i-1 ]}" />
       				<p>${fn:substring(eng.eng_mean, 0, 8) }~</p>
       				<p>${eng.eng_mean }</p>
       			</c:forEach>
       		</div> --%>
       		
       		
       	</div> 
    </div>
    
    <%-- <audio src="${pageContext.request.contextPath }/fileupload/sound/${eng.eng_sound_server}" hidden="true" autoplay loop></audio> --%>
    <input type="text" id="audiofile" value="${pageContext.request.contextPath }/fileupload/sound/${eng.eng_sound_server}" hidden="true" >
    <%-- <audio id="audio" controls="controls" autoplay="autoplay" loop="loop">
    <source src="${pageContext.request.contextPath }/resources/${eng.eng_sound_server}" type="audio/mp3" />
    </audio> --%>

</body>
</html>
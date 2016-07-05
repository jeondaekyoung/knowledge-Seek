<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!Doctype HTML>
<html>
<head>
    <jsp:include page="head.jsp" flush="false" />
    <script type="text/javascript">
    $(document).ready(function() {
		$('#fullpage').fullpage ({
			verticalCentered: false,
			anchors: ['firstPage', 'secondPage', '3rdPage'],
			menu: '#menu',
			scrollingSpeed: 1500,
         
			//to avoid problems with css3 transforms and fixed elements in Chrome, as detailed here: https://github.com/alvarotrigo/fullPage.js/issues/208
			css3:false
		});	
		});
	</script>
</head>
    
<body>
    <!-- UI Object -->
    <div id="wrap"><!--  style="display:none;" -->
        <!-- header -->
        <jsp:include page="top.jsp" flush="false" />
        <!-- //header -->
        
        <div id="fullpage">
			<div class="section colfff" id="section0" >
				<h1 class="pt46 shadow fadeInLeft animated">Open Innovation</h1>
                <p class="pt26 shadow fadeInLeft animated">혁신을 바탕으로 지식-IT사업을 선도하는 리더가 되겠습니다.</p>
                <div class="biz pt20 shadow fadeInUp animated">
                  	<a href="<c:url value='/homepage/aboutUs.jsp'/>"><span>자세히 보기</span></a>
                </div>
			</div>
			            
			<div class="section" id="section1">
			    <div class="slide" id="slide1">
			    	<!-- <p class="pt18 shadow colfff">사업소개</p> -->
			    	<h1 class="shadow colfff">IT</h1>
			    	<div class="wrap">
				    	<div class="div3 b1 hover">
				    		<p class="pt26 shadow colfff">솔루션 제작
				    		<br><span class="pt20 shadow colfff">SI / 웹 솔루션 / 교육용 소프트웨어</span></p>
				    	</div>
				    	<div class="div3 b2 hover">
				    		<p class="pt26 shadow colfff">어플리케이션 제작
				    		<br><span class="pt20 shadow colfff">IoT 어플리케이션 / 교육 어플리케이션</span></p>
				    	</div>
				    	<div class="div3 b3 hover">
				    		<p class="pt26 shadow colfff">홈페이지 제작
				    		<br><span class="pt20 shadow colfff">기업 홈페이지 / 쇼핑몰 구축</span></p>
				    	</div>
			    	</div>
			    </div>
			    
			    <div class="slide" id="slide2">
			    	<!-- <p class="pt18 shadow colfff">사업소개</p> -->
			    	<h1 class="shadow colfff">유아용 성장관리 솔루션</h1>
			    	<div class="div2">
			    		<div class="img"></div>
			    		<div class="img2"></div>
			    		<p class="pt20 col777">
			    			2015년 중소기업진흥공단 청년창업사관학교 5기 선발
			    			<br>2016년 미래창조과학부 본투글로벌 스타트업캠퍼스 선발
			    		</p>
			    		<a href="http://phyctogram.com" class="btn btn-info">제품 보러가기</a>
			    	</div>
			    </div>
			    
			    <div class="slide" id="slide3">
			    	<!-- <p class="pt18 shadow colfff">사업소개</p> -->
			    	<h1 class="shadow colfff">교육</h1>
			    	<div class="div4">
			    		<div class="pt36 colfff shadow hover">진로교육</div>
			    		<div class="pt36 colfff shadow hover">취업교육</div>
			    		<div class="pt36 colfff shadow hover">창업교육</div>
			    		<div class="pt36 colfff shadow hover">NCS교육</div>
			    	</div>
			    </div>
			</div>
			
			<div class="section" id="section2">
				<h1 class="pt18 shadow colfff">CONTACT</h1>
				<!-- 지도 API -->
                <div class="addr">
	                <div id="map" class="box-map"></div>                
                </div>

                <script type="text/javascript" src="http://openapi.map.naver.com/openapi/naverMap.naver?ver=2.0&key=3458891285f3e9f9c5aa09005e704c79"></script>
                <!-- <script type="text/javascript" src="http://openapi.map.naver.com/openapi/naverMap.naver?ver=2.0&key=a2087383a7b455ac2da93ccd02ba28b6"></script> -->

                <script type="text/javascript">
                    var oSeoulCityPoint = new nhn.api.map.LatLng(37.4046237,127.1058193); //좌표값 ( 위도, 경도 )
                    var defaultLevel = 11;
                    var oMap = new nhn.api.map.Map(document.getElementById('map'), { 
                                    point : oSeoulCityPoint,
                                    zoom : defaultLevel,
                                    enableWheelZoom : true,
                                    enableDragPan : true,
                                    enableDblClickZoom : false,
                                    mapMode :0,
                                    activateTrafficMap : false,
                                    activateBicycleMap : false,
                                    minMaxLevel : [ 1, 14 ],
                                    size : new nhn.api.map.Size(1920, 450) });

                    var oSize = new nhn.api.map.Size(28, 37);
                    var oOffset = new nhn.api.map.Size(14, 37);
                    var oIcon = new nhn.api.map.Icon('http://static.naver.com/maps2/icons/pin_spot2.png', oSize, oOffset);

                    var oMarker = new nhn.api.map.Marker(oIcon, { title : '(주)나리지식앤컴퍼니' });  //마커를 생성한다 
                    oMarker.setPoint(oSeoulCityPoint); //마커의 좌표를 oPoint 에 저장된 좌표로 지정한다

                    oMap.addOverlay(oMarker); //마커를 네이버 지도위에 표시한다

                    var oLabel = new nhn.api.map.MarkerLabel(); // 마커 라벨를 선언한다. 
                    oMap.addOverlay(oLabel); // - 마커의 라벨을 지도에 추가한다. 
                    oLabel.setVisible(true, oMarker); // 마커의 라벨을 보이게 설정한다.
                </script>

                <!-- //지도 API -->
                <div class="addr">
                    <p class="pt20 shadow colfff"><i class="fa fa-phone fa-3x icon-muted"></i><br>070-8624-4536</p>
                	<p class="pt20 shadow colfff"><i class="fa fa-map-marker fa-3x icon-muted"></i><br>경기도 성남시 분당구 판교로289번길 20<br>3동 310호(삼평동, 스타트업캠퍼스)</p>
                	<p class="pt20 shadow colfff"><i class="fa fa-envelope-o fa-3x icon-muted"></i><br>seek-knowledge@knowledge-seek.com</p>
                </div>
                
			</div>
		</div>
        
        <!-- footer -->
            <jsp:include page="bottom.jsp" flush="false" />
        <!-- //footer -->
    </div>
    <!-- //UI Object -->
    <jsp:include page="script.jsp" flush="false" />
    
    <script>
		$(document).ready(function() {
			$('.hover').hover(function(){
				$(this).stop().animate({'background-size': '205%' }, 800);
			});
	
			$('.hover').mouseleave(function(){
				$(this).stop().animate({'background-size': '175%' }, 200);
			});
		});
	</script>
</body>
</html>
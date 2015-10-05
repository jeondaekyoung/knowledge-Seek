<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!Doctype HTML>
<html>
<head>
    <title>(주)나리지식앤컴퍼니</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/index.js"></script>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/icon/favicon.ico">
    <link type="text/css" href="${pageContext.request.contextPath}/resources/css/reset.css" rel="stylesheet">
    <link type="text/css" href="${pageContext.request.contextPath}/resources/css/index.css" rel="stylesheet">
    <link type="text/css" href="${pageContext.request.contextPath}/resources/css/mobile.css" rel="stylesheet">
    
</head>
    
<body>
    <!-- UI Object -->
    <div id="wrap">
        <!-- header -->
        <div id="header">
            <div class="sta_wrap">
                <h1><a href="index.jsp"><img src="${pageContext.request.contextPath}/resources/images/logo/logo_cooper.png" alt="나리지식"></a></h1>    
                
                 <div class="gnb" style="float:right">
                    <ul>
                        <li><a href="aboutUs01.jsp" id="gnb1">회사소개</a></li>
                        <li><a href="business01.jsp" id="gnb2">사업영역</a></li>
                        <li><a href="resource01.jsp" id="gnb3">자료실</a></li>
                        <li><a href="customer.jsp" id="gnb4">고객센터</a></li>
                    </ul>
                </div>
                
                <div class="nav_btn"><img class="m_btn" src="${pageContext.request.contextPath}/resources/images/icon/m_menu.png" alt="메뉴보이기"></div>
            </div>
           
            
            <div class="nav_wrap">
                <div class="nav">
                    <div id="subMenu">
                        <div class="subContent">
                            <ul id="sub1" style="margin-left: 54%;">
                                <li><a href="aboutUs01.jsp">인사말</a></li>
                                <li><a href="aboutUs02.jsp">조직도</a></li>
                                <li><a href="aboutUs03.jsp">오시는길</a></li>
                            </ul>
                            <ul id="sub2" style="margin-left: 64%;">
                                <li><a href="business01.jsp">솔루션 / 홈페이지 제작</a></li>
                                <li><a href="business02.jsp">취업교육</a></li>
                                <li><a href="business03.jsp">정부 R&amp;D 과제</a></li>
                            </ul>
                            <ul id="sub3" style="margin-left: 78%;">
                                <li><a href="resource01.jsp">취업교육자료</a></li>
                            </ul>
                            <ul id="sub4" style="margin-left: 90%;">
                                <li><a href="customer.jsp">문의하기</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="m_nav">
                    <div id="m_subMenu">
                        <div class="m_subContent">
                            <ul>
                                <li class="gnbTit">회사소개</li>
                                <li><a href="aboutUs01.jsp">인사말</a></li>
                                <li><a href="aboutUs02.jsp">조직도</a></li>
                                <li><a href="aboutUs03.jsp">오시는길</a></li>
                                
                                <li class="gnbTit">사업영역</li>
                                <li><a href="business01.jsp">솔루션 / 홈페이지 제작</a></li>
                                <li><a href="business02.jsp">취업교육</a></li>
                                <li><a href="business03.jsp">정부 R&amp;D 과제</a></li>
                                
                                <li class="gnbTit">자료실</li>
                                <li><a href="resource01.jsp">취업교육자료</a></li>
                            
                                <li class="gnbTit">고객센터</li>
                                <li><a href="customer.jsp">문의하기</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- //header -->
        
        
        <!-- container -->
        <div id="container">
            <!-- content -->
            <div id="content1">
                <div class="introduce">
                    <div class="open">
                        <p class="pt14 shadow">회사소개</p>
                        <p class="pt36 shadow lh6">ㅡ</p>
                        <p class="pt18 shadow">Open Innovation을 실행하는 곳,<br>
                            <span class="pt22">(주)나리지식&amp;컴퍼니입니다</span></p>
                        <p class="pt14 col000">(주)나리지식&amp;컴퍼니는 솔루션 개발과 교육 컨설팅, 지식인들의 강연 등<br>
                            지식-교육 사업을 통해 Open Innovation을 실현시키고 있습니다.</p>
                        <p class="pt14"><a href="aboutUs01.jsp" class="ghostBtn1">자세히 보기</a></p>
                    </div>
                </div>
            </div>

            <div id="content2">
                <div class="business">
                    <div class="open">
                        <div class="bgbk about">
                            <p class="pt14">솔루션/홈페이지 제작</p>
                            <p class="pt36 lh6">ㅡ</p>
                            <p><img src="${pageContext.request.contextPath}/resources/images/img/buz1.png" alt="솔루션/홈페이지 제작"></p>
                            <p class="pt14"><a href="business01.jsp" class="ghostBtn2">자세히 보기</a></p>
                        </div>
                        <div class="bgbl about">
                            <p class="pt14">정부사업</p>
                            <p class="pt36 lh6">ㅡ</p>
                            <p><img src="${pageContext.request.contextPath}/resources/images/img/buz2.png" alt="정부사업"></p>
                            <p class="pt14"><a href="business03.jsp" class="ghostBtn2">자세히 보기</a></p>
                        </div>
                        <div class="bggr about">
                            <p class="pt14">교육사업</p>
                            <p class="pt36 lh6">ㅡ</p>
                            <p><img src="${pageContext.request.contextPath}/resources/images/img/buz3.png" alt="교육사업"></p>
                            <p class="pt14"><a href="business02.jsp" class="ghostBtn2">자세히 보기</a></p>
                        </div>
                    </div>
                </div>
            </div>

            <div id="content3">
                <div class="contact">
                    <div class="about">
                        <div class="fl">
                            <p class="pt14">찾아오는 길</p>
                            <p class="pt36">ㅡ</p>
                            <p class="pt18">서울특별시 강남구 학동로 56길 47, 4층</p>
                            <p class="pt18">(지번주소 : 서울특별시 강남구 삼성동 10-7, 4층)</p>                        
                            <p class="pt14"><a href="aboutUs03.jsp" class="ghostBtn1">자세히 보기</a></p>
                        </div>
                    </div>
                    <div class="about abouth">
                        <!-- 지도 API : width:980px;height:450px; -->
                        <div id="map" class="box-map" style="width:490px;height:356px;"></div>

                        <script type="text/javascript" 
                    src="http://openapi.map.naver.com/openapi/naverMap.naver?ver=2.0&key=3458891285f3e9f9c5aa09005e704c79"></script>
                        <script type="text/javascript" 
                    src="http://openapi.map.naver.com/openapi/naverMap.naver?ver=2.0&key=a2087383a7b455ac2da93ccd02ba28b6"></script>

                        <script type="text/javascript">
                            var oSeoulCityPoint = new nhn.api.map.LatLng(37.5142208,127.0434664); //좌표값 ( 위도, 경도 )
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
                                            size : new nhn.api.map.Size(490, 356)        });

                            var oSize = new nhn.api.map.Size(28, 37);
                            var oOffset = new nhn.api.map.Size(14, 37);
                            var oIcon = new nhn.api.map.Icon('http://static.naver.com/maps2/icons/pin_spot2.png', oSize, oOffset);

                            var oMarker = new nhn.api.map.Marker(oIcon, { title : '학동로 56길 47 4층' });  //마커를 생성한다 
                            oMarker.setPoint(oSeoulCityPoint); //마커의 좌표를 oPoint 에 저장된 좌표로 지정한다

                            oMap.addOverlay(oMarker); //마커를 네이버 지도위에 표시한다

                            var oLabel = new nhn.api.map.MarkerLabel(); // 마커 라벨를 선언한다. 
                            oMap.addOverlay(oLabel); // - 마커의 라벨을 지도에 추가한다. 
                            oLabel.setVisible(true, oMarker); // 마커의 라벨을 보이게 설정한다.
                        </script>

                        <!-- //지도 API -->
                    </div>
                </div>
                
                
                
                
                
                
                <div class="contact">
                    <div class="about abouth bgbl2"><p class="img"><img src="${pageContext.request.contextPath}/resources/images/img/buz4.png" alt="문의하기"></p></div>
                    <div class="about">
                        <div class="fr">
                            <p class="pt14">문의하기</p>
                            <p class="pt36">ㅡ</p>
                            <p class="pt18">메일 : <a href="mailto:seek-knowledge@knowledge-seek.com">seek-knowledge@knowledge-seek.com</a></p>
                            <p class="pt18">전화번호 : <a href="tel:07086244536">070-8624-4536</a></p>
                            <p class="pt14"><a href="customer.jsp" class="ghostBtn1">자세히 보기</a></p>
                        </div>
                    </div>
                </div>
            </div>
            <!-- //content -->
        </div>
        <!-- //container -->
        <!-- footer -->
        
        <div id="footer">
            <div class="footerWrap">

                <img src="${pageContext.request.contextPath}/resources/images/logo/logos.png" class="fl" alt="나리지식앤컴퍼니">
                <p class="info2">
                    대표이사 :노경환 <span>사업자등록번호</span> :220-88-64644 <span>대표전화</span> :070-8624-4536<br/>
                    주소 :서울특별시 강남구 학동로56길 47, 4층 (지번주소 : 서울특별시 강남구 삼성동 10-7, 4층)<br/>            
                </p>
                <img src="${pageContext.request.contextPath}/resources/images/logo/logofps.png" class="fr" alt="fps컨설팅">

                <address>
                    <em>Copyright ©</em>
                    <a href="index.jsp" target="_blank">Knowledge-seek &amp; Company</a>
                    <span>All Rights Reserved.</span>
                </address>
            </div>
        </div>
        <!-- //footer -->
    </div>
    <!-- //UI Object -->
    <script src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.smooth-scroll.js"></script>
    <script type="text/javascript">
	$(document).ready(function() {
        $('.side_nav a').smoothScroll();
		
		$('.m_btn').click(function(){
               $('.m_nav').slideToggle('slow');
            });
    });
    </script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
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
                        <li><a href="customer.jsp" id="gnb4">고객센터</a></li>
                    </ul>
                </div>
                
                <div class="nav_btn"><img class="m_btn" src="${pageContext.request.contextPath}/resources/images/icon/m_menu.png" alt="메뉴보이기"></div>
            </div>
           
            
            <div class="nav_wrap">
                <div class="nav">
                    <div id="subMenu">
                        <div class="subContent">
                            <ul id="sub1" style="margin-left: 60%;">
                                <li><a href="aboutUs01.jsp">인사말</a></li>
                                <li><a href="aboutUs02.jsp">조직도</a></li>
                                <li><a href="aboutUs03.jsp">오시는길</a></li>
                            </ul>
                            <ul id="sub2" style="margin-left: 70%;">
                                <li><a href="business01.jsp">IT사업</a></li>
                                <li><a href="business02.jsp">교육사업</a></li>
                                <li><a href="business03.jsp">정부 R&amp;D 과제</a></li>
                            </ul>
                            <!-- <ul id="sub3" style="margin-left: 78%;">
                                <li><a href="resource01.jsp">취업교육자료</a></li>
                            </ul> -->
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
                                <li><a href="business01.jsp">IT사업</a></li>
                                <li><a href="business02.jsp">교육사업</a></li>
                                <li><a href="business03.jsp">정부 R&amp;D 과제</a></li>
                                
                                <!-- <li class="gnbTit">자료실</li>
                                <li><a href="resource01.jsp">취업교육자료</a></li> -->
                            
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
        <div id="container2">
            
            <div class="contents">
                <div class="contentSbj">
                    <h3>찾아오시는 길</h3>
                    
                    <div class="direction">
                        <img src="${pageContext.request.contextPath}/resources/images/icon/home.png" alt="home icon"> > <span>회사소개</span> > <strong>찾아오시는 길</strong>
                    </div>
                </div>
                <div class="artcle">
                    <div class="map_wrap">
                        <div class="map_content2">
                            <!-- 지도 API : width:980px;height:450px; -->
                            <div id="map" class="box-map" style="width:980px;height:450px;"></div>

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
                                            size : new nhn.api.map.Size(980, 450)        });

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
                            <div class="addr">
                                <img src="${pageContext.request.contextPath}/resources/images/img/map.jpg" class="m_map">
                                <img src="${pageContext.request.contextPath}/resources/images/img/map2.png">
                            </div>
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
    <script type="text/javascript">
        $(document).ready(function() {
            $('.m_btn').click(function(){
               $('.m_nav').slideToggle('slow');
            });
        });
    </script>
</body>
</html>
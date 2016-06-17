<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!Doctype HTML>
<html>
<head>
      <jsp:include page="head.jsp" flush="false" />
</head>
    
<body>
    <!-- UI Object -->
    <div id="wrap">
        <!-- header -->
        	<jsp:include page="top.jsp" flush="false" />
        <!-- //header -->        
        
        <!-- container -->
        <div id="container">
            <!-- content -->
            <div id="content1">
                <div class="introduce">
                    <div class="open animated FadeInUp">
                        <p class="pt30 shadow">Open Innovation을 실행하는 곳,<br>
                            <span class="pt36">(주)나리지식&amp;컴퍼니입니다</span></p>
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
                            <p class="pt14">IT사업</p>
                            <p class="pt36 lh6">ㅡ</p>
                            <p><img src="${pageContext.request.contextPath}/resources/images/img/buz1.png" alt="솔루션/홈페이지 제작"></p>
                            <p class="pt14"><a href="business01.jsp" class="ghostBtn2">자세히 보기</a></p>
                        </div>
                        <div class="bggr about">
                            <p class="pt14">교육사업</p>
                            <p class="pt36 lh6">ㅡ</p>
                            <p><img src="${pageContext.request.contextPath}/resources/images/img/buz3.png" alt="교육사업"></p>
                            <p class="pt14"><a href="business02.jsp" class="ghostBtn2">자세히 보기</a></p>
                        </div>                        
                        <div class="bgbl about">
                            <p class="pt14">정부지원사업</p>
                            <p class="pt36 lh6">ㅡ</p>
                            <p><img src="${pageContext.request.contextPath}/resources/images/img/buz2.png" alt="정부사업"></p>
                            <p class="pt14"><a href="business03.jsp" class="ghostBtn2">자세히 보기</a></p>
                        </div>
                    </div>
                </div>
            </div>

            <div id="content3">
                <div class="contact">
                    <div class="about">
                        <div class="fl">
                            <p class="pt14">찾아오시는 길</p>
                            <p class="pt36">ㅡ</p>
                            <p class="pt18">경기도 성남시 분당구 판교로 289번길 20 (삼평동, 스타트업 캠퍼스) 3동 310호</p>
                            <p class="pt14"><a href="aboutUs03.jsp" class="ghostBtn1">자세히 보기</a></p>
                        </div>
                    </div>
                    <div class="about abouth">
                        <!-- 지도 API -->
                        <div id="map" class="box-map" style="width:50%;height:356px;"></div>

                        <script type="text/javascript" 
                    src="http://openapi.map.naver.com/openapi/naverMap.naver?ver=2.0&key=3458891285f3e9f9c5aa09005e704c79"></script>
                        <script type="text/javascript" 
                    src="http://openapi.map.naver.com/openapi/naverMap.naver?ver=2.0&key=a2087383a7b455ac2da93ccd02ba28b6"></script>

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
                                            size : new nhn.api.map.Size(570, 356)        });

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
            <jsp:include page="bottom.jsp" flush="false" />
        <!-- //footer -->
    </div>
    <!-- //UI Object -->
    <jsp:include page="script.jsp" flush="false" />
</body>
</html>
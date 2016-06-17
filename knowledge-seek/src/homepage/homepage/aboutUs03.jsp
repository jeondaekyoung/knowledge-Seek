<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
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

	                        <script type="text/javascript" src="http://openapi.map.naver.com/openapi/naverMap.naver?ver=2.0&key=3458891285f3e9f9c5aa09005e704c79"></script>
	                        <script type="text/javascript" src="http://openapi.map.naver.com/openapi/naverMap.naver?ver=2.0&key=a2087383a7b455ac2da93ccd02ba28b6"></script>
	
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
	                                            size : new nhn.api.map.Size(980, 450)        });
	
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
                                <%-- <img src="${pageContext.request.contextPath}/resources/images/img/map.jpg" class="m_map">
                                <img src="${pageContext.request.contextPath}/resources/images/img/map2.png"> --%>
                                <table>
                                	<colgroup><col style="width:20%;"><col style="width:*;"></colgroup>
                                	<tbody>
	                                	<tr>
	                                		<th>대표 전화</th>
	                                		<td>070-8624-4536</td>
	                                	</tr>
	                                	<tr>
	                                		<th>주소</th>
	                                		<td>(13488) 경기도 성남시 분당구 판교로 289번길 20 (삼평동, 스타트업 캠퍼스) 3동 310호</td>
	                                	</tr>
	                                	<tr>
	                                		<th>오시는 길</th>
	                                		<td>(마을) 602-1, 602-2, 71, 73, 73-1 (직행) 4000, 9007 (일반) 101, 330, 350, 380 : 봇들육교 정류장에서 도보 3분</td>
	                                	</tr>
                                	</tbody>
                                </table>
                            </div>
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
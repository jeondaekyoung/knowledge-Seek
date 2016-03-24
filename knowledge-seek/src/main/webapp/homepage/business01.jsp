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
                    <h3>솔루션 개발</h3>
                    
                    <div class="direction">
                        <img src="${pageContext.request.contextPath}/resources/images/icon/home.png" alt="home icon"> > <span>사업영역</span> > <strong>IT사업</strong>
                    </div>
                </div>
                <div class="artcle">
                    
                    <div class="edu">
                        <h4>IT사업</h4>
                        <div class="edu1">
                            <img src="${pageContext.request.contextPath}/resources/images/img/business01.png" alt="소프트웨어 제작" class="fl">
                            <p class="fr"><span>소프트웨어 개발</span>
								<br>SI, 웹 솔루션 개발, 교육용 소프트웨어 제작 등 세상에 꼭 필요하지만 지금은 없는 소프트웨어를 만들며 세상을 이롭게 할 Open Innovation을 실현하고 있습니다.
                            </p>
                        </div>

                        <div class="edu1">
							<img src="${pageContext.request.contextPath}/resources/images/img/business02.png" alt="홈페이지 제작" class="fr">
                            <p class="fl"><span>홈페이지 제작</span>
								<br>축적된 IT 노하우로 기업 홈페이지 제작, 쇼핑몰 홈페이지 구축 등 홍보와 사업의 얼굴이 될 홈페이지, 딱딱하고 뻔한 그저그런 홈페이지가 아닌 유니크한 홈페이지를 만들고 있습니다.
                            </p>
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
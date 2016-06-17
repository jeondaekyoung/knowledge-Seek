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
                    <h3>정부지원사업</h3>
                    
                    <div class="direction">
                        <img src="${pageContext.request.contextPath}/resources/images/icon/home.png" alt="home icon"> > <span>사업영역</span> > <strong>정부지원사업</strong>
                    </div>
                </div>
                <div class="artcle">
                    
                    <div class="edu">
                        <h4>중소기업진흥공단</h4>
                        <div class="edu1">
                            <img src="${pageContext.request.contextPath}/resources/images/img/business04.png" alt="중소기업진흥공단" class="fl">
                            <p class="fr" style="padding-bottom:50px"><span>종합성장·건강관리 서비스</span>
								<br>2015년도 중소기업진흥공단 청년창업사관학교에 5기 입교대상자로 선발되어 개발중에 있습니다.
                            </p>
                        </div>

                        <div class="edu1">
                            <h4>미래창조과학부</h4>
							<img src="${pageContext.request.contextPath}/resources/images/img/business06.png" alt="미래창조과학부" class="fl">
                            <p class="fr"><span>종합성장·건강관리 서비스</span>
								<br>미래창조과학부와 경기도가 지원하는 스타트업 캠퍼스에 입주하여 사물인터넷 제품을 개발하고 있습니다.
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
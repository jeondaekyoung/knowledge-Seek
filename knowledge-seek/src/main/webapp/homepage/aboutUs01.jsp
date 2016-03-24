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
                    <h3>인사말</h3>
                    
                    <div class="direction">
                        <img src="${pageContext.request.contextPath}/resources/images/icon/home.png" alt="home icon"> > <span>회사소개</span> > <strong>인사말</strong>
                    </div>
                </div>
                <div class="artcle">
                    <img src="${pageContext.request.contextPath}/resources/images/img/aboutUs.png" class="au1" alt="지식-교육사업을 통해 Open Innovation을 실현하는 곳, 나리지식앤컴퍼니입니다">
                    
                    <div class="aboutUs">
                        <div>
                            <h4>주요업무</h4>
                            <div class="work">
                                <p class="cir bgw1"><img src="${pageContext.request.contextPath}/resources/images/img/buz11.png" alt=""></p>
                                <p class="pt18">IT사업</p>
                                <p class="pt14 aleft">
                                    <span>- 홈페이지 제작</span><br>
                                    <span>- 솔루션 제작</span>
                                </p>
                            </div>
                            <div class="work">
                                <p class="cir bgw2"><img src="${pageContext.request.contextPath}/resources/images/img/buz22.png" alt=""></p>
                                <p class="pt18">정부R&amp;D과제</p>
                                <p class="pt14 aleft">
                                    <span>- IOT기반 성장 데이터 플랫폼</span>
                                </p>
                            </div>
                            <%-- <div class="work">
                                <p class="cir bgw1"><img src="${pageContext.request.contextPath}/resources/images/img/buz00.png" alt=""></p>
                                <p class="pt18">온라인 마케팅</p>
                                <p class="pt14 aleft">
                                    <span>- 키워드 광고</span><br>
                                    <span>- 바이럴 마케팅</span><br>
                                    <span>- 블로그 마케팅</span>
                                </p>
                            </div> --%>
                            <div class="work">
                                <p class="cir bgw1"><img src="${pageContext.request.contextPath}/resources/images/img/buz33.png" alt=""></p>
                                <p class="pt18">교육사업</p>
                                <p class="pt14 aleft">
                                    <span>- 진로/면접/직무 교육</span><br>
                                    <span>- 입사지원서 교육</span><br>
                                    <span>- 강사/컨설턴트 파견</span><br>
                                </p>
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
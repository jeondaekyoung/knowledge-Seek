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
                                <li><a href="business03.jsp">정부 R&amp;D과제</a></li>
                                
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
        <div id="container2">
            
            <div class="contents">
                <div class="contentSbj">
                    <h3>교육사업</h3>
                    
                    <div class="direction">
                        <img src="${pageContext.request.contextPath}/resources/images/icon/home.png" alt="home icon"> > <span>사업영역</span> > <strong>교육사업</strong>
                    </div>
                </div>
                <div class="artcle">
                    
                    <div class="edu">
                        <h4>성공적인 취업을 위한 교육 프로그램</h4>
                        <div class="edu1">
                            <img src="${pageContext.request.contextPath}/resources/images/img/business1.jpg" alt="취업마인드자기분석,입사지원서클리닉,면접클리닉,입사전교육" class="fl" usemap="#buss">
							<map name="buss">
								<area href="resource01.jsp" shape="rect" coords="0,0,178,110">
								<area href="resource01.jsp#001" shape="rect" coords="190,0,367,110">
								<area href="resource01.jsp#002" shape="rect" coords="0,122,178,233">
								<area href="resource01.jsp#003" shape="rect" coords="190,123,367,233">
							</map>

                            <p class="fr"><span>성공적인 취업을 위한 최적의 교육!</span>
                                <br><span class="edutt">취업마인드 자기분석 / 입사지원서 클리닉 / 면접 클리닉 / 입사 전 교육</span>

                            <br>자신에게 맞는 직업과 진로를 탐색하는 자기 분석에서부터 입사지원서 작성, 면접, 그리고 취업에 성공한 뒤 직장에서 해야할 기본 교육까지
                            취업을 앞둔 학생, 취업준비생을 위한 종합 교육 프로그램을 운영하고 있습니다.
                            </p>
                        </div>

                        <h4>실무 역량 강화교육 프로그램</h4>
                        <div class="edu1">
                            <img src="${pageContext.request.contextPath}/resources/images/img/business2.jpg" alt="경영사무관리 과정,금융실무 과정,유통마케팅 과정, 비서역량강화 과정" class="fr">
                            <p class="fl"><span>올바른 취업 준비와 기업 예비 주체로서의 역량 극대화</span>
                            <br><span class="edutt">경영 사무관리 과정 / 금융 실무과정 / 유통 마케팅 과정 / 비서 역량강화 과정</span>
                            <br>학생들의 능력과 적성을 고려하여
                            실제 직무를 먼저 체험해 보고 익힐 수 있는 실무 역량 강화 교육과정입니다.
                            스펙 위주의 취업 준비에서 벗어나 목표로 하는 직무의
                            역량을 강화함으로서 기업에서 실제로 필요로 하는 인재를 양성하고 있습니다.
                            </p>
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
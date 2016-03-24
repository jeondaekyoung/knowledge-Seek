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
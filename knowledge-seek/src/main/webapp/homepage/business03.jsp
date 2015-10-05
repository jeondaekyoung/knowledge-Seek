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
                    <h3>정부 R&amp;D 과제</h3>
                    
                    <div class="direction">
                        <img src="${pageContext.request.contextPath}/resources/images/icon/home.png" alt="home icon"> > <span>사업영역</span> > <strong>정부 R&amp;D 과제</strong>
                    </div>
                </div>
                <div class="artcle">
                    
                    <div class="edu">
                        <h4>중소기업진흥공단</h4>
                        <div class="edu1">
                            <img src="${pageContext.request.contextPath}/resources/images/img/business03.png" alt="소프트웨어 제작" class="fl">
                            <p class="fr" style="padding-bottom:50px"><span>종합성장·건강관리 서비스</span>
								<br>2015년도 중소기업진흥공단 청년창업사관학교에 5기 입교대상자로 선발되어 개발중에 있습니다.<br><br>
								<br><br><br><br><br><br><br><br><br><br><br><br>
                            </p>
                            <img src="${pageContext.request.contextPath}/resources/images/img/business04.png" alt="중소기업진흥공단" class="fl">
                        </div>

                        <!--div class="edu1">
							<img src="${pageContext.request.contextPath}/resources/images/img/business02.png" alt="홈페이지 제작" class="fr">
                            <p class="fl"><span>홈페이지 제작</span>
								<br>축적된 IT 노하우로 기업 홈페이지 제작, 쇼핑몰 홈페이지 구축 등 홍보와 사업의 얼굴이 될 홈페이지, 딱딱하고 뻔한 그저그런 홈페이지가 아닌 유니크한 홈페이지를 만들고 있습니다.
                            </p>
                        </div-->
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
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
    <link href="${pageContext.request.contextPath}/resources/css/jquery-ui.css" rel="stylesheet">
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
                    
                    <div class="edu">
                        <div class="resource" id="accordion">
                            <h4>취업 마인드 함양, 자기분석 교육</h4>
                            
                            <div class="h_auto"><div class="rsc">
                                <h5>&nbsp;先취업 後진학</h5>
                                <p><strong>先취업 後진학의 120% 활용법</strong>
                                    <br>- 개념, 취지, 제도, 전형별 진행 대학, 성공사례, 유의할 점 등
                                    <br>- 특성화고 학생들을 대상으로 취업과 진로 고민 해결
                                </p>
                                <h5>&nbsp;기업의 이해</h5>
                                <p><strong>중소기업에서 네 꿈을 키워라! 중소기업 CEO특강</strong>
                                    <br>- 중소기업에 대한 올바른 가치관 확립, 선입견 해소
                                    <br>- 올바른 직업관 형성 및 중소기업 이해
                                    <br>- 중소기업 취업에 대한 미래 전망과 계열별 유망 분야 소개
                                    <br>- 중소기업의 요구 인재상 알기
                                    <br>- 요구 역량 개발 방법
                                    <br>- 중소기업 성공 취업사례
                                </p>
                                <h5>&nbsp;직업의식 강화</h5>
                                <p><strong>도대체 왜? 알바가 아닌 직장을 다녀야 할까?</strong>
                                    <br>- 아르바이트와 직장 취업의 전격 비교분석 (임금시장, 근로조건, 4대보험의 중요성 등)
                                </p>
                                <h5>&nbsp;취업 자신감 강화</h5>
                                <p><strong>취업의 블랙홀 탈출법</strong>
                                    <br> - 스펙 만이 답이 아니다! 나만의 강점 찾기
                                    <br>- 나의 취업 경쟁력 높이는 방법
                                    <br>- 취업에서 두려움을 버리고 자신감을 높이기
                                </p>
                                <h5>&nbsp;취업 목표 설정</h5>
                                <p><strong>진로 설정 및 직업 선택의 중요성.목표 기업 설정과 목표 기업 분석</strong>
                                    <br>- 희망 직무 선행의 필요성 및 직무에 따른 방향 설정 노하우 전수
                                    <br>- 목표 기업 설정의 중요성
                                    <br>- 효율적인 목표 기업 분석 방법
                                </p>
                                <h5>&nbsp;취업 트렌드 분석</h5>
                                <p><strong>업종별, 직무별, 기업별 취업 트렌드 분석</strong>
                                    <br>- 업종별, 직무별, 기업별 분석을 통한  끈임없이 변화하는 채용시장 분석 및 탐구와 전략 수립
                                </p>
                                <h5>&nbsp;자기 분석</h5>
                                <p><strong>커리어 로드맵 작성</strong>
                                    <br>- MBTI 성격유형검사를 통한 자기분석 및 피드백
                                    <br>- SWOT 분석을 통한 취업 전까지의 구체적인 목표 및 계획수립
                                    <br>- 취업 후 10년 뒤, 20년 뒤의 모습 그리기
                                </p>
                                <h5>&nbsp;취업 포털 활용법</h5>
                                <p><strong>혼자서 성공 취업하기</strong>
                                    <br>- 취업포털 소개, 취업포털 이용 목적
                                    <br>- 부지런한 구직자가 좋은 직장을 얻는 방법 소개
                                    <br>( 채용공고 검색법 / 기업정보 / 취업정보 / 합격수기 등 정보 수집과 활용법 )
                                </p>
                            </div>
                            <div class="rscimg1">
                                <img src="${pageContext.request.contextPath}/resources/images/img/u68.png" alt="강의 사진">
                                <img src="${pageContext.request.contextPath}/resources/images/img/u70.png" alt="강의 사진">
                                <img src="${pageContext.request.contextPath}/resources/images/img/u72.jpg" alt="강의 사진">
                                <img src="${pageContext.request.contextPath}/resources/images/img/u74.png" alt="강의 사진">
                                <img src="${pageContext.request.contextPath}/resources/images/img/u76.jpg" alt="강의 사진">
                                </div></div>
                            
                            
                            
                            
                            <h4 id="001">입사지원서 클리닉</h4>
                            <div class="h_auto"><div class="rsc">
                                <h5>&nbsp;입사지원서 작성법</h5>
                                <p><strong>입사지원서 작성법 강의</strong>
                                    <br> - 작성시 유의사항
                                    <br>- 논리정연 하게 나의 이야기 작성하기
                                    <br>- 합격자의 입사지원서 분석
                                    <br>- 나의 입사지원서 직접 작성해보기
                                </p>
                                <h5>&nbsp;내가 인사담당자라면?</h5>
                                <p><strong>팀별 입사지원서 평가·분석</strong>
                                    <br>- 팀별로 가상의 지원자들의 입사지원서를 이용해 팀원 각자가 인사담당자가 되어 가상의 지원자들의 입사지원서를 평가해 보며 인사담당자들의 입장이 되어보기
                                </p>
                                <h5>&nbsp;입사지원서 컨설팅</h5>
                                <p><strong>개인별 / 그룹별 맞춤 입사지원서 컨설팅</strong>
                                    <br>- 참여자의 입사지원서를 전문 컨설턴트가 희망 업종, 직무, 회사 등에 따라 상담 중심으로 컨설팅 진행
                                    <br>- 컨설팅과 참가자 지원서 수정의 반복으로 '나만의 입사지원서 완성'
                                </p>
                            </div>
                            <div class="rscimg1">
                                <img src="${pageContext.request.contextPath}/resources/images/img/u88.png" alt="강의 사진">
                                <img src="${pageContext.request.contextPath}/resources/images/img/u90.png" alt="강의 사진">
                                </div></div>
                            
                            
                            
                            <h4 id="002">면접 클리닉</h4>

                            <div class="h_auto"><div class="rsc">
                                <h5>&nbsp;면접 이미지 메이킹</h5>
                                <p><strong>첫인상을 결정짓는 3초를 잡아라! 면접 복장 / 메이크업 강의</strong>
                                    <br>- 이미지메이킹의 중요성 (미소 / 인사법 / 면접장 입·퇴장 / 착석법 / 목소리 등 이미지를 결정짓는 요소와 좋은 이미지 전달법 강의)
                                    <br>- 좋은 인상을 주는 면접 복장 노하우
                                    <br>- 메이크업을 통한 이미지 메이킹 노하우
                                </p>
                                <h5>&nbsp;면접 역할극</h5>
                                <p><strong>면접 Role Playing을 통한 실전 연습</strong>
                                    <br>- 참여자가 각각 면접지원자와 면접관의 입장이 되는 면접 역할극을 수행하고 각각의 입장을 이해하며 자신의 면접 준비상태 점검
                                    <br>- 면접관의 입장을 이해, 효율적 면접 준비 가능
                                </p>
                                <h5>&nbsp;1분 자기소개</h5>
                                <p><strong>1분 자기소개 컨설팅</strong>
                                    <br>- 지원자가 취업을 희망하는 업종, 직무, 기업 등을 분석하여 
          개별맞춤 및 1분 자기소개서 완성 컨설팅
                                </p>
                                <h5>&nbsp;면접 질문</h5>
                                <p><strong>면접 질문 파헤치기</strong>
                                    <br>- 면접 공통 질문과 직무 및 업종별로 다수 출제되는 면접 질문을 제시
                                    <br>- 면접관의 질문 의도 파악하기
                                    <br>- 논리적으로 나만의 답변을 제시하는 방법
                                    <br>- 면접 답변 연습 평가와 피드백
                                </p>
                                <h5>&nbsp;모의 면접</h5>
                                <p><strong>개별면접 / 집단면접 / 프레젠테이션 면접</strong>
                                    <br>- 실제의 면접상황과 흡사한 환경에서 모의면접관과 면접을 진행
                                    <br>- 참여자의 면접준비 정도 파악 / 면접 질문 트렌드 파악
                                    <br>- 면접자가 다수일 경우 유의사항과 자신감 제고
                                    <br>- 다수의 면접자 중 제한된 면접시간 내에 자신을 어필하는 노하우
                                    <br>- 프레젠테이션 면접을 통하여 스피치 능력, 문서 표현 능력, 시간엄수 중요성과 훈련법 교육
                                    <br>- 발표 시나리오 작성 및 발표와 피드백을 통하여 부족한 능력 강화
                                </p>
                            </div>
                            <div class="rscimg2">
                                <img src="${pageContext.request.contextPath}/resources/images/img/u116.png" alt="강의 사진">
                                <img src="${pageContext.request.contextPath}/resources/images/img/u118.png" alt="강의 사진">
                                <img src="${pageContext.request.contextPath}/resources/images/img/u120.png" alt="강의 사진">
                                <img src="${pageContext.request.contextPath}/resources/images/img/u128.png" alt="강의 사진">
                                </div></div>
                            
                            
                            
                            
                            <h4 id="003">입사 전 교육</h4>
                            <div class="h_auto"><div class="rsc">
                                <h5>&nbsp;회사생활 교육</h5>
                                <p><strong>사회인의 언어로 말하기</strong>
                                    <br>- 학생이 아닌 사회인으로서의 언어사용 교육
                                    <br>- 긍정적인 회사생활의 사례 소개
                                </p>
                                <h5>&nbsp;비지니스 매너</h5>
                                <p><strong>사랑 받는 신입사원 되기</strong>
                                    <br> - 출/퇴근 예절, 이메일 작성법, 전화 받는법 등 기본 예절 학습
                                    <br>- 보고 하는법, 명함 교환, 상석 안내방법 등 비즈니스 매너를 통해 회사가 같이 일하고 싶은 사람 되기
                                    <br>- 가정, 공공시설 등 일상 생활에서의 사회인 다운 예절 학습
                                </p> 
                            </div>
                            <div class="rscimg3">
                                <img src="${pageContext.request.contextPath}/resources/images/img/u148.jpg" alt="강의 사진">
                                </div></div>
                            
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
    <script src="${pageContext.request.contextPath}/resources/js/jquery/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-ui.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $( "#accordion" ).accordion();
            
            $('.m_btn').click(function(){
               $('.m_nav').slideToggle('slow');
            });
        });
    </script>
</body>
</html>
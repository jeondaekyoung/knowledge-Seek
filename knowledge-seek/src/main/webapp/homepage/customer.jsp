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
                    <h3>문의하기</h3>
                    
                    <div class="direction">
                        <img src="${pageContext.request.contextPath}/resources/images/icon/home.png" alt="home icon"> > <span>고객센터</span> > <strong>문의하기</strong>
                    </div>
                </div>
                <div class="artcle">
                    <div class="customer">
                        <div class="cus_content">
                            <img class="cusmain" src="${pageContext.request.contextPath}/resources/images/img/customer.png" width="980px" alt="문의하기">
                            
                            <div class="custom">
                                <p><img src="${pageContext.request.contextPath}/resources/images/img/buz6.png"></p>
                                <span><h4>대표전화</h4>
                                    <p><a href="tel:07086244536">070-8624-4536</a></p>
                                </span>
                            </div>
                            
                            <div class="custom">
                                <p><img src="${pageContext.request.contextPath}/resources/images/img/buz7.png"></p>
                                <span><h4>대표메일</h4>
                                    <p><a href="mailto:seek-knowledge@knowledge-seek.com">seek-knowledge@knowledge-seek.com</a></p>
                                </span>
                            </div>
                            
                            <div class="custom">
                                <p><img src="${pageContext.request.contextPath}/resources/images/img/buz5.png"></p>
                                <span><h4>운영시간</h4>
                                    <p>평일 운영시간 9:30 ~ 18:00
                                    <br>주말, 공휴일은 휴무입니다.</p>
                                </span>
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
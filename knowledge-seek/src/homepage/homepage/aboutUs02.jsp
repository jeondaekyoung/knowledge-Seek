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
                    
                    <div class="direction">
                        <img src="${pageContext.request.contextPath}/resources/images/icon/home.png" alt="home icon"> > <span>회사소개</span> > <strong>조직도</strong>
                    </div>
                    <h3>조직도</h3>
                </div>
                <div class="artcle">
                    <div class="org_bg">
                    <br>
                    	<p>대표이사</p>
                    	<p class="pt30">노경환</p>
                    </div>
                    
                   	<div class="org">
                   		<p class="org_bg2">IT 사업본부</p>
                   		<ul>
                   			<li>- 개발실</li>
                   			<li>- 디자인실</li>
                   			<li>- 온라인홍보실</li>
                   		</ul>
                   	</div>
                   	
                   	<div class="org">
                   		<p class="org_bg2">HR 교육실</p>
                   		<ul>
                   			<li>- 교육기획 파트</li>
                   			<li>- 교육운영 파트</li>
                   			<li>- 취업컨설팅 파트</li>
                   		</ul>
                   	</div>
                   	
                   	<div class="org">
                   		<p class="org_bg2">전략기획실</p>
                   		<ul>
                   			<li>- 경영지원 파트</li>
                   			<li>- 사업기획 파트</li>
                   		</ul>
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!Doctype HTML>
<html>
<head>
    <jsp:include page="head.jsp" flush="false" />
        
	<style>
		#section2{
			background-image: url(${pageContext.request.contextPath}/resources/images/bg/bg1.jpg);
			padding: 0 0 6% 0;
			overflow: hidden;
		}
	</style> 
</head>
    
<body>
    <!-- UI Object -->
    <div id="wrap">
        <!-- header -->
        <jsp:include page="top.jsp" flush="false" />
        <script type="text/javascript">
			$(document).ready(function() {
				$('#fullpage').fullpage({
					verticalCentered: false,
					scrollingSpeed: 1500,
		         	scrollBar: true,
					css3:false
				});
			});
		</script>
        <!-- //header -->
        
        <div id="fullpage">			
			<div class="section aboutUs">
				<div class="sign">
				
					<img src=<c:url value='/resources/images/logo/signature.png'/> alt="signature">
				</div>
				<p class="pt36 fadeInDown animated">About Us</p>
				<p class="pt19 fadeInDown animated">&#40;주&#41;나리지식앤컴퍼니는 &#39;세상의 모든 지식을 말하다&#39;라는 슬로건을 내걸고 지식을 필요로하는 사람과 지식을 가진 사람을 이어주는 사업으로 출발했습니다.
					IoT 기반의 어플리케이션과 솔루션 등을 개발하여 IT기업으로 성장한 지금은 Open Innovation을 바탕으로 지식-IT 사업을 선도하는 리더로 자리매김하려 합니다.</p>
				<%-- <a href="#section2" class="scroll fadeInDown animated"><img src="${pageContext.request.contextPath}/resources/images/icon/scroll.png" alt="scroll button"></a>
				<script>
					$(document).ready(function($){
						$('.scroll').click(function(event){
							$('html, body').animate({'scrollTop' : $(this.hash).offset().top}, "slow");
						});
					});
				</script> --%>
			</div>
			
			<div class="section colfff" id="section2">
				<div class="org_bg" data-ride="animated" data-animation="fadeInLeft" data-delay="300">
                	<br>
                   	<p>대표이사</p>
                   	<p class="pt26">노경환</p>
                </div>
                
               	<div class="org fadeInLeft animated">
               		<p class="org_bg2">IT 사업본부</p>
               		<ul>
               			<li>- 개발실</li>
               			<li>- 디자인실</li>
               			<li>- 온라인홍보실</li>
               		</ul>
               	</div>
               	
               	<div class="org fadeInUp animated">
               		<p class="org_bg2">HR 교육실</p>
               		<ul>
               			<li>- 교육기획 파트</li>
               			<li>- 교육운영 파트</li>
               			<li>- 취업컨설팅 파트</li>
               		</ul>
               	</div>
               	
               	<div class="org fadeInRight animated">
               		<p class="org_bg2">전략기획실</p>
               		<ul>
               			<li>- 경영지원 파트</li>
               			<li>- 사업기획 파트</li>
               		</ul>
               	</div>
			</div>
		</div>
        
        <!-- footer -->
            <jsp:include page="bottom.jsp" flush="false" />
        <!-- //footer -->
    </div>
    <!-- //UI Object -->
    <jsp:include page="script.jsp" flush="false" />
</body>
</html>
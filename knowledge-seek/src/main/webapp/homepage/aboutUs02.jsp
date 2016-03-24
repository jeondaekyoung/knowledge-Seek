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
					<!--img src="${pageContext.request.contextPath}/resources/images/img/aboutUs2.png" alt="(주)나리지식앤컴퍼니는 고객님의 만족도를 위한 부서의 역할배치, 인력구성을 통하여 보다 조직적이고 시스템화된 서비스를 제공하고자 합니다."-->
                    <img src="${pageContext.request.contextPath}/resources/images/img/organization.png" class="organi" alt="조직도">
                    
                    
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
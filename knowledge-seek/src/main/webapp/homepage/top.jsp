<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
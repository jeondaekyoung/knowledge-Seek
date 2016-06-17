<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<header id="header" class="navbar navbar-fixed-top bg-white box-shadow b-b b-light" data-spy="affix" data-offset-top="1">
  <div id="menu" class="container">
    <div class="navbar-header">
      <a href="index.jsp" class="navbar-brand"><img src="${pageContext.request.contextPath}/resources/images/logo/logo_cooper.png" class="m-r-sm"></a>
      <button class="btn btn-link visible-xs" type="button" data-toggle="collapse" data-target=".navbar-collapse">
        <i class="fa fa-bars"></i>
      </button>
    </div>
    <div class="collapse navbar-collapse">
      <ul class="nav navbar-nav navbar-right">
		<li class="dropdown-submenu">
            <a tabindex="-1" href="aboutUs01.jsp" data-jump="true">회사소개 <b class="caret"></b></a>
            <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
              <li><a tabindex="-1" href="aboutUs01.jsp">인사말</a></li>
              <li><a tabindex="-1" href="aboutUs02.jsp">조직도</a></li>
              <li><a tabindex="-1" href="aboutUs03.jsp">오시는길</a></li>
            </ul>
        </li>
		<li class="dropdown-submenu">
            <a tabindex="-1" href="business01.jsp" data-jump="true">사업소개 <b class="caret"></b></a>
            <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
              <li><a tabindex="-1" href="business01.jsp">IT사업</a></li>
              <li><a tabindex="-1" href="business02.jsp">교육사업</a></li>
              <li><a tabindex="-1" href="business03.jsp">정부지원사업</a></li>
            </ul>
        </li>
        <li>
          <a href="customer.jsp" >Contact</a>
        </li>
      </ul>
    </div>
  </div>
</header>

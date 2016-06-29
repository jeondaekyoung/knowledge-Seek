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
        <li data-menuanchor="firstPage" class="scroll">
          <a href="index.jsp#section0">COMPANY</a>
        </li>
        <li data-menuanchor="secondPage" class="scroll">
          <a href="index.jsp#section1">SERVICE</a>
        </li>
        <li data-menuanchor="3rdPage" class="scroll">
          <a href="index.jsp#section2">CONTACT</a>
        </li>
      </ul>
    </div>
  </div>
</header>

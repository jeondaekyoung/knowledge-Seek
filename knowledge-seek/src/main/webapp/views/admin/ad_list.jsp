<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko" class="app">
<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" /> 
	<title>관리자 | 광고목록</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css" type="text/css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/animate.css" type="text/css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css" type="text/css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font.css" type="text/css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/datepicker/datepicker.css" type="text/css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/app.css" type="text/css" />
	
	<style>
	*{font-size: 12px}
        .admin {width:100%}
        .admin th {background-color:#eee; height:41px;}
        .admin th, .admin td{text-align:center; border:1px solid #ccc; padding:5px}
        .admin td input[type="text"] {width:100%; border-radius: 3px; border:1px solid #ccc}
        a.pop_entries {color:#FB6B5B; font-weight:bold}
        div.form-group {min-height:50px; width:100%; border-bottom: 1px dashed #ddd}
        div.form-group .col-lg-2 {padding-left:0; padding-bottom:10px}
        div.form-group .col-lg-2 h4 {font-size: 16px; font-weight: bold; text-align: center; line-height: 37px;}
    </style>
    
  <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/app.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/app.plugin.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/slimscroll/jquery.slimscroll.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/datepicker/bootstrap-datepicker.js"></script>
  <script src="${pageContext.request.contextPath}/vjs/admin/ad_list.js"></script>
  <!--[if lt IE 9]>
    <script src="${pageContext.request.contextPath}/resources/js/ie/html5shiv.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/ie/respond.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/ie/excanvas.js"></script>
  <![endif]-->	
</head>
<body>
<c:choose>
	<c:when test="${empty sessionScope.adminName }">
		<c:redirect url="${pageContext.request.contextPath }/admin/index.do" />
	</c:when>
</c:choose>

  <section class="vbox">
    <header class="bg-black dk header navbar navbar-fixed-top-xs">
      <div class="navbar-header aside-md">
        <a class="btn btn-link visible-xs" data-toggle="class:nav-off-screen,open" data-target="#nav,html">
          <i class="fa fa-bars"></i>
        </a>
        <a href="${pageContext.request.contextPath}/views/admin/index.jsp" class="navbar-brand"><img src="${pageContext.request.contextPath }/resources/images/logo.png" class="m-r-sm">알랑랑</a>
        <a class="btn btn-link visible-xs" data-toggle="dropdown" data-target=".nav-user">
          <i class="fa fa-cog"></i>
        </a>
      </div>
    </header>
      
    <section>
      <section class="hbox stretch">
        <!-- .aside -->
        <aside class="bg-black lter aside-md hidden-print" id="nav">          
          <section class="vbox">
            <header class="header bg-info lter text-center clearfix">
              
            </header>
            <section class="w-f scrollable">
              <div class="slim-scroll" data-height="auto" data-disable-fade-out="true" data-distance="0" data-size="5px" data-color="#333333">
                
                <!-- nav -->
                <nav class="nav-primary hidden-xs">
                  <ul class="nav">
                    <li >
                      <a href="${pageContext.request.contextPath }/views/admin/ad_reg.jsp"  >
                        <i class="fa fa-clock-o icon">
                          <b class="bg-success"></b>
                        </i>
                        <span>광고등록</span>
                      </a>
                      
                    <li >
                      <a href="${pageContext.request.contextPath }/views/admin/eng_reg.jsp"  >
                        <i class="fa fa-clock-o icon">
                          <b class="bg-primary dker"></b>
                        </i>
                        <span>영어등록</span>
                      </a>
                    </li>
                    <li >
                      <a href="${pageContext.request.contextPath }/views/admin/ad_list.jsp"  >
                        <i class="fa fa-clock-o icon">
                          <b class="bg-warning"></b>
                        </i>
                        <span>광고목록</span>
                      </a>
                    </li>
                  </ul>
                </nav>
                <!-- / nav -->
              </div>
            </section>
            
            <footer class="footer lt hidden-xs b-t b-black">
              
              <a href="#nav" data-toggle="class:nav-xs" class="pull-right btn btn-sm btn-black btn-icon">
                <i class="fa fa-angle-left text"></i>
                <i class="fa fa-angle-right text-active"></i>
              </a>
            </footer>
          </section>
        </aside>
        <!-- /.aside -->
        <section id="content">
          <section class="vbox">
            <header class="header bg-white b-b b-light">
              <p>Home > 광고목록</p>
            </header>
            
            <section class="scrollable wrapper w-f">
                
                <div class="form-group">
                	<div class="col-lg-2"><h4>광고검색조건</h4></div>
                	<div class="col-lg-2"><input type="text" id="ad_name" class="input-m form-control" placeholder="광고이름"></div>
                	<div class="col-lg-2"><input type="text" id="company_name" class="input-m form-control" placeholder="회사명"></div>
                	<div class="col-lg-2"><input type="text" id="start_date" class="input-m datepicker-input form-control" placeholder="시작날짜" size="16" data-date-format="yyyy-mm-dd" ></div>
                	<div class="col-lg-2"><input type="text" id="end_date" class="input-m datepicker-input form-control" placeholder="종료날짜" size="16" data-date-format="yyyy-mm-dd" ></div>
                	<div class="col-lg-2"><input type="button" id="searchAd" class="btn btn-s-lg btn-info" value="검색"></div>
                </div>
                
                <table class="admin" id="adTable">
                    <colgroup></colgroup>
                    <thead>
                        <tr>
                            <th>입력날짜</th>
                            <th>시작날짜</th>
                            <th>종료날짜</th>
                            <th>광고명</th>
                            <th>회사명</th>
                            <th>회사 연락처</th>
                            <th>회사 주소</th>
                        </tr>
                    </thead>
                    
                    <tbody>
                    	
                    </tbody>
                </table>
                
            </section>
            
          </section>
          <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav"></a>
        </section>
        <aside class="bg-light lter b-l aside-md hide" id="notes">
          <div class="wrapper">Notification</div>
        </aside>
      </section>
    </section>
  </section>

</body>
</html>
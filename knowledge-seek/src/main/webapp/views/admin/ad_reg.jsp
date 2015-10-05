<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko" class="app">
<head>
	<meta charset="utf-8" />
	<meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" /> 
	<title>관리자 | 광고등록</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css" type="text/css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/animate.css" type="text/css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css" type="text/css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font.css" type="text/css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/fuelux/fuelux.css" type="text/css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/datepicker/datepicker.css" type="text/css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/app.css" type="text/css" />
	
	<style>
	*{font-size: 12px}
        .admin {width:100%}
        .admin th {background-color:#eee; height:51px;}
        .admin th, .admin td{text-align:center; border:1px solid #ccc; padding:5px}
        .admin input[type="text"] {width:100%}
    </style>
  
	<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/app.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/app.plugin.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/slimscroll/jquery.slimscroll.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/fuelux/fuelux.js"></script>
  	<script src="${pageContext.request.contextPath}/resources/js/datepicker/bootstrap-datepicker.js"></script>
	<script src="${pageContext.request.contextPath }/vjs/admin/ad_reg.js"></script>
<!--[if lt IE 9]>
    <script src="${pageContext.request.contextPath}/resources/js/ie/html5shiv.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/ie/respond.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/ie/excanvas.js"></script>
<![endif]-->	
</head>
<body>

  <section class="vbox">
    <header class="bg-black dk header navbar navbar-fixed-top-xs">
      <div class="navbar-header aside-md">
        <a class="btn btn-link visible-xs" data-toggle="class:nav-off-screen,open" data-target="#nav,html">
          <i class="fa fa-bars"></i>
        </a>
        <a href="index.jsp" class="navbar-brand" data-toggle="fullscreen"><!-- img src="images/logo.png" class="m-r-sm"-->가꼬가</a>
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
              <p>Home > 광고등록</p>
            </header>
            
            <section class="scrollable wrapper w-f">
                
                <form action="${pageContext.request.contextPath }/admin/adRegister.do" method="post" id="adForm" enctype="multipart/form-data">
	                <table class="admin">
	                    <colgroup><col style="width:15%"><col style="width:35%"><col style="width:15%"><col style="width:35%"></colgroup>
	                    <tbody><tr>
	                    	<th>구분</th>
	                    	<td style="text-align:left">
		                            <div class="radio" style="padding-left:0;">
		                              <label class="radio-custom" style="margin:25px!important">
		                                <input type="radio" name="ad_gubun" value="O" checked="checked">
		                                <i class="fa fa-circle-o"></i>외부광고</label>
		                            
		                              <label class="radio-custom">
		                                <input type="radio" name="ad_gubun" value="I">
		                                <i class="fa fa-circle-o"></i>내부광고</label>
		                            </div>
		                    </td>
		                    <th>응모여부</th>
		                    <td style="text-align:left">
		                    	<div class="radio" style="padding-left:0;">
		                    		<label class="radio-custom" style="margin:25px!important">
		                    			<input type="radio" name="entry_or" value="Y" checked="checked">
		                    			<i class="fa fa-circle-o"></i>사용</label>
		                    		<label class="radio-custom">
		                    			<input type="radio" name="entry_or" value="N">
		                    			<i class="fa fa-circle-o"></i>미사용</label>	
		                    	</div>
		                    </td>
	                    	</tr><tr>
	                        <th>시작날짜</th>
	                        <td><input type="text" id="start_date" name="start_date" class="input-sm input-s datepicker-input form-control" size="16" data-date-format="yyyy-mm-dd" ></td>
	                        <th>종료날짜</th>
	                        <td><input type="text" id="end_date" name="end_date" class="input-sm input-s datepicker-input form-control" size="16" data-date-format="yyyy-mm-dd" ></td>
	                        </tr><tr>
	                        <th>회사명</th>
	                        <td><input type="text" id="company_name" name="company_name" class="input-sm input-s  form-control"></td>
	                        <th>회사연락처</th>
	                        <td><input type="text" id="company_tel" name="company_tel" class="input-sm input-s  form-control"></td>
	                        </tr><tr>
	                        <th>회사주소</th>
	                        <td><input type="text" id="company_addr1" name="company_addr1" class="input-sm input-s  form-control"></td>
	                        <th>광고명</th>
	                        <td><input type="text" id="ad_name" name="ad_name" class="input-sm input-s  form-control"></td>
	                        </tr><tr id="gubunO">
	                        <th >광고 url</th>
	                        <td><input type="text" id="ad_url" name="ad_url" class="input-sm input-s  form-control"></td>
	                        <th></th>
	                        <td></td>
	                        </tr><tr id="gubunI1">
	                        <th>유튜브 url</th>
	                        <td><input type="text" id="youtube_addr" name="youtube_addr" class="input-sm input-s  form-control"></td>
	                        <th></th>
	                        <td></td>
	                        </tr><tr id="gubunI2">
	                        <th>소리파일</th>
	                        <td><input type="file" id="ad_sound_file" name="ad_sound_file"></td>
	                        <th>이미지파일</th>
	                        <td><input type="file" id="ad_image_file" name="ad_image_file"></td>
	                        
	                        </tr></tbody>
	                </table>                
                	<input type="button" id="adRegister" class="btn btn-primary" value="등록" style="margin-top:20px">
                </form>
                
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
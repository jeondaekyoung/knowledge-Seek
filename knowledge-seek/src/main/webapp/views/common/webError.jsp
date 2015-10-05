<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko" class="app">
<head>
	<meta charset="utf-8" />
	<meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" /> 
	<title>에러페이지</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css" type="text/css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/animate.css" type="text/css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css" type="text/css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font.css" type="text/css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/app.css" type="text/css" />
	
	<style>
	*{font-size: 12px}
        .admin {width:100%}
        .admin th {background-color:#eee; height:41px;}
        .admin th, .admin td{text-align:center; border:1px solid #ccc; padding:5px}
        .admin td input[type="text"] {width:100%; border-radius: 3px; border:1px solid #ccc}
    </style>
</head>

<body>

<section id="content">
    <div class="row m-n">
	      <div class="col-sm-4 col-sm-offset-4">
		        <div class="text-center m-b-lg">
		          <h1 class="h text-white"><a href="${pageContext.request.contextPath}/views/admin/index.jsp">가꼬가</a></h1>
		          <p><strong style="font-size:18px">죄송합니다.<br>요청하신 페이지를 찾을 수 없습니다.</strong></p>
		          <p>방문하시려는 페이지의 주소가 잘못 입력되었거나, 페이지의 주소가 변경 혹은 삭제되어</p>
		          <p>요청하신 페이지를 찾을 수 없습니다.</p>
		          <p>입력하신 주소가 정확한지 다시 한번 확인해 주시기 바랍니다.</p>
		        </div>
	        <div class="list-group m-b-sm bg-white m-b-lg">
		          
		          <a href="javascript:history.go(-1)" class="list-group-item">뒤로가기</a>
		          
	        </div>
	      </div>
    </div>
</section>

</body>
</html>
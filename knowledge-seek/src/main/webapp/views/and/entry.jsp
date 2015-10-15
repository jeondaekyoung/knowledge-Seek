<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" >
        <meta http-equiv="X-UA-Compatible" content="IE=edge" >
        <meta name="apple-mobile-web-app-capable" content="yes" >
        <meta name="viewport" content="user-scalable=no, width=device-width, initial-scale=1.0, maximum-scale=1.0">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css" type="text/css" />
        <title>당첨자 확인</title>
    <style>
        *{padding:0; margin: 0; border-spacing: 0; border-collapse:collapse; font-size: 12px; font-family: 'malgun gothic', sans-serif; text-decoration: none}
        body {background:#F7F7F7;}
        #wrap {width:100%; height: 100%;}
        .tbl {display: table; width:100%}
        table {width:100%}
        table th {background-color:#edde45;}
        table th, table td{text-align:center; border:1px solid #ddd; padding:10px 5px}
        input {width: 100%; margin: 5px 0}
        div.form-group {padding:0 10px}   
        div.form-group h4 {font-size: 16px; font-weight: bold; text-align: center; line-height: 37px;}
    </style>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/vjs/and/entry.js"></script>
    
    </head>

    <body>
        <div class="wrap">
        
			<div class="form-group">
	            <div class="col-lg-2-4"><h4>당첨자 검색</h4></div>
	        	<div class="col-lg-2-4"><input type="text" id="ad_name" class="input-m form-control" placeholder="이름"></div>
	        	<div class="col-lg-2-4"><input type="text" id="company_name" class="input-m form-control" placeholder="전화번호"></div>
	        	<div class="col-lg-2-4"><input type="text" id="company_name" class="input-m form-control" placeholder="이메일"></div>
	        	<div class="col-lg-2-4"><input type="button" id="searchAd" class="btn btn-s-lg btn-info" value="검색"></div>
	        </div>
        
            <table id="listTable">
                <thead><tr>
                        <th>광고이름</th>
                        <th>시작날짜</th>
                        <th>종료날짜</th>
                        <th>당첨여부</th>
                        <th>응모실시</th>
                    </tr></thead>
                <tbody>
                </tbody>
            </table>
        </div>
        
        
    </body>
</html>
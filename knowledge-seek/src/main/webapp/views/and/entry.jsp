<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" >
        <meta http-equiv="X-UA-Compatible" content="IE=edge" >
        <meta name="apple-mobile-web-app-capable" content="yes" >
        <meta name="viewport" content="user-scalable=no, width=device-width, initial-scale=1.0, maximum-scale=1.0">
        <title>당첨자 확인</title>
    <style>
        *{padding:0; margin: 0; border-spacing: 0; border-collapse:collapse; font-size: 12px; font-family: 'malgun gothic', sans-serif; text-decoration: none}
        #wrap {width:100%; height: 100%;}
        .tbl {display: table; width:100%}
        table {width:100%}
        table th {background-color:#edde45;}
        table th, table td{text-align:center; border:1px solid #ddd; padding:5px}
        /* input[type="button"] {border-radius: 3px; border:none; background: #424242; color: #fff; width: 100%; margin-top: 20px; padding: 10px 0; } */
    </style>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/vjs/and/entry.js"></script>
    
    </head>

    <body>
    
    
	<div>
	    <div>
	        이름 <input type="text" id="name">
	        전화번호 <input type="text" id="phone">
	        이메일 <input type="text" id="email">
	        <input type="button" id="entry" value="확인하기" >
	    </div>
	</div>
	
        <div class="wrap">
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge" >
    <meta name="apple-mobile-web-app-capable" content="yes" >
    <meta name="viewport" content="user-scalable=no, width=device-width, initial-scale=1.0, maximum-scale=1.0">
	<title>응모자목록</title>
	<style>
        *{padding:0; margin: 0; border-spacing: 0; border-collapse:collapse; font-size: 12px; font-family: 'malgun gothic', sans-serif; text-decoration: none}
        #wrap {padding:5px}
        #wrap .container {width:100%; max-height:500px; overflow-y: scroll; border-top:1px solid #eee; border-bottom:1px solid #eee}
        table {width:100%}
        table th {background-color:#eee; height:31px}
        table th, table td{text-align:center; border:1px solid #ddd; padding:5px}
        .btn {border-radius: 3px; border:none; background: #65BD77; color: #fff; padding: 5px 10px; margin: 10px;}
        .random, .apply {float: right; background: #72CDCE;}
        .random {background: #424242;}
        .box {display: none; padding: 20px; width: 140px; background: #fff; border:1px solid #72CDCE; border-radius: 5px; position: absolute; top: 100px; left: 200px; }
        .box strong {line-height: 30px}
        .box input {}
        .box input[type="text"] {padding: 5px 0; border-radius: 3px; border:1px solid #e4e4e4}
    </style>
    
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath }/vjs/admin/pop_entries.js"></script>
</head>

<body>
<c:choose>
	<c:when test="${empty sessionScope.adminName }">
		<c:redirect url="${pageContext.request.contextPath }/admin/index.do" />
	</c:when>
</c:choose>

    <div id="wrap">
        <div><input type="button" class="btn random" value="랜덤체크"></div>
       
		<div class="container"><table>
	            <thead><tr>
	                    <th>체크</th>
	                    <th>이름</th>
	                    <th>전화번호</th>
	                    <th>이메일</th> 
	                    <th>쿠폰번호</th>                    
	                </tr></thead>
	            <tbody>
	            	<c:if test="${not empty entriesList }">
	            		<c:forEach var="entries" items="${entriesList }">
	            		<tr>
	            			<td><input type="checkbox" class="memberChk" name="chkbox" value="${entries.entry_seq }"  <c:if test="${entries.win_sepa == 'Y'}">checked="checked"</c:if> ></td>
	                		<td>${entries.name }</td>
	                		<td>${entries.phone }</td>
	                		<td>${entries.email }</td>
	                		<td><input type="text" id="${entries.entry_seq }" value="${entries.coupon_num }"> </td>
	                	</tr>
	            		</c:forEach>
	            	</c:if>
	            </tbody>
	        </table>	
        </div>
        <div>
            <input type="button" id="winnerSend" value="문자발송" class="btn" >
        </div>
        
        <div class="box">
            <strong>당첨자 수를 입력하세요</strong>
            <input type="text" id="winner"><input type="button" value="적용" class="btn apply" id="btn_winner" >
        </div>
    </div>
   
</body>
</html>
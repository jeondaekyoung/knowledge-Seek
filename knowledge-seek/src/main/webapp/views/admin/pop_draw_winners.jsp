<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <script>
        $(document).ready(function(){

          	//당첨자 입력창 토글
            $(".random").click(function(){
                $(".box").toggle();
            });
            $(".apply").click(function(){
                $(".box").hide();
            });
            
    		//페이지 시작시 input 초기화
            chkNum.val("");
        	boxIpt.prop("checked", false);
        	
        });
	</script>

</head>

<body>

    <div id="wrap">
        <div><a href="#" class="btn random">랜덤체크</a></div>
       
		<div class="container"><table>
	            <thead><tr>
	                    <th>체크</th>
	                    <th>이름</th>
	                    <th>전화번호</th>
	                    <th>이메일</th> 
	                    <th>쿠폰번호</th>                    
	                </tr></thead>
	            <tbody><tr>
	                <td><input type="checkbox" class="memberChk" name="chkbox"></td>
	                <td>김김김</td>
	                <td>010-0000-0000</td>
	                <td>aaaaaaa.com</td>
	                <td>A20150903EFF</td>
	                </tr>
	                <tr>
	                <td><input type="checkbox" class="memberChk" name="chkbox"></td>
	                <td>김김김</td>
	                <td>010-0000-0000</td>
	                <td>aaaaaaa.com</td>
	                <td>A20150903EFF</td>
	                </tr>
	                <tr>
	                <td><input type="checkbox" class="memberChk" name="chkbox"></td>
	                <td>김김김</td>
	                <td>010-0000-0000</td>
	                <td>aaaaaaa.com</td>
	                <td>A20150903EFF</td>
	                </tr>
	                <tr>
	                <td><input type="checkbox" class="memberChk" name="chkbox"></td>
	                <td>김김김</td>
	                <td>010-0000-0000</td>
	                <td>aaaaaaa.com</td>
	                <td>A20150903EFF</td>
	                </tr>
	                <tr>
	                <td><input type="checkbox" class="memberChk" name="chkbox"></td>
	                <td>김김김</td>
	                <td>010-0000-0000</td>
	                <td>aaaaaaa.com</td>
	                <td>A20150903EFF</td>
	                </tr>
	                <tr>
	                <td><input type="checkbox" class="memberChk" name="chkbox"></td>
	                <td>김김김</td>
	                <td>010-0000-0000</td>
	                <td>aaaaaaa.com</td>
	                <td>A20150903EFF</td>
	                </tr>
	                <tr>
	                <td><input type="checkbox" class="memberChk" name="chkbox"></td>
	                <td>김김김</td>
	                <td>010-0000-0000</td>
	                <td>aaaaaaa.com</td>
	                <td>A20150903EFF</td>
	                </tr>
	                <tr>
	                <td><input type="checkbox" class="memberChk" name="chkbox"></td>
	                <td>김김김</td>
	                <td>010-0000-0000</td>
	                <td>aaaaaaa.com</td>
	                <td>A20150903EFF</td>
	                </tr>
	                <tr>
	                <td><input type="checkbox" class="memberChk" name="chkbox"></td>
	                <td>김김김</td>
	                <td>010-0000-0000</td>
	                <td>aaaaaaa.com</td>
	                <td>A20150903EFF</td>
	                </tr>
	                <tr>
	                <td><input type="checkbox" class="memberChk" name="chkbox"></td>
	                <td>김김김</td>
	                <td>010-0000-0000</td>
	                <td>aaaaaaa.com</td>
	                <td>A20150903EFF</td>
	                </tr>
	            </tbody>
	        </table>	
        </div>
        <div>
            <input type="button" value="문자발송" class="btn">
            <input type="button" value="엑셀다운" class="btn">
        </div>
        
        <div class="box">
            <strong>당첨자 수를 입력하세요</strong>
            <input type="text" id="winner"><input type="button" value="적용" class="btn apply" id="btn_winner" >
        </div>
    </div>
    
	<script>
	var chkNum = $("#winner");	//당첨자 txt박스
	var allChk = $("#btn_winner");	//확인버튼
	var box = $("table");				//테이블
	var boxIpt = box.find("input");	//체크박스
	var totIpt = boxIpt.length;		//전체 체크박스 개수
	var arr;
	
	// input의 갯수만큼 랜덤수 배열 만들기
	function randomMake(){
		arr = [];
		//console.log("totIpt : " + totIpt);
		for(var j=0; j<totIpt; j++){
			var r = Math.floor(Math.random() * totIpt);
			//console.log("r : " + r + ", j : " + j + ", arr.indexOf(r) : " + arr.indexOf(r));
			(arr.indexOf(r) == -1) ? arr[j] = r : j--;
		}
		//console.log(arr);
	}
	
	// 버튼 클릭시 이벤트
	allChk.click(function(){
		var num = chkNum.val();
		
		if(num == "" || num.length == 0){
			alert("아무것도 입력하지 않았습니다.");
			return false;
		}
		if(num <= 0){
			alert("0보다 큰 수를 입력하십시오.");
			return false;
		}
		if(num >= totIpt){
			alert("전체 응모자 수보다 큰 수를 입력했습니다.");
			return false;
		}
		if(isNaN(num)){
			alert("숫자가 아닙니다.");
			return false;
		}

		// 체크 전체 해제
		boxIpt.prop("checked", false);

		// 입력한 수 만큼 랜덤으로 아무거나 체크하기
		randomMake();
		//console.log("boxIpt : " + boxIpt);
		for(var k=0; k<num; k++){
			boxIpt.eq(arr[k]).prop("checked", true);
		}

	});
	</script>


</body>
</html>
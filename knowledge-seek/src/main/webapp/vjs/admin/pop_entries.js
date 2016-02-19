
$(document).ready(function(){
	//console.log("열렸다.");
	var url = window.location.protocol + '//' +window.location.host;
	
  	//당첨자 입력창 토글
    $(".random").click(function(){
        $(".box").toggle();
    });
    $(".apply").click(function(){
        $(".box").hide();
    });
    
	//페이지 시작시 input 초기화
    $("#winner").val("");		//당첨자 txt박스
    /*$("input:text").each(function(){
    	$(this).val("");
    });*/

	
	//랜덤수입력후 적용클릭시 이벤트
	$("#btn_winner").click(function(){
		var num = $("#winner").val();	//당첨자 txt박스
		var box = $("table");				//테이블
		var boxIpt = $("input:checkbox");	//체크박스
		var totIpt = $("input:checkbox").length;		//전체 체크박스 개수
		var arr;
		
		if(num == "" || num.length == 0){
			alert("아무것도 입력하지 않았습니다.");
			return false;
		}
		if(num <= 0){
			alert("0보다 큰 수를 입력하십시오.");
			return false;
		}
		if(num > totIpt){
			alert("전체 응모자 수보다 큰 수를 입력했습니다.");
			return false;
		}
		if(isNaN(num)){
			alert("숫자가 아닙니다.");
			return false;
		}

		// 체크 전체 해제
		boxIpt.prop("checked", false);

		//input의 갯수만큼 랜덤수 배열 만들기
		arr = [];
		for(var j=0; j<totIpt; j++){
			var r = Math.floor(Math.random() * totIpt);
			(arr.indexOf(r) == -1) ? arr[j] = r : j--;
		}
		// 입력한 수 만큼 랜덤수로 체크하기
		for(var k=0; k<num; k++){
			boxIpt.eq(arr[k]).prop("checked", true);
		}
	});
	
	//문자발송 클릭
	$("#winnerSend").click(function(){
		//console.log("문자발송");
		var num = $("#winner").val();	//당첨자 txt박스
		var entriesY = new Array();
		var couponY = new Array();
		 
		$("input:checkbox[name='chkbox']:checked").each(function(){
			var entry_seq = $(this).val();
			entriesY.push(entry_seq);
			var idresult = "#" + entry_seq;
			var coupon_num = $(idresult).val();
			couponY.push(coupon_num);
		});
		//console.log(couponY);
		var entriesN = new Array();
		$("input:checkbox[name='chkbox']:not(:checked)").each(function(){
			var entry_seq = $(this).val();
			entriesN.push(entry_seq);
		});
		//console.log(entriesY + ", " + couponY + ", "+ entriesN);
		if(entriesY.length > 0){
			$.ajax({
				url: "/admin/sendWinner.do"
				,type: "POST"
				,data: {
					entry_seqsY: entriesY
					,entry_seqsN: entriesN
					,coupon_numsY: couponY
				}
				,success: sendWinnerSuccess
				,error: errorCallback
			});
		} else {
			alert("당첨자를 체크해주세요");
		}
		
		
	});
	
});


var sendWinnerSuccess = function(resultData){
	//console.log("ajax 성공 : " + resultData);
	if(resultData == "finish event"){
		alert("이미 당첨이 완료된 이벤트입니다.");
	} else if(resultData == "success"){
		alert("당첨자가 등록되었습니다.");
	}
}

var errorCallback = function(){
	alert("수행 중 오류가 발생했습니다.");
};

//데이터 널 체크
var checkNull = function(data){
	if(data == ""){
		return undefined;
	} else {
		return data;
	}
};
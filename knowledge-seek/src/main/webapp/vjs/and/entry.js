

$(document).ready(function(){
	
	var url = window.location.protocol + '//' + window.location.host;
		
	$("#entry").click(function(){
		var name = checkNull($("#name").val());
		var phone = checkNull($("#phone").val());
		var email = checkNull($("#email").val());
		
		if(checkValue() == true){
			console.log("당첨자 확인하기 ");
			$.ajax({
				url: "/and/adEntryResult.do"
				,type: "POST"
				,data: {
					name: checkNull($("#name").val())
					,phone: checkNull($("#phone").val())
					,email: checkNull($("#email").val())
				}
				,success: entrySuccess
				,error: errorCallback
			})
		}
	});
	
});

var entrySuccess = function(resultData){
	console.log("ajax성공 : " + resultData);
	$("#listTable > tbody").empty();
	$.each(resultData, function(index, item){
		var sbmTr = sbmHtmlTemplate.makesbmTr(index, item);
		$("#listTable > tbody").append(sbmTr);
	});
}
var sbmHtmlTemplate = {
		makesbmTr : function(index, item){
			var sysdate = new Date(item.input_datetime);
			var sbmTr = "<tr><td>" + item.ad_name + "</td><td>" + item.start_date + "</td><td>" + item.end_date + "</td><td>"
			+ item.win_sepa +   "</td><td>"	+ formatDate(sysdate) + "</td></tr>";
	return sbmTr;
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

var checkValue = function(){
	if($("#name").val() == ""){
		alert("이름을 입력하세요");
		$("#name").focus();
		return false;
	}
	if($("#phone").val() == ""){
		alert("전화번호를 입력하세요");
		$("#phone").focus();
		return false;
	}
	if($("#email").val() == ""){
		alert("이메일을 입력하세요");
		$("#email").focus();
		return false;
	}
	
	return true;
}

//시간포맷
var formatDate = function(dateObj){
	var curr_year = dateObj.getFullYear();
	var curr_month = dateObj.getMonth() + 1;
	var curr_date = dateObj.getDate();
	var curr_hr = dateObj.getHours();
	var curr_min = dateObj.getMinutes();
	
	if(curr_month.toString().length == 1) {
		curr_month = '0' + curr_month;
	}
	if(curr_date.toString().length == 1){
		curr_date = '0' + curr_date;
	}
	if(curr_hr.toString().length == 1){
		curr_hr = '0' + curr_hr;
	}
	if(curr_min.toString().length == 1){
		curr_min = '0' + curr_min;
	}
	
	return curr_year + "-"+ curr_month + "-" + curr_date + "  " + curr_hr + ":" + curr_min;
}

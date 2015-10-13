
$(document).ready(function(){
	
	$("#searchAd").click(function(){
		//console.log("검색클릭");
		var ad_name = checkNull($("#ad_name").val());
		var company_name = checkNull($("#company_name").val());
		var start_date = checkNull($("#start_date").val());
		var end_date = checkNull($("#end_date").val());
		var url = window.location.protocol + '//' +window.location.host;
		//console.log(url);
		
		console.log(ad_name + ", " + company_name + ", " + start_date + ", " + end_date);
		if((ad_name==null) && (company_name==null) && (start_date==null) && (end_date==null)){
			alert("검색조건을 입력해주세요");
		} else {
			$.ajax({
				url:  "/admin/searchAdList.do"
				,type: "POST"
				,data: {
					ad_name: ad_name
					,company_name: company_name
					,start_date: start_date
					,end_date: end_date
				}
				,success: searchAdListSuccess
				,error: errorCallback
			});
		}
		
	});
	
});

var searchAdListSuccess = function(resultData){
	console.log("ajax 성공 : " + resultData);
	$("#adTable > tbody").empty();
	$.each(resultData, function(index, item){
		//console.log(key + ", " + value.ad_name);
		var sbmTr = sbmHtmlTemplate.makesbmTr(index, item);
		$("#adTable > tbody").append(sbmTr);
				
	});
}
var sbmHtmlTemplate = {
		makesbmTr : function(index, item){
			var sysdate = new Date(item.input_date);
			var sbmTr = "<tr><td>" + formatDate(sysdate) + "</td><td>" + item.start_date + "</td><td>" + item.end_date + "</td><td>"
					+ "<input type='button'  class='pop_draw_winners' onclick='winners(" + item.ad_seq + ");' value='" + item.ad_name + "'>" +   "</td><td>" 
					+ item.company_name + "</td><td>" + item.company_tel + "</td><td>" + item.company_addr1 + "</td></tr>";
			
			return sbmTr;
		}
};

var winners = function(ad_seq){
	console.log("광고시퀀스 : " + ad_seq + " - 응모자목록으로 가서 당첨자를 만든다.");
	var url = window.location.protocol + '//' +window.location.host + "/admin/popEntries.do?ad_seq=" + ad_seq;
	window.open(url, '응모자 목록','width=600, height=620, scrollbars=yes, location=no');
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
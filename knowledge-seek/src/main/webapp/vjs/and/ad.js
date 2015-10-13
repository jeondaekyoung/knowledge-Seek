

$(document).ready(function(){
	
	var url = window.location.protocol + '//' + window.location.host;
	var ad_gubun = $("#ad_gubun").val();
	var entry_or = $("#entry_or").val();
	//var file = $("#audiofile").val();
	/*var ad_url = $("#ad_url").val();
	console.log("광고구분 " + ad_gubun + "," + ad_url);*/
	
	//화면셋팅
	if(ad_gubun == 'O'){	//외부광고
		$("#ad_gubunI").hide();
		$("#ad_gubunO").show();
		
	}
	if(ad_gubun == 'I'){		//내부광고
		$("#ad_gubunI").show();
		$("#ad_gubunO").hide();
		//오디오시작
		console.log("오디오시작 ");
		var audioElement = document.createElement("audio");
		audioElement.setAttribute("src", $("#audiofile").val());
		audioElement.setAttribute("loop", true);
		audioElement.play();
		console.log(audioElement);
		
		if(entry_or == 'Y'){
			$("#entry_orY").show();
		} else {
			$("#entry_orY").hide();
		}
	}
	
	$("#entry").click(function(){
		var name = checkNull($("#name").val());
		var phone = checkNull($("#phone").val());
		var email = checkNull($("#email").val());
		
		if(checkValue() == true){
			console.log("응모하기 ");
			$.ajax({
				url: "/and/entry.do"
				,type: "POST"
				,data: {
					name: checkNull($("#name").val())
					,phone: checkNull($("#phone").val())
					,email: checkNull($("#email").val())
					,ad_seq: $("#ad_seq").val()
				}
				,success: entrySuccess
				,error: errorCallback
			})
		}
	});
	
});

var entrySuccess = function(resultData){
	console.log(resultData);
	if(resultData == 'success'){
		alert("응모하였습니다");
	}
	if(resultData == 'fail'){
		alert("응모한 이벤트입니다");
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


$(document).ready(function(){
	//console.log("열렸다.");
	
	$("#engRegister").click(function(){
		console.log("저장클릭");
		if(checkValue() == true){
			//폼태크전송
			console.log("폼태그 전송");
			$("#engForm").submit();
		}
		
	});
});

//필수항목 저장 전 체크
var checkValue = function(){
	if($("#today_date").val() == ""){
		alert("날짜를 입력해주세요.");
		$("#today_date").focus();
		return false;
	}
	if($("#eng_sound_file").val() == ""){
		alert("소리파일을 선택해주세요.");
		$("#eng_sound_file").focus();
		return false;
	} else {
		if( $("#eng_sound_file").val() != "" ){
			var ext = $('#eng_sound_file').val().split('.').pop().toLowerCase();
			      if($.inArray(ext, ['mp3']) == -1) {
				 alert('mp3 파일만 업로드 할수 있습니다.');
				 return;
			      }
			}
	}
	if($("#eng_sentence").val() == ""){
		alert("예문을 입력해주세요");
		$("#eng_image_file").focus();
		return false;
	}
	if($("#eng_mean").val() == ""){
		alert("뜻을 입력해주세요");
		$("#eng_mean").focus();
		return false;
	}
	
	return true;
}









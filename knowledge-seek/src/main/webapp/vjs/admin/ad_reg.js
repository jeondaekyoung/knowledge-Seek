
$(document).ready(function(){
	
	$("#gubunO").show();
	$("#gubunI").hide();
	
	
	//광고 구분 변경
	$("input[name=ad_gubun]").change(function(){
		//console.log($("input[name=ad_gubun]:checked").val());
		if($("input[name=ad_gubun]:checked").val() == "O"){
			$("#gubunO").show();
			$("#gubunI").hide();
		}
		if($("input[name=ad_gubun]:checked").val() == "I"){
			$("#gubunI").show();
			$("#gubunO").hide();
		}
	});
	
	//유튜브와 이미지파일 중 하나만 등록하도록 입력이 되었는지 확인한다.
	$("#youtube_addr").change(function(){
		if($(this).val().length > 0){
			$("#ad_sound_file").attr("disabled", true);
			$("#ad_image_file").attr("disabled", true);
		} else {
			$("#ad_sound_file").attr("disabled", false);
			$("#ad_image_file").attr("disabled", false);
		}
	});
	$("#ad_sound_file").change(function(){
		if($(this).val().length > 0 || $("#ad_image_file").val().length > 0){
			$("#youtube_addr").attr("disabled", true);
		} else {
			$("#youtube_addr").attr("disabled", false);
		}
	});
	$("#ad_image_file").change(function(){
		if($(this).val().length > 0 || $("#ad_sound_file").val().length > 0){
			$("#youtube_addr").attr("disabled", true);
		} else {
			$("#youtube_addr").attr("disabled", false);
		}
	});
	
	//등록
	$("#adRegister").click(function(){
		//console.log("저장클릭");
		if(checkValue() == true){
			//console.log("폼태그 전송");
			$("#adForm").submit();
		}
	});
	
});

//필수항목 저장 전 체크
var checkValue = function(){
	if($("#start_date").val() == ""){
		alert("날짜를 입력해주세요.");
		$("#start_date").focus();
		return false;
	}
	if($("#end_date").val() == ""){
		alert("날짜를 입력해주세요");
		$("#end_date").focus();
		return false;
	}
	if($("#company_name").val() == ""){
		alert("회사명을 입력해주세요");
		$("#company_name").focus();
		return false;
	}
	if($("#company_tel").val() == ""){
		alert("회사연락처를 입력해주세요");
		$("#company_tel").focus();
		return false;
	}
	if($("#company_addr1").val() == ""){
		alert("회사주소를 입력해주세요");
		$("#company_addr1").focus();
		return false;
	}
	if($("#ad_name").val() == ""){
		alert("광고명을 입력해주세요");
		$("#ad_name").focus();
		return false;
	}
	
	if($("input[name=ad_gubun]:checked").val() == "O"){
		if($("#ad_url").val() == ""){
			alert("광고url를 입력해주세요");
			$("#ad_url").focus();
			return false;
		}
		if($("#ad_sound_file").val().length <= 0){
			alert("소리파일을 입력해주세요");
			$("#ad_sound_file").focus();
			return false;
		}
	}
	
	if($("input[name=ad_gubun]:checked").val() == "I"){
		if($("#youtube_addr").val().length <= 0 && 
				$("#ad_sound_file").val().length <= 0 && $("#ad_image_file").val().length <= 0){
			alert("광고 컨턴츠(유튜브url 또는 소리 및 이미지파일)를 등록해주세요");
			return false;
		}
		if($("#youtube_addr").val().length <= 0){
			if($("#ad_sound_file").val().length <= 0 || $("#ad_image_file").val().length <= 0){
				alert("소리 및 이미지파일을 확인해주세요");
				return false;
			}
		} 

	}
	
	return true;
}





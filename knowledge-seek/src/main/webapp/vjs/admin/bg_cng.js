$(document).ready(function(){
	
	$("#bgRegister").click(function(){
		console.log("저장클릭");
		if(checkValue() == true){
			//폼태크전송
			console.log("폼태그 전송");
			$("#bgForm").submit();
		}
		
	});
	
	//취소버튼
	/*$("#main_bg_file").change(function(){
		console.log("버튼을 눌렀다? 파일이 변경되었다?" +  $(this).val().length);
		//console.log("버튼을 눌렀다? 파일이 변경되었다?");
		if($(this).val().length == 0){
			console.log("0이다.");
		}
	});*/
	/*$("#main_bg_file").change(function(){
		console.log("바뀜1");
	});
	
	$("#main_bg_file").click(function(){
		console.log("클릭함1");
	})*/
});



var checkValue = function(){
	
	if($("#main_bg_file").val().length <= 0 && $("#star_bg_file").val().length <= 0 && $("#eng_bg_file").val().length <= 0){
		alert("업로드할 파일을 확인해주세요");
		return false;
	}
	
	//확장자 체크
	if( $("#main_bg_file").val() != ""){
		var ext = $('#main_bg_file').val().split('.').pop().toLowerCase();
	    if($.inArray(ext, ['png', 'jpg']) == -1) {
	    	alert('png파일와 jpg파일만 업로드 할수 있습니다.');
			return false;
	    }
	}
	if( $("#star_bg_file").val() != ""){
		var ext = $('#star_bg_file').val().split('.').pop().toLowerCase();
	    if($.inArray(ext, ['png', 'jpg']) == -1) {
	    	alert('png파일와 jpg파일만 업로드 할수 있습니다.');
	    	return false;
	    }
	}
	if( $("#eng_bg_file").val() != ""){
		var ext = $('#eng_bg_file').val().split('.').pop().toLowerCase();
	    if($.inArray(ext, ['png', 'jpg']) == -1) {
	    	 alert('png파일와 jpg파일만 업로드 할수 있습니다.');
	    	 return false;
	    }
	}
	
	return true;
}
$(document).ready(function(){
	
	$("#bgRegister").click(function(){
		console.log("저장클릭");
		if(checkValue() == true){
			//폼태크전송
			console.log("폼태그 전송");
			$("#bgForm").submit();
		}
		
	});
});

var checkValue = function(){
	
	if( $("#bg_main_file").val() != "" ){
		var ext = $('#bg_main_file').val().split('.').pop().toLowerCase();
		      if($.inArray(ext, ['png']) == -1) {
			 alert('png 파일만 업로드 할수 있습니다.');
			 return;
		      }
		}
	if( $("#bg_star_file").val() != "" ){
		var ext = $('#bg_star_file').val().split('.').pop().toLowerCase();
		      if($.inArray(ext, ['png']) == -1) {
			 alert('png 파일만 업로드 할수 있습니다.');
			 return;
		      }
		}
	if( $("#bg_eng_file").val() != "" ){
		var ext = $('#bg_eng_file').val().split('.').pop().toLowerCase();
		      if($.inArray(ext, ['png']) == -1) {
			 alert('png 파일만 업로드 할수 있습니다.');
			 return;
		      }
		}
	return true;
}
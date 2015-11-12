$(document).ready(function(){
	//console.log("열렸다.");
	
	$("#engRegister").click(function(){
		console.log("저장클릭");
		if(checkValue() == true){
			//폼태크전송
			console.log("폼태그 전송");
			$("#bgForm").submit();
		}
		
	});
});

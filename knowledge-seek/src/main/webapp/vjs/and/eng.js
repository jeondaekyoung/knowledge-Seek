

$(document).ready(function(){
	
	//오디오시작
	console.log("오디오시작 ");
	var audioElement = document.createElement("audio");
	audioElement.setAttribute("src", $("#audiofile").val());
	audioElement.setAttribute("loop", true);
	audioElement.play();
	console.log(audioElement);
	
		
});

var engByEngSeq = function(data){
	console.log(data);
	location.replace("/and/engByEngSeq.do?eng_seq=" + data);
}


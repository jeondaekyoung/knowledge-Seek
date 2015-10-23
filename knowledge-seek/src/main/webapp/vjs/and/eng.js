

$(document).ready(function(){
	
	//오디오시작
	console.log("오디오시작 ");
	var audioElement = document.createElement("audio");
	audioElement.setAttribute("src", $("#audiofile").val());
	audioElement.setAttribute("loop", true);
	audioElement.play();
	console.log(audioElement);
	
	/* 버튼 클릭시 페이지 이동 자바스크립트 */
	$("#btnPrev").click(function() {
		alert("눌러");
        //현재 left값 구하기
        var left = parseInt($(".imgContent").css("left"));
        if(left >= 0) return;
        left += 300;
        var movePos = Math.floor(left/300.0)*300; 
        $(".imgContent").animate({left : movePos}, 1000);
    });
    $("#btnNext").click(function() {
        var left = parseInt($(".imgContent").css("left"));
        if(left <= -1000) return;
        left -= 300;
        var movePos = Math.ceil(left/300.0)*300;
        $(".imgContent").animate({left : movePos}, 1000);
    });
		
});


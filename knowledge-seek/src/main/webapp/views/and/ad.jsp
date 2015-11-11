<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" >
    <meta name="apple-mobile-web-app-capable" content="yes" >
    <meta name="viewport" content="user-scalable=no, width=device-width, initial-scale=1.0, maximum-scale=1.0">
	<title>광고알람</title>
    <style type="text/css">
        *{padding:0; margin: 0; border-spacing: 0; border-collapse:collapse; font-size: 12px; font-family: 'malgun gothic', sans-serif; text-decoration: none;}
        body{width:100%; height:100%;}
        .tbl {display: table; width:100%}
        .tbl-cell {display: table-cell; vertical-align: middle; text-align: center; }
        .eventImg {}
        .eventImg .tbl-cell img {height:380px}
        .outIframe iframe {width:100%; height:600px}
        .uTube {height:20%; overflow: hidden}
        .inputTxt {height:10%; overflow: hidden}
        .inputBtn {height:10%; overflow: hidden}        
        .inputTxt input {border-radius: 3px; border: 1px solid #ddd; padding: 5px; width: 15%}
        .inputBtn input {border-radius: 3px; border:none; background: #424242; color: #fff; width: 100%; padding: 10px 0; }
    </style>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/vjs/and/ad.js"></script>

</head>

<body>

	<div id="ad_gubunI">
        <div class="tbl eventImg">
		    <div class="tbl-cell">
		        <img src="${pageContext.request.contextPath }/fileupload/image/${ad.ad_image_server}" alt="광고이미지" >
		    </div>
		</div>
		
		
		<div class="tbl uTube">
		    <div class="tbl-cell">
		        <iframe width="100%" height="100%" src="${ad.youtube_addr }?autoplay=1" frameborder="0" allowfullscreen ></iframe>
		    </div>
		</div>
	</div>
	
	<div id="ad_gubunO" class="outIframe">
		<iframe id="urlTest" src="${ad.ad_url }" width="100%" height="100%" ></iframe>
	</div>
	
	<input type="text" id="audiofile" value="${pageContext.request.contextPath }/fileupload/sound/${ad.ad_sound_server}"  hidden="true" >
	
<input type="hidden" id="ad_gubun" value="${ad.ad_gubun }" />
<input type="hidden" id="entry_or" value="${ad.entry_or }" />
<input type="hidden" id="ad_seq" value="${ad.ad_seq }" />
</body>
</html>
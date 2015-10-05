<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>

    <head>
        <title>로그인</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" >
        <meta http-equiv="content-type" content="text/html; charset=utf-8" >
        <meta name="apple-mobile-web-app-capable" content="yes" >
        <meta name="viewport" content="user-scalable=no, width=device-width, initial-scale=1.0, maximum-scale=1.0">
        
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <style>
        *{padding:0; margin: 0; border-spacing: 0; border-collapse:collapse; font-size: 12px; font-family: 'malgun gothic', sans-serif; text-decoration: none}
        #wrap {width:100%; height: 100%;}
        table {width:100%}
        table th {background-color:#edde45;}
        table th, table td{text-align:center; border:1px solid #ddd; padding:5px}
        input[type="button"] {border-radius: 3px; border:none; background: #424242; color: #fff; width: 100%; margin-top: 20px; padding: 10px 0; }
    
    </style>
        
        <script>
            $(document).ready(function(){
                $(".random").click(function(){
                    $(".box").toggle();
                });
                
                $(".apply").click(function(){
                    $(".box").hide();
                });
            });
        </script>
    </head>

    <body>
        <div id="wrap">
          
            <table>
                <thead><tr>
                        <th>광고이름</th>
                        <th>시작날짜</th>
                        <th>종료날짜</th>
                        <th>이름</th>
                        <th>이메일</th>
                        <th>당첨</th>
                        <th>사용</th>
                    </tr></thead>
                <tbody><tr>
                    <td>해피광고</td>
                    <td>15-05-05</td>
                    <td>15-05-05</td>
                    <td>주아람</td>
                    <td>aaaa@aaa.com</td>
                    <td>O</td>
                    <td>X</td>
                    </tr>
                    
                </tbody>
            </table>
            
            <div>
                <input type="button" value="확인" class="btn">
            </div>
            
        </div>
    </body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<title>TELCO</title>
</head>
<body>

<%
	//response.sendRedirect("/homepage/index.jsp"); 
RequestDispatcher rd=request.getRequestDispatcher("/homepage/index.jsp");
rd.forward(request,response);

%>

</body>
</html>
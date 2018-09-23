<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
	String serverName =request.getServerName();
	int port=request.getServerPort();
	String context=request.getContextPath();
	String basePath="http://"+serverName+":"+port+context;
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>
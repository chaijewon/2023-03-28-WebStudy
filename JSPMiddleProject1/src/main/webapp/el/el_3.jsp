<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.model.*"%>
<jsp:useBean id="model" class="com.sist.model.SawonModel"/>
<%
    model.sawonInfo(request);// Controller 해결 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
사번:${vo.getSabun() }<br>
이름:${vo.name }<br>
부서:${vo.dept }<br>
직위:${vo.job }<br>
연봉:${vo.pay }
</body>
</html>
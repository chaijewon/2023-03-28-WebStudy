<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String id=request.getParameter("id");
    
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <center>
    <h1>Content.jsp</h1>
    <h3>전송된 값:<%=id %></h3>
   </center>
</body>
</html>
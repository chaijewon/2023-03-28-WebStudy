<%--
    JSP안에서 조건문 
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
     int sum=0;
     int even=0;
     int odd=0;
%>
<%--
   <%! %> : X
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <%
     for(int i=1;i<=100;i++)
     {
    	 if(i%2==0)
    		 even+=i;
    	 else 
    		 odd+=i;
    	 
    	 sum+=i;
     }
  %>
  <h1>1~100까지의 총합:<%=sum %></h1>
  <h1>1~100까지의 짝수합:<%=even %></h1>
  <h1>1~100까지의 홀수합:<%=odd %></h1>
</body>
</html>
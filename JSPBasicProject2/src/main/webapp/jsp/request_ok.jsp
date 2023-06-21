<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    //request.jsp에서 전송된 데이터를 받는다 
    // 1. 한글 (2byte로 읽어온다 => Unicode)
    request.setCharacterEncoding("UTF-8");
    // 1) 이름 (단일값) <input type=text name=name>
    String name=request.getParameter("name");
    String sex=request.getParameter("sex");
    String tel=request.getParameter("tel");
    String tel2=request.getParameter("tel2");
    String content=request.getParameter("content");
    // 입력창 대부분은 getParameter / checkbox= getParameterValues
    String[] hobby=request.getParameterValues("hobby");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>전송된 값</h1>
이름:<%=name %><br>
성별:<%=sex %><br>
전화:<%= tel+")"+tel2 %><br>
소개:<%=content %><br>
취미:
    <ul>
    <%-- <%
       if(hobby!=null)
       {
    	   for(String h:hobby)
    	   {
    %>
               <li><%=h %></li>
    <%
    	   }
       }
       else
       {
    %>
          <font color="red">취미가 없습니다</font>
    <%
        }
    %> --%>
    <%
       try
       {
    	  for(String h:hobby)
    	  {
    %>
             <li><%=h %></li>
    <%
    	  }
       }catch(Exception ex)
       {
    %>
           <font color="red">취미가 없습니다</font>
    <%
       }
    %>
    </ul>
</body>
</html>
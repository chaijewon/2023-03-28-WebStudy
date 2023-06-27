<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
     String fno=request.getParameter("fno");
     // Cookie읽기
     Cookie[] cookies=request.getCookies();
     if(cookies!=null)
     {
    	 for(int i=0;i<cookies.length;i++)
    	 {
    		 if(cookies[i].getName().equals("food_"+fno))
    		 {
    			 cookies[i].setPath("/");
    			 // 삭제
    			 cookies[i].setMaxAge(0);
    			 response.addCookie(cookies[i]);
    			 break;
    		 }
    	 }
     }
     response.sendRedirect("main.jsp");
%>
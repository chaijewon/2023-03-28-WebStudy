<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<jsp:useBean id="dao" class="com.sist.dao.DataBoardDAO"></jsp:useBean>
<%
    String no=request.getParameter("no");
    String bno=request.getParameter("bno");
    
    dao.replyDelete(Integer.parseInt(no));
    
    // 이동 
    response.sendRedirect("detail.jsp?no="+bno);
%>

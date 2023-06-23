<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*,com.sist.vo.*"%>
<jsp:useBean id="dao" class="com.sist.dao.DataBoardDAO"></jsp:useBean>
<%
    request.setCharacterEncoding("UTF-8");
    String msg=request.getParameter("msg");
    String bno=request.getParameter("bno");
    String no=request.getParameter("no");
    
    // DAO연결 
    dao.replyUpdate(Integer.parseInt(no), msg);
    // 이동 
    response.sendRedirect("detail.jsp?no="+bno);
%>

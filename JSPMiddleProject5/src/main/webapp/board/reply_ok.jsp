<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
      request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="dao" class="com.sist.dao.ReplyBoardDAO"/>
<%-- 값을 받는다 --%>
<jsp:useBean id="vo" class="com.sist.dao.ReplyBoardVO">
  <jsp:setProperty name="vo" property="*"/>
</jsp:useBean>
<%
    String pno=request.getParameter("pno");// gi , gs, gt , root , depth
    // DAO 
    dao.replyInsert(Integer.parseInt(pno), vo);
%>
<c:redirect url="list.jsp"/>

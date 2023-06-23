<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    // session 해제 => 저장된 모든 정보를 지운다 
    session.invalidate();
    // 한개씩 지우는 메소드 : removeAttribute("key")
    response.sendRedirect("../databoard/list.jsp");
%>
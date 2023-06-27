<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    // 쿠키를 생성해서 전송 
    String fno=request.getParameter("fno");
    // 1. 쿠키 생성 
    Cookie cookie=new Cookie("food_"+fno,fno);
    // 2. 저장 기간 설정
    cookie.setMaxAge(60*60*24);
    // 3. 경로 지정 
    cookie.setPath("/");
    // 4. 클라이언트 브라우저로 전송 
    response.addCookie(cookie);
    // 전송 끝나면 => detail로 이동 
    response.sendRedirect("detail.jsp?fno="+fno); // GET
%>